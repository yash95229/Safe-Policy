package com.safepolicy.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.safepolicy.dao.PersonalDetailRepository;
import com.safepolicy.dao.PolicyRepository;
import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.Order;
import com.safepolicy.model.PersonalDetail;
import com.safepolicy.model.Policy;
import com.safepolicy.model.User;
import com.safepolicy.service.PaypalService;
import com.safepolicy.service.PdfService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

@EnableMethodSecurity
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;

    @Autowired
	PolicyRepository policyRepository;
    
    @Autowired
    APIContext apiContext;
    
	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	 PdfService pdfService;
	 
	 @Autowired
	 PersonalDetailRepository personalDetailRepository;
	 
	 @Autowired
	 JavaMailSender mailSender;

    
    public static final String successUrl = "http://localhost:8080/pay/success";
    public static final String cancelUrl = "http://localhost:8080/pay/cancel";

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order theOrder,HttpSession httpSession, @RequestParam("quoteId") String quoteId) throws PayPalRESTException {
        
    	
    	try {
            Payment thePayment = paypalService.createPayment(theOrder.getPrice(), theOrder.getCurrency(),
                    theOrder.getMethod(), theOrder.getIntent(), theOrder.getDescription(), cancelUrl, successUrl);
            
            httpSession.setAttribute("quoteId", quoteId);
          
            for (Links links: thePayment.getLinks()) 
            	if(links.getRel().equals("approval_url")) return "redirect:"+links.getHref();
                
            
        }
        catch (PayPalRESTException payPalRESTException) {
            payPalRESTException.printStackTrace();
        }
       
        return "redirect:/";
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpSession httpSession, Principal principal) throws IOException, MessagingException {
 
           String quoteId = (String) httpSession.getAttribute("quoteId");
   
   		List<Policy> quoteIdData = policyRepository.findByQuoteId(quoteId);
   		
   		quoteIdData.stream()
   	    .forEach(policy -> {
   	        policy.setPolicyStatus("Policy Issued");
   	        policyRepository.save(policy);
   	    });
   		
   		//pdf data
   		
   		List<Policy> policy = policyRepository.findByQuoteId(quoteId);
        List<PersonalDetail> personalDetail = personalDetailRepository.findByQuoteId(quoteId);
        byte[] pdfBytes = pdfService.generatePolicyPdf(policy,personalDetail);

      //sending mail
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //SimpleMailMessage message = new SimpleMailMessage();
        
        String userName = principal.getName();
        
        helper.setFrom("safepolicyteam@gmail.com");
        helper.setTo(userName);
        helper.setSubject("Policy Details");
        helper.setText("Successfully Applied for the Policy");
        helper.addAttachment("policy.pdf", new ByteArrayResource(pdfBytes));
        
        mailSender.send(mimeMessage);
        
        return "redirect:/policyView";
    }
    
    @GetMapping("/cancel")
	public String cancelPay(HttpSession httpSession) throws PayPalRESTException{
    	
    	String quoteId = (String) httpSession.getAttribute("quoteId");

		List<Policy> quoteIdData = policyRepository.findByQuoteId(quoteId);
		quoteIdData.stream()
	    .forEach(policy -> {
	        policy.setPolicyStatus("Policy Pending");
	        policyRepository.save(policy);
	    });

		 return "redirect:/policyView";
			
	}

}
package com.safepolicy.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safepolicy.dao.PersonalDetailRepository;
import com.safepolicy.dao.PolicyRepository;
import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.PersonalDetail;
import com.safepolicy.model.Policy;
import com.safepolicy.model.User;
import com.safepolicy.service.PdfService;

import jakarta.websocket.server.PathParam;



@Controller
public class PolicyController {

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PersonalDetailRepository personalDetailRepository;
	
	 @Autowired
	 PdfService pdfService;
	
	@RequestMapping("/policyView")
	public String policyView( Model model, Principal principal, @RequestParam(defaultValue = "0") int page) {
		
		String username = principal.getName();
		
   		User currentUserData = userRepository.findByUsername(username);
   		
		 int pageSize = 10;
	     Pageable pageable = PageRequest.of(page, pageSize);
	     
	    Page<Policy> policyPage;

	    String isAdmin = currentUserData.getRole();

        if (isAdmin.equalsIgnoreCase("ADMIN")) {
            policyPage = policyRepository.findAll(pageable);
        } else {
            policyPage = policyRepository.findByUserId(currentUserData.getUserId(), pageable);
        }

	        model.addAttribute("policyPage", policyPage);
	        model.addAttribute("currentPage", page);
	
		
		return "policyView";
	}
	
	@GetMapping("/downloadPolicy")
    public ResponseEntity<ByteArrayResource> downloadPolicy(@RequestParam String quoteId) throws IOException {
		
		
       quoteId = "#"+quoteId;

		List<Policy> policy = policyRepository.findByQuoteId(quoteId);
        List<PersonalDetail> personalDetail = personalDetailRepository.findByQuoteId(quoteId);
     
        byte[] pdfBytes = pdfService.generatePolicyPdf(policy,personalDetail);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=policy.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }
}

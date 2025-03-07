package com.safepolicy.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safepolicy.dao.ConfigurationRepository;
import com.safepolicy.dao.IncomeDetailRepository;
import com.safepolicy.dao.ProductRepository;
import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.Configuration;
import com.safepolicy.model.IncomeDetail;
import com.safepolicy.model.Product;
import com.safepolicy.model.User;

@EnableMethodSecurity
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class IncomeDetailController {

    @Autowired
    IncomeDetailRepository incomeDetailRepository;

    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addIncomeDetail")
    public String addIncomeDetail(IncomeDetail incomeDetail, Model model, @RequestParam("quoteId") String quoteId) {
        // Check if an IncomeDetail with the given quoteId already exists
    	
    	
   IncomeDetail incomeDetailToUpdate = incomeDetailRepository.findByQuoteId(quoteId);

        if (incomeDetailToUpdate != null) {
            incomeDetailToUpdate.setSumAssured(incomeDetail.getSumAssured());
            incomeDetailToUpdate.setPolicyTerm(incomeDetail.getPolicyTerm());
            incomeDetailToUpdate.setPaymentFrequency(incomeDetail.getPaymentFrequency());
            incomeDetailToUpdate.setPayoutOption(incomeDetail.getPayoutOption());
            incomeDetailToUpdate.setPremiumPayingTerm(incomeDetail.getPremiumPayingTerm());
            incomeDetailToUpdate.setTotalPremium(incomeDetail.getTotalPremium());

            incomeDetailRepository.save(incomeDetailToUpdate);
            model.addAttribute("totalPremium", incomeDetailToUpdate.getTotalPremium());
        } else {
            // Save the new IncomeDetail
            IncomeDetail incomeDetailData = incomeDetailRepository.save(incomeDetail);
            model.addAttribute("totalPremium", incomeDetailData.getTotalPremium());
        }

        List<Configuration> configData = configurationRepository.findAll();
        List<Configuration> configFilterData = configData.stream()
                .filter(entry -> entry.getAddonName() != null && !entry.getAddonName().isEmpty()
                        && entry.getAddonValue() != null)
                .collect(Collectors.toList());

        model.addAttribute("ConfigurationData", configFilterData);
        model.addAttribute("quoteId", quoteId);

        return "addonBenefits";
    }

    // On back button of addonbenefits income data
    @GetMapping("/incomeBackData")
    public String addonBackButton(Model model, @RequestParam("quoteId") String quoteId) {

        IncomeDetail incomeData = incomeDetailRepository.findByQuoteId(quoteId);

        String policyTerm = null;
        long sumAssured = 0;
        String paymentFrequency = null;
        String payoutOption = null;
        Integer premiumPayingTerm = 0;
        long totalPremium = 0;


        if (incomeData != null) {
            sumAssured = incomeData.getSumAssured();
            policyTerm = incomeData.getPolicyTerm();
            paymentFrequency = incomeData.getPaymentFrequency();
            payoutOption = incomeData.getPayoutOption();
            premiumPayingTerm = incomeData.getPremiumPayingTerm();
            totalPremium = incomeData.getTotalPremium();
        }

        model.addAttribute("quoteId", quoteId);
        model.addAttribute("sumAssured", sumAssured);
        model.addAttribute("policyTerm", policyTerm);
        model.addAttribute("paymentFrequency", paymentFrequency);
        model.addAttribute("payoutOption", payoutOption);
        model.addAttribute("premiumPayingTerm", premiumPayingTerm);
        model.addAttribute("totalPremium", totalPremium);

        List<Product> productData = productRepository.findAll();
        model.addAttribute("productData", productData);

        return "incomeDetail";
    }

    @ModelAttribute
    public void foundUser(Principal principal, Model model) {
        String username = principal.getName();
        User currentUser = userRepository.findByUsername(username);
        model.addAttribute("currentUser", currentUser);
    }
}

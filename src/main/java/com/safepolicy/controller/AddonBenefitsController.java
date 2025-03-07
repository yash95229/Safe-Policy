package com.safepolicy.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safepolicy.dao.AddonBenefitsRepository;
import com.safepolicy.dao.ConfigurationRepository;
import com.safepolicy.dao.IncomeDetailRepository;
import com.safepolicy.dao.PersonalDetailRepository;
import com.safepolicy.dao.PolicyRepository;
import com.safepolicy.dao.ProductRepository;
import com.safepolicy.dao.UserRepository;
import com.safepolicy.model.AddonBenefits;
import com.safepolicy.model.Configuration;
import com.safepolicy.model.IncomeDetail;
import com.safepolicy.model.PersonalDetail;
import com.safepolicy.model.Policy;
import com.safepolicy.model.Product;
import com.safepolicy.model.User;
import com.safepolicy.util.GeneralUtil;

@EnableMethodSecurity
@Controller
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class AddonBenefitsController {

    @Autowired
    AddonBenefitsRepository addonBenefitsRepository;

    @Autowired
    PersonalDetailRepository personalDetailRepository;

    @Autowired
    IncomeDetailRepository incomeDetailRepository;

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfigurationRepository configurationRepository;

    @PostMapping("/addAddonBenefits")
    public String addAddonBenefits(AddonBenefits addonBenefits, @RequestParam("addonField") List<String> addonFields, Model model, @RequestParam("quoteId") String quoteId) {
        
        int addonField = addonFields.stream().map(Integer::parseInt).reduce(0, Integer::sum);
        String addFieldString = String.join(";", addonFields);
        
        addonBenefits.setAddonField(addFieldString);
        
        // Check if an AddonBenefits with the given quoteId already exists
        List<AddonBenefits> existingAddonBenefitsList = addonBenefitsRepository.findByQuoteId(quoteId);
        if (!existingAddonBenefitsList.isEmpty()) {
            AddonBenefits existingAddonBenefits = existingAddonBenefitsList.get(0);
            existingAddonBenefits.setAddonField(addFieldString);
            existingAddonBenefits.setFinalPremium(addonBenefits.getFinalPremium());
            addonBenefitsRepository.save(existingAddonBenefits);
            model.addAttribute("finalPremium", existingAddonBenefits.getFinalPremium());
        } else {
            AddonBenefits addonBenefitsData = addonBenefitsRepository.save(addonBenefits);
            model.addAttribute("finalPremium", addonBenefitsData.getFinalPremium());
        }

        // Get personal detail data
        List<PersonalDetail> personalDetailData = personalDetailRepository.findByQuoteId(quoteId);
        String fullName = "";
        String email = "";
        String panNumber = "";
        String number = "";
        PersonalDetail personalData = null;

        if (!personalDetailData.isEmpty()) {
            personalData = personalDetailData.get(0); 
            fullName = personalData.getFullName();
            email = personalData.getEmail();
            panNumber = personalData.getPanNumber();
            number = personalData.getNumber();
        }

        model.addAttribute("quoteId", quoteId);
        model.addAttribute("fullName", fullName);
        model.addAttribute("email", email);
        model.addAttribute("panNumber", panNumber);
        model.addAttribute("number", number);

        // Get income detail data
        IncomeDetail incomeData = incomeDetailRepository.findByQuoteId(quoteId);
        long sumAssured = 0;
        String policyTerm = null;

        if (incomeData != null) {
            sumAssured = incomeData.getSumAssured();
            policyTerm = incomeData.getPolicyTerm();
            policyTerm = policyTerm.replace("years", "").trim();
        }

        int policyTermYears = Integer.parseInt(policyTerm);

        // Get user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User currentUserData = userRepository.findByUsername(username);

        // Set policy data
        Date maturityDate = GeneralUtil.generateMaturityDate(new Date(), policyTermYears);
        maturityDate.setDate(maturityDate.getYear() + policyTermYears);

        Policy policy = new Policy();
        policy.setQuoteId(quoteId);
        policy.setApplicationNumber(GeneralUtil.generateApplicationNo());
        policy.setBookingId(GeneralUtil.generateBookingId());
        policy.setBookingDate(new Date());
        policy.setMaturityDate(maturityDate);
        policy.setPlanName("Life Insurance");
        policy.setPlanTerm(String.valueOf(policyTerm));
        policy.setFullName(fullName);
        policy.setSumAssured(sumAssured);
        policy.setTotalAddon(addonField);
        policy.setFinalPremium(addonBenefits.getFinalPremium());
        policy.setUserId(currentUserData.getUserId());

        policyRepository.save(policy);

        return "payment";
    }

    @GetMapping("/addonBackData")
    public String addonBackButton(Model model, @RequestParam("quoteId") String quoteId) {
        
        List<AddonBenefits> addonDetailData = addonBenefitsRepository.findByQuoteId(quoteId);
        String addonField = null;
        AddonBenefits addonData = null;

        if (!addonDetailData.isEmpty()) {
            addonData = addonDetailData.get(0); 
            addonField = addonData.getAddonField();
        }

        List<Configuration> configData = configurationRepository.findAll();
        List<Configuration> configFilterData = configData.stream()
            .filter(entry -> entry.getAddonName() != null && !entry.getAddonName().isEmpty()
                && entry.getAddonValue() != null)
            .collect(Collectors.toList());

        model.addAttribute("ConfigurationData", configFilterData);
        model.addAttribute("addonValue", addonField);
        model.addAttribute("quoteId", quoteId);

        return "addonBenefits";
    }
}

package com.safepolicy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IncomeDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String quoteId;
	private long sumAssured;
	private Integer premiumPayingTerm;
	private String paymentFrequency;
	private String policyTerm;
	private String payoutOption;
	private long totalPremium;
	private boolean condition;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public long getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}
	public Integer getPremiumPayingTerm() {
		return premiumPayingTerm;
	}
	public void setPremiumPayingTerm(Integer premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}
	public String getPaymentFrequency() {
		return paymentFrequency;
	}
	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}
	
	public String getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}
	public long getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(long totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getPayoutOption() {
		return payoutOption;
	}
	public void setPayoutOption(String payoutOption) {
		this.payoutOption = payoutOption;
	}
	public boolean isCondition() {
		return condition;
	}
	public void setCondition(boolean condition) {
		this.condition = condition;
	}
	
	
	
	

}

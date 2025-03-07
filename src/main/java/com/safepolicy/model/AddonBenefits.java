package com.safepolicy.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AddonBenefits {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String quoteId;
	private String addonField;
	private long finalPremium;
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
	
	
	public String getAddonField() {
		return addonField;
	}
	public void setAddonField(String addonField) {
		this.addonField = addonField;
	}
	public long getFinalPremium() {
		return finalPremium;
	}
	public void setFinalPremium(long finalPremium) {
		this.finalPremium = finalPremium;
	}
	public boolean isCondition() {
		return condition;
	}
	public void setCondition(boolean condition) {
		this.condition = condition;
	}
	
	
	
	
}

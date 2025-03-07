package com.safepolicy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Configuration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String annualIncome;
	private long sumAssured;
	private String addonName;
	private Integer addonValue;
	
	public String getAddonName() {
		return addonName;
	}
	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}
	public Integer getAddonValue() {
		return addonValue;
	}
	public void setAddonValue(Integer addonValue) {
		this.addonValue = addonValue;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public long getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}
	 public boolean getAddonValueAsString(String addon) {
		 if(addon != null) {
		        return addon.contains(addonValue.toString());
		 }else {
			 return false;
		 }
		
	    }

}

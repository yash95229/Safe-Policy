package com.safepolicy.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long policyId;
	private long userId;
	private String quoteId;
	private String applicationNumber;
	private Integer bookingId;
	private Date  bookingDate;
	private Date maturityDate;
	private String planName;
	private String planTerm;
	public String getPlanTerm() {
		return planTerm;
	}
	public void setPlanTerm(String planTerm) {
		this.planTerm = planTerm;
	}
	private String fullName;
	private long sumAssured;
	private long totalAddon;
	private long finalPremium;
	private String policyStatus;
	
	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public long getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}
	public long getTotalAddon() {
		return totalAddon;
	}
	public void setTotalAddon(long totalAddon) {
		this.totalAddon = totalAddon;
	}
	public long getFinalPremium() {
		return finalPremium;
	}
	public void setFinalPremium(long finalPremium) {
		this.finalPremium = finalPremium;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	

}

package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.basemodel.BaseModel;

@Entity
@Table(name = "FILE_IMPORT_REMITTANCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Remittance extends BaseModel implements Serializable{

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conduit_id")
	private Conduit conduitId;

	@Column(name = "reference_number")
	private String referenceNumber;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@Column(name = "beneficiary_address")
	private String beneficiaryAddress;

	@Column(name = "sent_amount")
	private BigDecimal sentAmount;

	@Column(name = "sent_currency")
	private Integer sentCurrency;

	@Column(name = "payout_amount")
	private BigDecimal payoutAmount;

	@Column(name = "payout_currency")
	private Integer payoutCurrency;

	@Column(name = "sender_name")
	private String senderName;

	@Column(name = "beneficiary_name")
	private String beneficiaryName;

	@Column(name = "transaction_type")
	private Integer transactionType;

	@Column(name = "status")
	private Integer status;

	public Conduit getConduitId() {
		return conduitId;
	}

	public void setConduitId(Conduit conduitId) {
		this.conduitId = conduitId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getBeneficiaryAddress() {
		return beneficiaryAddress;
	}

	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}

	public BigDecimal getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(BigDecimal sentAmount) {
		this.sentAmount = sentAmount;
	}

	public Integer getSentCurrency() {
		return sentCurrency;
	}

	public void setSentCurrency(Integer sentCurrency) {
		this.sentCurrency = sentCurrency;
	}

	public BigDecimal getPayoutAmount() {
		return payoutAmount;
	}

	public void setPayoutAmount(BigDecimal payoutAmount) {
		this.payoutAmount = payoutAmount;
	}

	public Integer getPayoutCurrency() {
		return payoutCurrency;
	}

	public void setPayoutCurrency(Integer payoutCurrency) {
		this.payoutCurrency = payoutCurrency;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

package com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.genpact.springboot.microservice.fileimport.springbootmicroservicefileimport.springbootmicroservicefileimportservice.model.basemodel.BaseModel;

@Entity
@Table(name = "FILE_IMPORT_CONDUIT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conduit extends BaseModel implements Serializable {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "code")
	private String code;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_balance")
	private BigDecimal accountBalance;

	@Column(name = "preferred_courier")
	private Long preferredCourier;

	@Column(name = "password")
	private String password;

	@Column(name = "pre_enrolled_tag")
	private Integer preEnrolledTag;

	@Column(name = "credit_limit")
	private BigDecimal creditLimit;

	@Column(name = "credit_limit_currency")
	private Integer creditLimitCurrency;

	@Column(name = "credit_amount")
	private BigDecimal creditAmount;

	@Column(name = "credit_amount_currency")
	private Integer creditAmountCurrency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Long getPreferredCourier() {
		return preferredCourier;
	}

	public void setPreferredCourier(Long preferredCourier) {
		this.preferredCourier = preferredCourier;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPreEnrolledTag() {
		return preEnrolledTag;
	}

	public void setPreEnrolledTag(Integer preEnrolledTag) {
		this.preEnrolledTag = preEnrolledTag;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Integer getCreditLimitCurrency() {
		return creditLimitCurrency;
	}

	public void setCreditLimitCurrency(Integer creditLimitCurrency) {
		this.creditLimitCurrency = creditLimitCurrency;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Integer getCreditAmountCurrency() {
		return creditAmountCurrency;
	}

	public void setCreditAmountCurrency(Integer creditAmountCurrency) {
		this.creditAmountCurrency = creditAmountCurrency;
	}

}

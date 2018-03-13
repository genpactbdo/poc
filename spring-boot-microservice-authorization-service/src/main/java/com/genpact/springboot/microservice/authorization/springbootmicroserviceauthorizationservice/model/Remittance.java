package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Remittance {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 
    private String status;
    private String conduit;
    private Date date;
    private String transactionType;
    private String productType;
    private String refNo;
    private String remitterCode;
    private String remitterName;
    private String remitterGender;
    private String civilStatus;
    private Date dateOfBirth;
    private String address1;
    private String address2;
    private String cityTown;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String telNo;
    private String beneficiaryCode;
    private String beneficiaryName;
    private String beneficiaryGender;
    private String beneficiaryCivilStatus;
    private Date beneficiaryDateOfBirth;
    private String beneficiaryBank;
    private String beneficiaryBankAccountNo;
    private String beneficiaryBankAccountCurrency;
    private String beneficiaryBankBranch;
    private String sourceCurrency;
    private Double sourceAmount;
    private String beneficiaryAddress1;
    private String beneficiaryAddress2;
    private String beneficiaryCityTown;
    private String beneficiaryStateProvince;
    private String beneficiaryPostalCode;
    private String beneficiaryCountry;
    
    public Remittance() {
	}
    
	public Remittance(String conduit) {
		this.conduit = conduit;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConduit() {
		return conduit;
	}
	public void setConduit(String conduit) {
		this.conduit = conduit;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getRemitterCode() {
		return remitterCode;
	}
	public void setRemitterCode(String remitterCode) {
		this.remitterCode = remitterCode;
	}
	public String getRemitterName() {
		return remitterName;
	}
	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}
	public String getRemitterGender() {
		return remitterGender;
	}
	public void setRemitterGender(String remitterGender) {
		this.remitterGender = remitterGender;
	}
	public String getCivilStatus() {
		return civilStatus;
	}
	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCityTown() {
		return cityTown;
	}
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}
	public void setBeneficiaryCode(String beneficiaryCode) {
		this.beneficiaryCode = beneficiaryCode;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneficiaryGender() {
		return beneficiaryGender;
	}
	public void setBeneficiaryGender(String beneficiaryGender) {
		this.beneficiaryGender = beneficiaryGender;
	}
	public String getBeneficiaryCivilStatus() {
		return beneficiaryCivilStatus;
	}
	public void setBeneficiaryCivilStatus(String beneficiaryCivilStatus) {
		this.beneficiaryCivilStatus = beneficiaryCivilStatus;
	}
	public Date getBeneficiaryDateOfBirth() {
		return beneficiaryDateOfBirth;
	}
	public void setBeneficiaryDateOfBirth(Date beneficiaryDateOfBirth) {
		this.beneficiaryDateOfBirth = beneficiaryDateOfBirth;
	}
	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}
	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}
	public String getBeneficiaryBankAccountNo() {
		return beneficiaryBankAccountNo;
	}
	public void setBeneficiaryBankAccountNo(String beneficiaryBankAccountNo) {
		this.beneficiaryBankAccountNo = beneficiaryBankAccountNo;
	}
	public String getBeneficiaryBankAccountCurrency() {
		return beneficiaryBankAccountCurrency;
	}
	public void setBeneficiaryBankAccountCurrency(String beneficiaryBankAccountCurrency) {
		this.beneficiaryBankAccountCurrency = beneficiaryBankAccountCurrency;
	}
	public String getBeneficiaryBankBranch() {
		return beneficiaryBankBranch;
	}
	public void setBeneficiaryBankBranch(String beneficiaryBankBranch) {
		this.beneficiaryBankBranch = beneficiaryBankBranch;
	}
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public Double getSourceAmount() {
		return sourceAmount;
	}
	public void setSourceAmount(Double sourceAmount) {
		this.sourceAmount = sourceAmount;
	}
	public String getBeneficiaryAddress1() {
		return beneficiaryAddress1;
	}
	public void setBeneficiaryAddress1(String beneficiaryAddress1) {
		this.beneficiaryAddress1 = beneficiaryAddress1;
	}
	public String getBeneficiaryAddress2() {
		return beneficiaryAddress2;
	}
	public void setBeneficiaryAddress2(String beneficiaryAddress2) {
		this.beneficiaryAddress2 = beneficiaryAddress2;
	}
	public String getBeneficiaryCityTown() {
		return beneficiaryCityTown;
	}
	public void setBeneficiaryCityTown(String beneficiaryCityTown) {
		this.beneficiaryCityTown = beneficiaryCityTown;
	}
	public String getBeneficiaryStateProvince() {
		return beneficiaryStateProvince;
	}
	public void setBeneficiaryStateProvince(String beneficiaryStateProvince) {
		this.beneficiaryStateProvince = beneficiaryStateProvince;
	}
	public String getBeneficiaryPostalCode() {
		return beneficiaryPostalCode;
	}
	public void setBeneficiaryPostalCode(String beneficiaryPostalCode) {
		this.beneficiaryPostalCode = beneficiaryPostalCode;
	}
	public String getBeneficiaryCountry() {
		return beneficiaryCountry;
	}
	public void setBeneficiaryCountry(String beneficiaryCountry) {
		this.beneficiaryCountry = beneficiaryCountry;
	}
    
}
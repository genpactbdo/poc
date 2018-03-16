package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice;

import java.util.ArrayList;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Remittance;

public class RemittanceForm {

    private ArrayList<Remittance> remittances;
    private Double authorizedAmount;
    private String[] idSelectedAmountEntries;
    private String conduitName;
    
	public ArrayList<Remittance> getRemittances() {
		return remittances;
	}
	public void setRemittances(ArrayList<Remittance> remittances) {
		this.remittances = remittances;
	}
	public Double getAuthorizedAmount() {
		return authorizedAmount;
	}
	public void setAuthorizedAmount(Double authorizedAmount) {
		this.authorizedAmount = authorizedAmount;
	}
	public String[] getIdSelectedAmountEntries() {
		return idSelectedAmountEntries;
	}
	public void setIdSelectedAmountEntries(String[] idSelectedAmountEntries) {
		this.idSelectedAmountEntries = idSelectedAmountEntries;
	}
	public String getConduitName() {
		return conduitName;
	}
	public void setConduitName(String conduitName) {
		this.conduitName = conduitName;
	}
	
}
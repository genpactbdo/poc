package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice;

import java.util.ArrayList;
import java.util.List;

import com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model.Conduit;

public class ConduitForm {

    private List<Conduit> conduits;
    private long[] idSelectedConduits;
    
	public List<Conduit> getConduits() {
		return conduits;
	}
	public void setConduits(ArrayList<Conduit> conduits) {
		this.conduits = conduits;
	}
	public long[] getIdSelectedConduits() {
		return idSelectedConduits;
	}
	public void setIdSelectedConduits(long[] idSelectedConduits) {
		this.idSelectedConduits = idSelectedConduits;
	}
    
}
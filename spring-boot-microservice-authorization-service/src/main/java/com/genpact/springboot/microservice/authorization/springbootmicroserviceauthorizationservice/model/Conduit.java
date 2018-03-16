package com.genpact.springboot.microservice.authorization.springbootmicroserviceauthorizationservice.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Conduit {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    private String conduitName;
    private double balance;
    private double creditLimit;
    private double totalRemit;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getConduitName() {
		return conduitName;
	}
	public void setConduitName(String conduitName) {
		this.conduitName = conduitName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public double getTotalRemit() {
		return totalRemit;
	}
	public void setTotalRemit(double totalRemit) {
		this.totalRemit = totalRemit;
	}
    
}
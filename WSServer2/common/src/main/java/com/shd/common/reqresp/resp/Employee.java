package com.shd.common.reqresp.resp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class Employee {
	
	
	private List<String> pstr = Arrays.asList("A", "B", "C","V", "Y", "X", "Z","D", "K","M","L") ;
	
	private int employeeId; 
	private String firstName ;
    private String lastName ;
    private  BigDecimal annualSalaryl ;
    private int yrsNoOfExperience ;  
    private String organisationName; 
    private int  retPInx = -1 ;
    private int adjInt = -1 ;
    private int recNum = 0 ;
    private Integer runNo = null ;
    
    public Employee generateNewEmployee(int i)
    {
    	
    	Employee emp = new Employee() ;
    	emp.employeeId = i + 100 ;
    	emp.setRecNum(i) ;
    	emp.firstName="First-Name" + retNextPstr() ;
    	emp.lastName="Last-Name-Name" + retNextPstr() ;
    	emp.setSal(new BigDecimal( (1000*i*2)/(retPInx+1*3) + 800.5) );  
    	emp.retPInx = retPInx ;
    	emp.organisationName="Organisation" +pstr.get(retPInx) ;
    	emp.setYrsNoOfExperience((retPInx*5)+ adjInt);
    	adjInt = adjInt*(-1) ;
    	return emp ;
    }
    public Integer getRunNo() {
		return runNo;
	}
    private String retNextPstr()
    {
    	++retPInx ;
    	if ( retPInx == pstr.size() )retPInx= 0 ;
    	return pstr.get(retPInx) ;
    }
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getSal() {
	    annualSalaryl.setScale(2, RoundingMode.HALF_UP);
	    return annualSalaryl ;
	}
	public void setSal(BigDecimal sali) {
		this.annualSalaryl = sali;
	}
	public int getYrsNoOfExperience() {
		return yrsNoOfExperience;
	}
	public void setYrsNoOfExperience(int yrsNoOfExperiencei) {
		this.yrsNoOfExperience = yrsNoOfExperiencei;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public int getRecNum() {
		return recNum;
	}
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", sal="
				+ getSal() + ", yrsNoOfExperience=" + yrsNoOfExperience + ", organisationName=" + getOrganisationName() + ", RecNum =" + getRecNum() +"]";
	}
	
	

	
}

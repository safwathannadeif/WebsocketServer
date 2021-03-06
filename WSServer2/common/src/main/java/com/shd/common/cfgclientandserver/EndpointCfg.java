package com.shd.common.cfgclientandserver;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EndpointCfg {
	private String endpointClass 	;
	private String path4EndpointClass  ;
	private Integer clientNumOfPersistenceConnections ; 
	private String comment ;
	
	public EndpointCfg()
	{
		
	}
	public EndpointCfg(String endpointClass, String path4endpointClass,Integer clientNumOfPersistenceConnections, String comment) {
		this.endpointClass = endpointClass;
		this.path4EndpointClass = path4endpointClass;
		this.clientNumOfPersistenceConnections = clientNumOfPersistenceConnections ;
		this.comment = comment;
	}
	public String getEndpointClass() {
		return endpointClass;
	}
	public void setEndpointClass(String endpointClass) {
		this.endpointClass = endpointClass;
	}
	public String getPath4endpointClass() {
		return path4EndpointClass;
	}
	public void setPath4endpointClass(String path4endpointClass) {
		this.path4EndpointClass = path4endpointClass;
	}
	public Integer getClientNumOfPersistenceConnections() {
		return clientNumOfPersistenceConnections;
	}
	public void setClientNumOfPersistenceConnections(Integer clientNumOfPersistenceConnections) {
		this.clientNumOfPersistenceConnections = clientNumOfPersistenceConnections;
	}
	
	
	
}

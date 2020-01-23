package com.shd.common.cfgclientandserver;

import java.util.ArrayList;
import java.util.List;

public class ConfigServerEndpoints {
	private List<EndpointCfg> endPoints =new ArrayList<EndpointCfg>(); 
	private String hostIp ="" ;
	private Integer port = -1 ;
	private String rootPath = null ;
	public List<EndpointCfg> getEndPoints() {
		return endPoints;
	}
	public void setEndPoints(List<EndpointCfg> endPoints) {
		this.endPoints = endPoints;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public void addEndPoint(EndpointCfg endpointCfg)
	{
		endPoints.add(endpointCfg) ;	
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
}

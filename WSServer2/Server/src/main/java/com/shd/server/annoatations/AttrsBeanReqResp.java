package com.shd.server.annoatations;

import java.lang.reflect.Method;
import java.util.Optional;


public class AttrsBeanReqResp {

	private Class<?> reqRespClass;  //Class Contains the processReqResp Method and shoul be  annotated by AWebSocket.class
	private Class<?> inpParmClass; 	//for  injectMethod;
	private Class<?> retOutClass; 	//for extractMethod; 
	private  Method injectAndExtractMethod ;
//	public class  EmptyClass {
//		  
//	  }
//	public Class<?> EmptyClass  ;
	
	public AttrsBeanReqResp() {
	}

	public Class<?> getReqRespClass() {
		return reqRespClass;
	}
	public Class<?> getInpParmClass() {
		//inpParmClass = Optional.ofNullable(inpParmClass).orElse(inpParmClass = Class<null>) ;
		return inpParmClass;
	}
	public Class<?> getRetOutClass() {
		return retOutClass;
	}
	public void setReqRespClass(Class<?> reqRespClass) {
		this.reqRespClass = reqRespClass;
	}
	public void setInpParmClass(Class<?> inpParmClass) {
		
		
		this.inpParmClass = inpParmClass;
	}

	public void setRetOutClass(Class<?> retOutClass) {
		this.retOutClass = retOutClass;
	}
	public Method getInjectAndExtractMethod() {
		return injectAndExtractMethod;
	}
	public void setInjectAndExtractMethod(Method injectMethodAndExtractMethod) {
		this.injectAndExtractMethod = injectMethodAndExtractMethod;
	}
	@Override
	public String toString() {
		return "AttrsBeanReqResp [reqRespClass=" + reqRespClass.getName() + ", injectAndExtractMethod=" + injectAndExtractMethod.getName() + 
				  ", inpParmClass=" + getOptionalClassName(inpParmClass)   + ", retOutClass=" + getOptionalClassName(retOutClass)  + "]";
	}
  public String getOptionalClassName(Class<?> cls)
  {
	 String name  = Optional.ofNullable(cls).map(cl -> cl.getName()).orElse("");		 
	 return name ;
	 
  }
  
  
	
}

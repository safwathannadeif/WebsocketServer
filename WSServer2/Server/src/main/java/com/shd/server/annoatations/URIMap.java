package com.shd.server.annoatations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.shd.server.Ref2Singleton;
public class URIMap {
	private  final Map<String, AttrsBeanReqResp> storeClassesForURI = new HashMap<String, AttrsBeanReqResp>();
	private Object reqRespObj = null;
	private String uriI = null;
	public String getUri() {
		return uriI;
	}
	public Object getReqRespObj() {
		return reqRespObj;
	}
	public  void addReqRespAnnotted(String uri, AttrsBeanReqResp a)  {
		//assert getAttrsBeanReqResp(uri)== null : "Duplicated value for URI=" + uri ;
		storeClassesForURI.put(uri, a);
	}
	public  void printURIMapToReqRespAnnoted() {
		// Use Java 8 -Stream.forEach()
		// storeClassesForURI.values().stream().forEach(System.out::println);
		storeClassesForURI.forEach((key, value) -> {
			Ref2Singleton.ONEINSREF.getServLogger().info("Key : " + key + "\nValue : " + value +"\n");
		});
	}

	public  Object injectReq(String url, String Req) throws NoSuchMethodException, SecurityException {
		AttrsBeanReqResp aa = storeClassesForURI.get(url);
		Object oDefineTheMethod = null;
		try {

			Method m = aa.getInjectAndExtractMethod();
			// Object oReqParm = m.getParameterTypes()[0].getConstructor().newInstance() ;
			oDefineTheMethod = aa.getReqRespClass().getConstructor().newInstance();
			m.invoke(oDefineTheMethod, Req);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ex) {
			Ref2Singleton.ONEINSREF.getServLogger().severe(Ref2Singleton.ONEINSREF.expToStr(ex) );
		}
		return oDefineTheMethod;
	}

	public  <Request, ReqResClss> void injectReqGen(String url, ReqResClss reqResObj, Request req) {
		AttrsBeanReqResp attr = storeClassesForURI.get(url);

		try {

			Method mInReqResClss = attr.getInjectAndExtractMethod() ;
			mInReqResClss.invoke(reqResObj, req);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			Ref2Singleton.ONEINSREF.getServLogger().severe(Ref2Singleton.ONEINSREF.expToStr(ex) );
		}
	}

	//
	public <Request> void injectReqGen2(String uriReqResp, Request reqObj)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		AttrsBeanReqResp attr = storeClassesForURI.get(uriReqResp);

		if (null == attr)
			throw new IllegalAccessException("attr for ReqRespont not founf fpr url=" + uriReqResp);
		uriI = uriReqResp;
		Method mInReqResClss = attr.getInjectAndExtractMethod() ;
		Class<?> classReqResp = attr.getReqRespClass();
		Object reqRespObj = classReqResp.getConstructor().newInstance();
		mInReqResClss.invoke(reqRespObj, reqObj); // injectReqResp with input Request -- setRequest

	}

	//
	public Object extractResponse2()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return reqRespObj;
	}

	//
	public  AttrsBeanReqResp getAttrsBeanReqResp(String urlRequest) throws IllegalAccessException {
		AttrsBeanReqResp attr = storeClassesForURI.get(urlRequest);
		if (null == attr)
			throw new IllegalAccessException("attr for ReqResponse not found fpr uri=" + urlRequest);
		return attr;
	}

	public  AttrsBeanReqResp chkAttrsBeanReqResp(String uriRequest) 
	{
		AttrsBeanReqResp attr = storeClassesForURI.get(uriRequest);
		return attr ;
	}
}	

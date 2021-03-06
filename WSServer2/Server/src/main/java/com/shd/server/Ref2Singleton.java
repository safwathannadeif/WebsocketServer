package com.shd.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.shd.common.gen.rwfiles.WriteReqRespJsonMsgs;
import com.shd.server.annoatations.URIMap;
import com.shd.server.logging.LoggerRef;


// Singleton Ref 
public enum Ref2Singleton {
	ONEINSREF ;
	private   AtomicInteger atomicRunNo = new AtomicInteger(1);
	private   Logger servLogDisply = null ;
	
	public  Logger getServLogger() 
	{
		if ( servLogDisply == null ) servLogDisply = LoggerRef.makeLogRef() ;
		Objects.requireNonNull(servLogDisply, "NULL!! for displog" ) ;
		return servLogDisply;
	}
	private Map<String, String> propertyHashMap = new HashMap<>();
	private WriteReqRespJsonMsgs writeReqRespJsonMsgsSingleref = null ;
	MessageDigest saltMD = null ;
	//
	public String expToStr(Exception ex)
	{
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	ex.printStackTrace(pw);
	return sw.toString() ;
	}
	//
	ResourceBundle rb = null ; 
	private URIMap uRIMap = new URIMap() ;
	public  URIMap getURIMap()
	{
		return uRIMap ;
	}
	//
	
	private   ExecutorService execService = null ;				//= Executors.newFixedThreadPool(35); //pool for thread ExecutorServiceNoOfThreads
	public    ExecutorService  getExecService()
	{
		if ( execService == null)
			{
			int noOfThreads = Integer.valueOf(getPropertyHashMap().get("executorServiceNoOfThreads").trim()) ;
			execService =Executors.newFixedThreadPool(noOfThreads) ;
			}
		
		return execService ;
	}
	/*
	 * Possible:: UUID (Universally Unique Identifier),
	 *  also known as GUID (Globally Unique Identifier) represents a 128-bit long value 
	 *  that is unique for all practical purposes. 
	 *  The standard representation of the UUID uses hex digits (octals):
	 */
	public  String  getPossibleUnique () throws NoSuchAlgorithmException, UnsupportedEncodingException {
	   // return UUID.nameUUIDFromBytes(str.getBytes(StandardCharsets.UTF_8));
       if ( null == saltMD)saltMD = getMDSalt() ;
		getMDSalt().update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        String uinqStr = bytesToHex(saltMD.digest());
        return uinqStr ;
    }
	private MessageDigest getMDSalt() throws NoSuchAlgorithmException
	{
		saltMD = MessageDigest.getInstance("SHA-256") ;
		return saltMD ;
	}
	//
	private  String bytesToHex(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	public Integer getRunNo() {
		return atomicRunNo.incrementAndGet();
	}
	
	
	public Map<String, String> getPropertyHashMap() {
		return propertyHashMap;
	}
	public void setPropertyHashMap(Map<String, String> propertyHashMap) {
		this.propertyHashMap = propertyHashMap;
	}
	public  void MakeSingleRefToWriteReqResp() 
	{
		writeReqRespJsonMsgsSingleref= new WriteReqRespJsonMsgs() ;
		
	}
	public  WriteReqRespJsonMsgs getTheSingleRefToWriteReqResp() 
	{
		return writeReqRespJsonMsgsSingleref ;
		
	}
	public Function<Map<String,String> , String> displMap = (map)  -> {
		String dispProps = map.entrySet() 
				.stream()
				.map(e -> e.getKey() + "=\"" + e.getValue() + "\"" )
				.collect(Collectors.joining("\n"));	
		return dispProps ;
	};
    
}
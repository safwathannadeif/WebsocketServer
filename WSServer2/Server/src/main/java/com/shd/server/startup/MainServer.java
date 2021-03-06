package com.shd.server.startup;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

import com.google.gson.Gson;
import com.shd.common.cfgclientandserver.ConfigServerEndpoints;
import com.shd.common.cfgclientandserver.EndpointCfg;
import com.shd.common.properties.ReadAndCaptureProps;
import com.shd.server.Ref2Singleton;
public class MainServer {
public static void main(String[] args) throws  IOException, InterruptedException, DeploymentException, NoSuchAlgorithmException, ClassNotFoundException {
	
	//-DServerPropsDir="C:/WSServer2/WSServer2/Server/src/main/resources" where VM argument is: -DServerPropsDir="C:/WSServer2/WSServer2/Server/src/main/resources"
	
	/**********??****** */
	ConfigServerEndpoints cfgEndPoint = config() ;
	/**********??****** */
	//********** makeFiles4ReqResp *********
	//Server server1 = new Server("localhost", 8080, "", null, ServerEndPt1.class);
	
	String host = cfgEndPoint.getHostIp().trim();
	int port=cfgEndPoint.getPort() ; 
	String rootPath=cfgEndPoint.getRootPath().trim();
//	host="localhost" ;
//	port=8080 ;
//	rootPath="/serverPath" ;
	Server server1 = new Server(host, port, rootPath, null, ServerApplicationConfigImp.class);
	ScanAndCapAnnotation scanAndCapAnnotation = new ScanAndCapAnnotation() ;
	scanAndCapAnnotation.scanAnot();
	scanAndCapAnnotation.capAnot() ;
	Ref2Singleton.ONEINSREF.getURIMap().printURIMapToReqRespAnnoted();
	//URIMap.printReqRespAnnoted();
	server1.start();
	Ref2Singleton.ONEINSREF.getServLogger().info("Server Started ...") ;
	new CountDownLatch(1).await();  //Wait and Keep Server Up
}

public static ConfigServerEndpoints config() throws IOException, ClassNotFoundException
{
//*********** Property ********
	String serverPropsDir =System.getProperty("ServerPropsDir");
	String propFile =  "ServerGen.properties" ;
	System.out.println("PropFileDirectory=[" + serverPropsDir +"]") ;
	System.out.println("propFile=[" + propFile +"]") ;
	ReadAndCaptureProps readAndCaptureProps = new ReadAndCaptureProps () ; 
	String propStrToDisply = readAndCaptureProps.readAndUpdProprsi1.andThen(
	Ref2Singleton.ONEINSREF.displMap).apply(serverPropsDir,propFile);
	Ref2Singleton.ONEINSREF.setPropertyHashMap(readAndCaptureProps.getHashMap());
	System.out.println("******* Print Prop Captured :\n" + propStrToDisply +"\n******************") ; 
	readAndCaptureProps = null ;
//*********** Property ********
//********** MakeFiles4ReqResp *********
	String flagPrintReqRespCfg =Ref2Singleton.ONEINSREF.getPropertyHashMap().get("makeFiles4ReqResp").trim() ; //True or False
	if (flagPrintReqRespCfg.equals("True")) {
	String headerdircToUse =Ref2Singleton.ONEINSREF.getPropertyHashMap().get("serverHeaderDirectory4ReqRespMsgs").trim() ; //True or False
	
	Ref2Singleton.ONEINSREF.MakeSingleRefToWriteReqResp();
	Ref2Singleton.ONEINSREF.getTheSingleRefToWriteReqResp().setInpHeader(headerdircToUse);
	Ref2Singleton.ONEINSREF.getTheSingleRefToWriteReqResp().setDirPrefix("ReqResp_") ;
	
	String cleanFiles4ReqRespBeforeStart =Ref2Singleton.ONEINSREF.getPropertyHashMap().get("cleanFiles4ReqRespBeforeStart").trim() ; //True or False
	if (cleanFiles4ReqRespBeforeStart.equals("True"))Ref2Singleton.ONEINSREF.getTheSingleRefToWriteReqResp().cleanBeforeStart() ;
	
	Ref2Singleton.ONEINSREF.getTheSingleRefToWriteReqResp().createNewDirs();		
	}
//********** MakeFiles4ReqResp *********
//********** Config Server End Points *********	
	ServerApplicationConfigImp serverApplicationConfigImp = new ServerApplicationConfigImp() ;
	ConfigServerEndpoints cfgEndPoint = serverApplicationConfigImp.getCfg();
	serverApplicationConfigImp.makeServerEndpointConfig(cfgEndPoint) ;
	return cfgEndPoint ;
//********** Config Server End Points *********	
	
	
}		   
}

/* run Server from Command line:
 * -1 cd C:\WSServer2\WSServer2\Server\target -2 java 	 * -DServerPropsDir="C:/WebSockt_WrkDirec/Server/Properties" -jar Server-1.0-jar-with-dependencies.jar 
 * Not: the Property file[ServerGen.propertie] for Server located in  C:\WebSockt_WrkDirec\Server\Properties
 */
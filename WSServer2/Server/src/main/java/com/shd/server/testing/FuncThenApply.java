package com.shd.server.testing;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shd.common.cfgclientandserver.ConfigServerEndpoints;
import com.shd.common.cfgclientandserver.EndpointCfg;
import com.shd.server.startup.ServerApplicationConfigImp;
 /*
  * 
  */
public class FuncThenApply 
{
	public interface FunctionX1  
	{	
		Map<String, String> apply1(String str1, String str2); 
	} ;
	//
	@FunctionalInterface
	public interface FunctionX2  
	{	
		String apply2(Map<String, String> map ); 
	} ;
	public static void main(String[] args)
	  {
//		ConfigServerEndpoints  configServerEndpoints = new ConfigServerEndpoints() ;
//		configServerEndpoints.setPort(800);
//		configServerEndpoints.setHostIp("localhost") ;
//			EndpointCfg ret = new EndpointCfg() ;
//			EndpointCfg configServerEndpointi = new EndpointCfg("Class1","Path1",15,"Comment1") ;
//			configServerEndpoints.addEndPoint(configServerEndpointi) ;
//			configServerEndpointi = new EndpointCfg("Class2","Path2",20,"Comment2") ;
//			configServerEndpoints.addEndPoint(configServerEndpointi) ;
//			configServerEndpointi = new EndpointCfg("Class3","Path3",11,"Comment3") ;
//			configServerEndpoints.addEndPoint(configServerEndpointi) ;
//			
//			Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
//			String pStr = gsonPretty.toJson(configServerEndpoints) ;
//			System.out.println(pStr) ;
			ServerApplicationConfigImp serverApplicationConfigImp = new ServerApplicationConfigImp() ;
			serverApplicationConfigImp.getCfg(); 
		Function<Integer,String> functionSqrt = n -> String.valueOf(Math.sqrt(n));
	      Function<String,BigDecimal> functionMultiByItSelf = s -> BigDecimal.valueOf((Float.valueOf(s)*Float.valueOf(s))) ;
	        
	        
	        BigDecimal result=functionSqrt.andThen(functionMultiByItSelf).apply(900);
	        System.out.println("Result with andThen: "+result);
	        
	  
	            Function<Integer,String> converter = (i)-> Integer.toString(i);
	            
	            Function<String, Integer> reverse = (s)-> Integer.parseInt(s);
	           
	            System.out.println(converter.apply(3).length());
	            System.out.println(converter.compose(reverse).apply("30").length());
	            
	           ////
	            String serverPropsDir =System.getProperty("ServerPropsDir");
	        	String propFile =  "ServerGen.properties" ;
	        	
	        	
	        	BiFunction<String,String,Map<String, String>>  readAndUpdProprsi = (Str1,Str2) -> 
	            { 
	            	Path filePath = Paths.get(Str1, Str2 );
	            
	            	Map<String, String> hashMap = new HashMap<>();
	                System.out.println("PropFile=[" +filePath.toString() +"]") ;
	                 try {
	        			Files.lines(filePath)
	        			 .filter(str-> !str.startsWith("#"))
	        			.forEach(line -> {
	        				String[] str2 = line.split("=") ;
	        				 hashMap.put(str2[0].trim(), str2[1].trim()) ;
	        				 
	        			}	
	        			);
	        		} catch (IOException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		} 
	        return hashMap ;
	            	
	            } ;

	        //
	            Function<Map<String,String> , String> dsplpropi = (map)  -> {
	        	String dispProps = map.entrySet() 
	        			         .stream()
	        			         .map(e -> e.getKey() + "=\"" + e.getValue() + "\"" )
	        			         .collect(Collectors.joining("\n"));	
	        	return dispProps ;
	        	
	        	
	        }  ;
	            
	          ////
	        readAndUpdProprsi.andThen(dsplpropi).apply(serverPropsDir,propFile);
	    //    readAndUpdProprsi.a
	        
	            
	  }
}
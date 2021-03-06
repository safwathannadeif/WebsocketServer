package com.shd.common.properties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


import com.google.gson.Gson;
public class ReadAndCaptureProps {
	//	@FunctionalInterface
	//	public interface Function1  
	//	{	
	//		Map<String, String> apply1(String str1, String str2); 
	//	} ;
	//	//
	//	@FunctionalInterface
	//	public interface Function2  
	//	{	
	//		String apply2(Map<String, String> map ); 
	//	} ;
	//	
	//public	Function1 readAndUpdProprsi = (propsDir, propFileName) -> 
	//    { 
	//    	Path filePath = Paths.get(propsDir.trim(), propFileName.trim());
	//    	Map<String, String> hashMap = new HashMap<>();
	//        System.out.println("PropFile=[" +filePath.toString() +"]") ;
	//         try {
	//			Files.lines(filePath)
	//			 .filter(str-> !str.startsWith("#"))
	//			.forEach(line -> {
	//				String[] str2 = line.split("=") ;
	//				 hashMap.put(str2[0].trim(), str2[1].trim()) ;
	//				 
	//			}	
	//			);
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} 
	//return hashMap ;
	//    	
	//    } ;
	//
	////
	//public Function2 displpropi = (map)  -> {
	//	String dispProps = map.entrySet() 
	//			         .stream()
	//			         .map(e -> e.getKey() + "=\"" + e.getValue() + "\"" )
	//			         .collect(Collectors.joining("\n"));	
	//	return dispProps ;
	//	
	//	
	//}  ;
	/*
	 * -1 cd C:\WSServer2\WSServer2\Server\target -2 java 	 * -DServerPropsDir="C:/WebSockt_WrkDirec/Server/Properties" -jar Server-1.0-jar-with-dependencies.jar 
	 * Not: the Property file[ServerGen.propertie] for Server located in  C:\WebSockt_WrkDirec\Server\Properties
	 */
	private Map<String, String> hashMap = new HashMap<>(); 
	public BiFunction<String,String,Map<String, String>>  readAndUpdProprsi1 = (Str1,Str2) -> 
	{ 
		Path filePath = Paths.get(Str1, Str2 );
		try {
			Files.lines(filePath)
			.filter(str-> !str.startsWith("#"))
			.forEach(line -> {
				String[] str2 = line.split("=") ;
				hashMap.put(str2[0].trim(), str2[1].trim()) ;
			}	
					);
		} catch (IOException e2) {
			throw new RuntimeException(e2) ;
		} 

		return  hashMap ;
	} ;
	//
//	public Function<Map<String,String> , String> displMap = (map)  -> {
//		String dispProps = map.entrySet() 
//				.stream()
//				.map(e -> e.getKey() + "=\"" + e.getValue() + "\"" )
//				.collect(Collectors.joining("\n"));	
//		return dispProps ;
//	};

	public Map<String, String> getHashMap() {
		return hashMap;
	}
public String readServJsonCfg(String strFilePath)
{
	
	String json = null ;
	Path filePath = Paths.get(strFilePath );
	try {
		json = Files.lines(filePath)
				.filter(line-> 	!line.trim().startsWith("#"))
				.collect(Collectors.joining("\n"));	
		
	}	
	catch (IOException e2) {
		throw new RuntimeException(e2) ;
	} 

return json ;
}
}


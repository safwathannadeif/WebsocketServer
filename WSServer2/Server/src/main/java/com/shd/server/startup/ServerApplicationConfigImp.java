/*  javax.websocket.server Docs:
     * Return a set of ServerEndpointConfig instances that the server container
     * will use to deploy the programmatic endpoints. The set of Endpoint classes passed in to this method is
     * the set obtained by scanning the archive containing the implementation
     * of this ServerApplicationConfig. This set passed in
     * may be used the build the set of ServerEndpointConfig instances
     * to return to the container for deployment.
     *
     * @param endpointClasses the set of all the Endpoint classes in the archive containing
     *                the implementation of this interface.
     * @return the non-null set of ServerEndpointConfig s to deploy on the server, using the empty set to
     * indicate none.
*/
package com.shd.server.startup;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import com.google.gson.Gson;
import com.shd.common.cfgclientandserver.ConfigServerEndpoints;
import com.shd.common.cfgclientandserver.EndpointCfg;
import com.shd.common.properties.ReadAndCaptureProps;
import com.shd.server.Ref2Singleton;


public class ServerApplicationConfigImp implements ServerApplicationConfig { 
	//private  Set<ServerEndpointConfig> servEndReady = null ; // = new HashSet<>();
	@Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses)   {
		ConfigServerEndpoints cfgEndPoint = getCfg();
		Set<ServerEndpointConfig> servEndReady =makeServerEndpointConfig(cfgEndPoint) ;
        Ref2Singleton.ONEINSREF.getServLogger().info("New ServerApplicationConfigImp is Completed") ;  
        
        return servEndReady ;
    }
	
    
	
	public Set<ServerEndpointConfig> makeServerEndpointConfig(ConfigServerEndpoints endPointsToConfgure) 
	{
		Set<ServerEndpointConfig> servEndReady2  = new HashSet<>();
		List<EndpointCfg> lisOfEndCfg = endPointsToConfgure.getEndPoints() ;
		ServerEndpointConfig servCfgedEndPt = null ;
		for (EndpointCfg endPt : lisOfEndCfg) {
			String endPath = endPt.getPath4endpointClass().trim() ;
			String className = endPt.getEndpointClass().trim();
			Class<?> claz = null ;
			try {
				claz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("NewXXXXX cfg point endPath  claz [" + endPath +"] [" + claz.getCanonicalName() +"]") ;
			servCfgedEndPt  = ServerEndpointConfig.Builder.create(claz, endPath).build();
			servEndReady2.add(servCfgedEndPt) ;
		} 
		
		System.out.println ("NewXXXXX2222" + servEndReady2.toString()) ;
		return servEndReady2 ;
		
	}

	//@Override
    public Set<ServerEndpointConfig> getEndpointConfigsOLD(Set<Class<? extends Endpoint>> endpointClasses) {
     
        Set<ServerEndpointConfig> result = new HashSet<>();
        
                ServerEndpointConfig servEndPtsTest4 = ServerEndpointConfig.Builder.create(ServerEndPtTest4.class, "/test4EndPoint").build();
                ServerEndpointConfig serverEndPt1 = ServerEndpointConfig.Builder.create(ServerEndPt1.class, "/test1EndPoint").build();
                result.add(servEndPtsTest4);
                result.add(serverEndPt1) ; 
                Ref2Singleton.ONEINSREF.getServLogger().info("OldXXXXServerApplicationConfigImp is Completed") ;  
                System.out.println ("XXXXX2222OLD Old" + result.toString()) ;
        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return Collections.emptySet();
    }
 public Set<Class<? extends Endpoint>> makeEndpointClasses()
 {
	 Set<Class<? extends Endpoint>> retSet = new HashSet<>();
	 retSet.add( ServerEndPtTest4.class) ;
	 getEndpointConfigs(retSet) ;
	 return retSet ;
 }
 
 public ConfigServerEndpoints getCfg()
 {
	 String endPointsJsonCfgFile = Ref2Singleton.ONEINSREF.getPropertyHashMap().get("endpointsCfFilePath").trim()  ;
	 ReadAndCaptureProps readAndCaptureProps = new ReadAndCaptureProps() ;
	 String jsonStr = readAndCaptureProps.readServJsonCfg(endPointsJsonCfgFile) ;
	 Gson gson = new Gson() ;
	 ConfigServerEndpoints cfgEndPoint = gson.fromJson(jsonStr, ConfigServerEndpoints.class) ;
	 System.out.println("XXXX" + cfgEndPoint.getHostIp()) ;
	 System.out.println(cfgEndPoint.getPort());
	 System.out.println(cfgEndPoint.getRootPath() ) ;
	 //System.out.println("XXXX" + cfgEndPoint.getEndPoints().get(1).getPath4endpointClass()) ;
	 System.out.println("XXXX" + cfgEndPoint.getEndPoints().get(0).getEndpointClass()) ;
	 
	 return cfgEndPoint ;
	 
 }
}
//https://tyrus-project.github.io/documentation/1.13.1/user-guide.html#websocket-programmatic-endpoint
//  Deployment of Annotated Endpoint Using ServerContainer
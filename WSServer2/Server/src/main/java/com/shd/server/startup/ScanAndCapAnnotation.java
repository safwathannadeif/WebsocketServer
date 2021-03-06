package com.shd.server.startup;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.sun.java.util.jar.pack.Package.File;
import com.common.SingletonRef;
import com.scan.annotate.AnnottedClazsFrmPkgs;
import com.scan.annotate.query.ClzAndAssociatedMethdsQR;
import com.shd.server.Ref2Singleton;
import com.shd.server.annoatations.AWebSocket;
import com.shd.server.annoatations.AttrsBeanReqResp;
import com.shd.server.annoatations.WebSockURIReqResp;

public class ScanAndCapAnnotation {
private  final int rightNumOfInpParms = 1;	
//private final  String propFileName4ScanPkg = "com.shd.server.startup.properties.PkgNamesToScan" ; //\resources
//private final  String propFileName4ScanPkg = "../../resources.PkgNamesToScan" ; //\resources
private final String propKeyStrName4Scan = "pkgsToScan";
public void scanAnot() throws IOException {
//	Properties properties = new Properties();
//    try {
//        properties.load(getClass().getClassLoader().getResourceAsStream("PkgNamesToScan.properties"));
//    } catch (IOException e) {
//    	e.printStackTrace();
//    }
    String lisOfPkgsToScan =Ref2Singleton.ONEINSREF.getPropertyHashMap().get(propKeyStrName4Scan).trim() ;
	//ResourceBundle resourceBundle = ResourceBundle.getBundle(file.getAbsolutePath()) ;
	//String lisOfPkgsToScan = properties.getProperty(propKeyStrName4Scan);
	System.out.println("pkgs  from Prop As List: " + lisOfPkgsToScan);
	List<String> inputPKGs = Arrays.asList(lisOfPkgsToScan.split(",")) ;
				
	List<Class<? extends Annotation>>  annotedClassLis = new ArrayList<Class<? extends Annotation>> (); 
	List<Class<? extends Annotation>> annotedClz4MethodLis = new ArrayList<Class<? extends Annotation>> ();
	//****How To Scan: -3 Add Annotations for Classes  
	annotedClassLis.add(AWebSocket.class) ;
	//****How To Scan: -4 Add Annotations for Methods within the Classes given in step -2  
	annotedClz4MethodLis.add(WebSockURIReqResp.class) ;
	
	AnnottedClazsFrmPkgs anntClazFrmInResAndPkg = new AnnottedClazsFrmPkgs() ;
	//****How To Scan: -5 Invoke doScanAndCapture   
	anntClazFrmInResAndPkg.doScanAndCapture(inputPKGs,annotedClassLis, annotedClz4MethodLis ) ;
	//****How To Scan: -6 the Scan output "OutPutLisOfAnnotClzsAndMethds" instance Captured in SingletonRef for Further Processing during the run life time 
	//****  Further Processing like  Annotation Query will use this instance/"OutPutLisOfAnnotClzsAndMethds"   
	//
}
public void capAnot()
{
	List<ClzAndAssociatedMethdsQR> qList = SingletonRef.ONLYONEINS.getDoQuery().doIt(AWebSocket.class,WebSockURIReqResp.class) ;  // class and Method within the class
	qList.forEach(antCls -> {
		
		AWebSocket[]   classAnnoteWithAWebSocket  = 	antCls.getClz().getAnnotationsByType(AWebSocket.class) ; 
		System.out.println("For Class[" + antCls.getClz().getName() + "]  and Annoation [" + AWebSocket.class.getName() + "]  Values:: ==>") ;
		if (classAnnoteWithAWebSocket.length == 0) System.out.println("") ;
		for ( AWebSocket a:classAnnoteWithAWebSocket)
		{
			System.out.println("Value = [" + a.WebSocketToProcess()+ "]" ) ;
		}
		antCls.getMethodLis().forEach(m -> {
			WebSockURIReqResp[]   webSockURIReqResp  = 	m.getAnnotationsByType(WebSockURIReqResp.class) ; 
			System.out.println("For Class[" + antCls.getClz().getName() + "]  and Method [" + m.getName() +"]  Annoation [" + WebSockURIReqResp.class.getName() + "]  Values:: ==>") ;
			if (webSockURIReqResp.length == 0) System.out.println("") ;
			String uriValue = null ;
			for ( WebSockURIReqResp ma:webSockURIReqResp)
			{
				uriValue = ma.valueURL() ;
				System.out.println("Value = [" + ma.valueURL()  + "]" ) ;
			}
			////
			AttrsBeanReqResp attrsBeanReqResp = new AttrsBeanReqResp();
			attrsBeanReqResp.setReqRespClass(antCls.getClz());
			attrsBeanReqResp.setInjectAndExtractMethod(m);
			if ( rightNumOfInpParms < m.getParameters().length) throw 
			     new RuntimeException("Class/Method" + antCls.getClz().getName() + "/" + m.getName() + " Inp-Parm Shouldn't be greater than :" + m.getParameters().length) ;
			Class<?> clzInpParm = null ;
			if ( m.getParameters().length == 1 ) clzInpParm =m.getParameterTypes()[0] ; //Req
			attrsBeanReqResp.setInpParmClass(clzInpParm);
			Class<?> clzReturn   = m.getReturnType() ;  // Resp 
			if ( null == clzReturn)throw 
			     new RuntimeException("Class/Method" + antCls.getClz().getName() + "/" + m.getName() + " Return Shouldn't be NULL") ;
			attrsBeanReqResp.setRetOutClass(clzReturn) ;
			if ( Ref2Singleton.ONEINSREF.getURIMap().chkAttrsBeanReqResp(uriValue) != null) throw 
			    new RuntimeException("Duplicated uri vlaue:" + uriValue + " class:" +antCls.getClz().getName() + " method:" + m.getName() );
			Ref2Singleton.ONEINSREF.getURIMap().addReqRespAnnotted(uriValue, attrsBeanReqResp) ;
			//URIMap.addReqRespAnnotted(uriValue, attrsBeanReqResp) ;
		});
	}) ;
}
//
}



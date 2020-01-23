//package com.shd.server.testing;
//
//import com.google.gson.Gson;
//import com.shd.common.msgelms.Wrap;
//import com.shd.server.processing.SendAsynchMsgIF;
//
//public class TestSendOnlyImpSendAsyncMsh  implements SendAsynchMsgIF 
//{
//	Wrap wrap = null ;
//	String respMsg = null ; 
//	public void sendTheMsg(String msg)
//	{
//		System.out.println ("Final Testing OutMsg from TestSendOnly.sendTheMsg\n" + msg) ;
//		respMsg = msg ;
//	}
//	public void setWrapReps(Wrap wrapi) 
//	{
//		wrap=wrapi ;
//		convertBackToObj() ;
//	}
//	public void convertBackToObj() 
//	{
//		try {
//			//AttrsBeanReqResp attr =Ref2Singleton.ONEINSREF.getURIMap().getAttrsBeanReqResp("/TestNo1-UnitTest-A-1");
//			//Method mInjectReqBExtractResp = attr.getInjectAndExtractMethod() ;
//			//Class<?> classTypeForResponse  = mInjectReqBExtractResp.getReturnType();
//			String respMsgFromWrap = wrap.getJsonStrmsg() ;
//			String className4JsonStrMsg = wrap.getClassType4jsonStrmsg() ;
//			Class<?> classForGson = Class.forName(className4JsonStrMsg) ;
//			Gson gson = new Gson();
//			Object respObj = gson.fromJson(respMsgFromWrap, classForGson);
//			System.out.println ("Final Testing OuObj from MWarp msg in TestSendOnly.convertBackToObj\n" + respObj.toString()) ;
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//}
//
//

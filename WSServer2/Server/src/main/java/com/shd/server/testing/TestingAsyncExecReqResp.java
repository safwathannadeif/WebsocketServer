//package com.shd.server.testing;
//
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//
//import com.google.gson.Gson;
//import com.shd.server.Ref2Singleton;
//import com.shd.common.msgelms.Header;
//import com.shd.common.msgelms.MesgTypes;
//import com.shd.common.msgelms.Wrap;
//import com.shd.common.reqresp.req.Req1;
//import com.shd.server.processing.AsyncExecReqResp;
//import com.shd.server.processing.SendAsynchMsgIF;
//
//public class TestingAsyncExecReqResp {
//
//	public void  doUnitTest_A_1() throws NoSuchAlgorithmException, UnsupportedEncodingException
//	{
//		Req1 req1 = new Req1();
//		String jsonReqStr1= req1.getReq1TestObjJsonStr() ;
//		Wrap wrap = new Wrap() ;
//		Header header = new Header() ;
//		String reqRespId  = Ref2Singleton.ONEINSREF.getPossibleUnique() ;
//		header.setReqResId(reqRespId);
//		header.setMsgType(MesgTypes.requestMsgType);
//		header.setUriRequest("/TestNo1-UnitTest-A-1");
//		wrap.setHeader(header);
//		wrap.setJsonStrmsg(jsonReqStr1);
//		Gson gson = new Gson();
//		String wrapStrToSend = gson.toJson(wrap);  
//		
//		SendAsynchMsgIF testSendOnly = new TestSendOnlyImpSendAsyncMsh() ;
//		 AsyncExecReqResp  asyncExecReqResp = new AsyncExecReqResp() ;
//		 asyncExecReqResp.doRequestRespFromCliAsyn(wrapStrToSend,testSendOnly) ;
//		 
//			 
//		
//	
//		
//	}
//
//}
// 
package com.shd.server.processing;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.websocket.Session;

import com.google.gson.Gson;
import com.shd.common.gen.rwfiles.WriteReqRespJsonMsgs;
import com.shd.common.msgelms.Header;
import com.shd.common.msgelms.Wrap;
import com.shd.server.Ref2Singleton;
import com.shd.server.annoatations.AttrsBeanReqResp;
import com.shd.server.sendmsgs.SendToClientImp;
import com.shd.server.sendmsgs.ThredLocalContents;


public class AsyncExecReqResp {
	private String uriInpRequestResp = null;
	//private String wrapInpJonStr = null;
	private String reqRespId = null;
	private Session session = null;
	private Future<?> future ;
	private Wrap wraprequest = null ;
	private WriteReqRespJsonMsgs writeReqRespJsonMsgs = null ;
	private Object inReqObj = null ;
	private Gson gson = new Gson();
	
	public void doRequestRespFromCliAsyn(String wrapReqStr, Session sessioni ) throws ClassNotFoundException {
		session = sessioni ;
		wraprequest = gson.fromJson(wrapReqStr, Wrap.class);
		reqRespId =wraprequest.getHeader().getReqResId() ;
		uriInpRequestResp = wraprequest.getHeader().getUriRequest();
		//wrapInpJonStr = wrapReqStr; 
		Header headerReq = wraprequest.getHeader();
		Class<?> classreq = Class.forName(headerReq.getClassType4jsonStrmsg()) ;
		inReqObj = gson.fromJson(wraprequest.getJsonStrmsg(), classreq);
		Ref2Singleton.ONEINSREF.getServLogger().info("Start Request:" + reqRespId);
		writeReqRespJsonMsgs =Ref2Singleton.ONEINSREF.getTheSingleRefToWriteReqResp() ;

		if (writeReqRespJsonMsgs != null ) writeReqRespJsonMsgs.writeReqMsg(headerReq ,inReqObj ) ; 
		future =Ref2Singleton.ONEINSREF.getExecService().submit(lampdaAsyncServer) ;
		
	}
	Callable<?> lampdaAsyncServer = () -> {
		Exception exception = null;
		try {
			AttrsBeanReqResp attr =Ref2Singleton.ONEINSREF.getURIMap().getAttrsBeanReqResp(uriInpRequestResp) ;
			Method mInjectReqBExtractResp = attr.getInjectAndExtractMethod() ;
			//Class<?> classTypeForReq = mInjectReqBExtractResp.getParameterTypes()[0];//To Do Chk for No Input Param??
			Class<?> classReqResp = attr.getReqRespClass();
			Object reqRespObj  = classReqResp.getConstructor().newInstance();
			SendToClientImp sendToClientImp = new SendToClientImp(session,reqRespId,future,uriInpRequestResp);
			ThredLocalContents.createObject(sendToClientImp); 
			Object objRespone = mInjectReqBExtractResp.invoke(reqRespObj,inReqObj);
			if ( !future.isCancelled() ) sendToClientImp.sendFinalResponse(objRespone);
		} catch (Exception ex) {
			Ref2Singleton.ONEINSREF.getServLogger().severe("Exception:\n" + Ref2Singleton.ONEINSREF.expToStr(ex) );
			ex.printStackTrace();
			exception = ex;
		}
		finally {
			ThredLocalContents.removeObject() ;
			Ref2Singleton.ONEINSREF.getServLogger().info("End Request:" + reqRespId);
		}
		return exception;
	};
}

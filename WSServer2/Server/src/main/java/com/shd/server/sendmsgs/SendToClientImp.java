package com.shd.server.sendmsgs;

import java.util.concurrent.Future;

import javax.websocket.Session;

import com.google.gson.Gson;
import com.shd.common.gen.rwfiles.WriteReqRespJsonMsgs;
import com.shd.common.msgelms.Header;
import com.shd.common.msgelms.MesgTypes;
import com.shd.common.msgelms.ResponseMsgTypes;
import com.shd.common.msgelms.Wrap;
import com.shd.server.Ref2Singleton;

public class SendToClientImp implements SendToClientIF {
	private Session session = null ;
	private String reqRespId = null ;
	private Future<?> future ;
	private String uriInpRequestResp = null ;
	private WriteReqRespJsonMsgs writeReqRespJsonMsgs = null ;

	public  SendToClientImp(Session sessioni,  String reqRespIdi, Future<?> futurei,String uriStr)
	{
		future=futurei ;
		session = sessioni ;
		reqRespId = reqRespIdi ;
		uriInpRequestResp=uriStr ;
		writeReqRespJsonMsgs =Ref2Singleton.ONEINSREF.getTheSingleRefToWriteReqResp() ;
	}
	

	private void sendTheMsg(String msg)
	{
		// Only one thread is permitted to Send.Eliminate any surprise from this WebScoket implementation 
		synchronized(this) 
		{ 
			session.getAsyncRemote().sendText(msg) ; 
		} 
	}
@Override
public <T> void cancelRequest(T t) { // send Cancel
	future.cancel(true) ;
	generatetheMsg(t, ResponseMsgTypes.cancelRespType) ;
	}
	//
	@Override
	public <T> void sendProgress(T t) { // send Progress
		generatetheMsg(t, ResponseMsgTypes.progressRespType) ;	
	}
	//
	@Override
	public <T> void sendError(T t) {  // send error
		generatetheMsg(t, ResponseMsgTypes.errorRespType) ;
	}
	@Override
	public <T> void sendWarning(T t) { // send Warning
		generatetheMsg(t, ResponseMsgTypes.warningRespType) ;
	}
	public <T> void sendFinalResponse(T t) { // send Warning
		generatetheMsg(t, ResponseMsgTypes.finalRespType) ;
	}
	private <M> void generatetheMsg(M m , ResponseMsgTypes typeResp)
	{
		
		if ( typeResp.makeCancel(typeResp) ) {
			future.cancel(true);
			/// ThredLocalContents.removeObject(); no need since finally in the Calling will be Removed
		}
		String classTypeNameToSend = m.getClass().getCanonicalName() ;
		// Get Response
		Wrap wrapResp = new Wrap();
		Header headerResp = new Header();
		headerResp.setMsgType(MesgTypes.responseMsgType);
		headerResp.setUriRequest(uriInpRequestResp);
		headerResp.setReqResId(reqRespId);
		headerResp.setClassType4jsonStrmsg(classTypeNameToSend); 
		headerResp.setRespType(typeResp);
		wrapResp.setHeader(headerResp);
		Gson gson = new Gson() ;
		String respJsonRespStr = gson.toJson(m, m.getClass()) ;
		wrapResp.setJsonStrmsg(respJsonRespStr) ;
		String msgToSend= gson.toJson(wrapResp, Wrap.class) ;
		sendTheMsg(msgToSend) ;
		if (writeReqRespJsonMsgs != null ) writeReqRespJsonMsgs.writeRespMsg(headerResp,m,typeResp) ;
		 
	}

}
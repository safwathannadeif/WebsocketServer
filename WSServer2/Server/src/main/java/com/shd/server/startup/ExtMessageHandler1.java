package com.shd.server.startup;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

import com.shd.server.Ref2Singleton;
import com.shd.server.processing.AsyncExecReqResp;

public class ExtMessageHandler1 implements MessageHandler.Whole<String>
{
	private Session session ;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public ExtMessageHandler1()
	{
		
	}
	@Override
    public void onMessage(String message) {
        System.out.println("inMessage From ExtMessageHandler=" + message ) ;
        msg(message);
}
	//@OnMessage
	public void msg(String inpMsg) {
		try {
			Ref2Singleton.ONEINSREF.getServLogger().info("Server msg recv Length:[" + inpMsg.length()+"]") ;
			AsyncExecReqResp asyncExecReqResServer = new AsyncExecReqResp() ;
			asyncExecReqResServer.doRequestRespFromCliAsyn(inpMsg, session) ; 			
		} catch (Exception ex) {
			Ref2Singleton.ONEINSREF.getServLogger().severe("Exception:\n" + Ref2Singleton.ONEINSREF.expToStr(ex) );
		}
	}
}

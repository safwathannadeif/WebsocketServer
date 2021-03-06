package com.shd.server.startup;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;

import com.shd.server.Ref2Singleton;
import com.shd.server.processing.AsyncExecReqResp ;



//@ServerEndpoint(value="/test1" )
//@ServerEndpoint(value="/" )
public class ServerEndPt1  extends Endpoint{
	Session session = null ;
	String reqRespId = null ;
	@Override
    public void onOpen(Session session, EndpointConfig config) {
    	
        System.out.println("Peer " + session.getId() + " connected with RequestURI [" + session.getRequestURI() +"]");
        System.out.println("EndpointConfig=" + config.toString()) ;
        try {
			session.getBasicRemote().sendPong((ByteBuffer.wrap(("Pong from EndPt Open:" + session.getId().toString()).getBytes()))) ;
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ExtMessageHandler1 eMHandler =new ExtMessageHandler1() ;
        eMHandler.setSession(session);
        session.addMessageHandler(eMHandler);
//        session.addMessageHandler(new MessageHandler.Whole<String>() {
//            @Override
//            public void onMessage(String message) {
//                try {
//                	 System.out.println("inMessage=" + message ) ;
//                    session.getBasicRemote().sendText("Got message from " + session.getId() + "\n" + message);
//                } catch (IOException ex) {
//                }
//            }
//        });
    }
	@OnOpen
	public void open (Session session) throws IOException {
		this.session =session ;
		Ref2Singleton.ONEINSREF.getServLogger().info("Server Session id:[" + session.getId() +"]") ;
		session.getBasicRemote().sendPong((ByteBuffer.wrap(("Pong from EndPt Open:" + session.getId().toString()).getBytes()))) ;

	}   
	@OnMessage
	public void msg(String m) {
		try {
			Ref2Singleton.ONEINSREF.getServLogger().info("Server msg recv Length:[" + m.length()+"]") ;
			AsyncExecReqResp asyncExecReqResServer = new AsyncExecReqResp() ;
			asyncExecReqResServer.doRequestRespFromCliAsyn(m, session) ; 			
		} catch (Exception ex) {
			Ref2Singleton.ONEINSREF.getServLogger().severe("Exception:\n" + Ref2Singleton.ONEINSREF.expToStr(ex) );
		}
	}
	@Override
	public void onClose(Session session, CloseReason closeReason) {

		Ref2Singleton.ONEINSREF.getServLogger().severe("onClose For Session ::" +  session.getId() +  " ClosedReason:\n"+ closeReason.getReasonPhrase());
	}

	@OnMessage 
	public void msg(PongMessage pongMessage) {
		StringBuffer pong = new StringBuffer();
		pong.append(": pong message: ").append(new String(pongMessage.getApplicationData().array()));
		Ref2Singleton.ONEINSREF.getServLogger().info("Server Session id:[" + session.getId() +"] PongRecv:" + pong.toString() ) ;
	}
	@Override
	public void onError(Session session, Throwable thr) {
		Ref2Singleton.ONEINSREF.getServLogger().severe("onError Recved Throwable:\n" + thr.toString() + "\n Message:" +thr.getMessage());
	}

}

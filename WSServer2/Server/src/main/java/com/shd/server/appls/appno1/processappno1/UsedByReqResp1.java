package com.shd.server.appls.appno1.processappno1;


import java.time.Instant;
import java.util.concurrent.TimeUnit;

import com.shd.common.reqresp.resp.ProgresMsgFrmRqRsp1Msg2;
import com.shd.server.sendmsgs.SendToClientIF;
import com.shd.server.sendmsgs.ThredLocalContents;

public class UsedByReqResp1 {

	public UsedByReqResp1() {

	}
	public void sendAnotherProgressMsgViaUsedByReqResp1() throws InterruptedException
	{
		//Instant start = Instant.now();
		// sleep for 5 seconds
		TimeUnit.SECONDS.sleep(1); 
		ProgresMsgFrmRqRsp1Msg2 progressMsgfromRqrsp2 = new ProgresMsgFrmRqRsp1Msg2(String.valueOf(this.hashCode()));
		progressMsgfromRqrsp2.setClassName(ProgresMsgFrmRqRsp1Msg2.class);
		TimeUnit.SECONDS.sleep(1);
		progressMsgfromRqrsp2.setMsg2Time2(Instant.now().getNano());
		progressMsgfromRqrsp2.setComment("Testing ReqResp1 ReqResp1 UsedByReqResp1 sendAnotherProgressMsg Using ProgressMsgfromRqrsp1Msg2") ;
		progressMsgfromRqrsp2.setAllAsToStrAfteAll() ;
		SendToClientIF sendToClientIF = ThredLocalContents.getObject() ;
		sendToClientIF.sendProgress(progressMsgfromRqrsp2) ;
	}
}

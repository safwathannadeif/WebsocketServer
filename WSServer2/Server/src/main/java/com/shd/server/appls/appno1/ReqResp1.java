package com.shd.server.appls.appno1 ;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.shd.common.reqresp.req.Req1;
import com.shd.common.reqresp.req.Req14ListOfStates;
import com.shd.common.reqresp.resp.CountriesResp2;
import com.shd.common.reqresp.resp.Employee;
import com.shd.common.reqresp.resp.ProgresMsgFrmRqRsp1Msg1;
import com.shd.common.reqresp.resp.Resp1;
import com.shd.server.Ref2Singleton;
import com.shd.server.annoatations.AWebSocket;
import com.shd.server.annoatations.WebSockURIReqResp;
import com.shd.server.appls.appno1.processappno1.UsedByReqResp1;
import com.shd.server.sendmsgs.SendToClientIF;
import com.shd.server.sendmsgs.ThredLocalContents;

@AWebSocket
public class ReqResp1 {

	private Req1 req1 ;
	private Resp1 resp1;
	public ReqResp1()
	{
	} 
	public Req1 getReq1() {
		return req1;
	}
	@WebSockURIReqResp(valueURL = "/Employee-Report-1") // WebSockURIReqResp
	public Resp1 reqAndRespEmployeeReport(Req1 reqi) throws InterruptedException {
		/////
		Instant start = Instant.now();
		// sleep for 5 seconds
		TimeUnit.SECONDS.sleep(1); 
		ProgresMsgFrmRqRsp1Msg1 progressMsgfromRqrsp1 = new ProgresMsgFrmRqRsp1Msg1(String.valueOf(this.hashCode()));
		progressMsgfromRqrsp1.setClassName(ProgresMsgFrmRqRsp1Msg1.class);
		TimeUnit.SECONDS.sleep(1);
		progressMsgfromRqrsp1.setMsg1Time2(Instant.now().getNano());
		progressMsgfromRqrsp1.setComment("Testing ReqResp1 ReqResp1 Using ProgressMsgfromRqrsp1Msg1") ;
		progressMsgfromRqrsp1.setAllAsToStrAfteAll() ;
		SendToClientIF sendToClientIF = ThredLocalContents.getObject() ;
		sendToClientIF.sendProgress(progressMsgfromRqrsp1) ;
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toNanos() ;
		String vofDuration = String.valueOf(timeElapsed) ;
		Ref2Singleton.ONEINSREF.getServLogger().info("Server Done with  ReqResp1.reqAndResp sendProgress progressMsgfromRqrsp1 vofDuration In Nanos=" + vofDuration) ;
		//
		UsedByReqResp1 usedByReqResp1 = new UsedByReqResp1() ;
		usedByReqResp1.sendAnotherProgressMsgViaUsedByReqResp1();
		// sleep for 5 seconds
		TimeUnit.SECONDS.sleep(1);        
		finish = Instant.now();
		long timeElapsed2 = Duration.between(start, finish).toNanos() ;
		String vofDuration2 = String.valueOf(timeElapsed2) ;
		////////////////
		req1=reqi ;
		Ref2Singleton.ONEINSREF.getServLogger().info(" Recevied Req1 for /Employee-Report-1 ..vofDuration2.. " + vofDuration2) ;
		GenerateLisOfEmployee empMaker = new GenerateLisOfEmployee() ;
		List<Employee> empLisResp =empMaker.empListReport(reqi.getStart(), reqi.getEnd()) ;
		Resp1 respRet = new Resp1() ;
		respRet.setList(empLisResp);
		return respRet ;
	}
	@WebSockURIReqResp(valueURL = "/CountriesResp2-UnitTest-A-1") // WebSockURIReqResp
	public CountriesResp2 reqAndRespTestA1(Req1 req1i) {

		Ref2Singleton.ONEINSREF.getServLogger().info(" Done with reqAndRespTestA1. Where URI=/TestNo1-UnitTest-A-1 ") ;
		CountriesResp2 resp2 = new CountriesResp2();  // getResp2TestObj
		return resp2.getListEuroZone() ;
	}
	@WebSockURIReqResp(valueURL = "/testRR11-1") // WebSockURIReqResp
	public Resp1 reqAndResp(Req14ListOfStates req1) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2); 
		ProgresMsgFrmRqRsp1Msg1 progressMsgfromRqrsp1 = new ProgresMsgFrmRqRsp1Msg1(String.valueOf(this.hashCode()));
		progressMsgfromRqrsp1.setClassName(this.getClass());
		TimeUnit.SECONDS.sleep(5);
		progressMsgfromRqrsp1.setMsg1Time2(Instant.now().getNano());
		progressMsgfromRqrsp1.setComment("Testing ReqResp1 ReqResp1 Using ProgressMsgfromRqrsp1Msg1") ;
		progressMsgfromRqrsp1.setAllAsToStrAfteAll() ;
		SendToClientIF sendToClientIF = ThredLocalContents.getObject() ;
		sendToClientIF.sendProgress(progressMsgfromRqrsp1) ;
		GenerateLisOfEmployee empMakeri = new GenerateLisOfEmployee() ;
		List<Employee> empLisRespi =empMakeri.empListReport(1, 100) ;
		Resp1 respRet = new Resp1() ;
		respRet.setList(empLisRespi);
		return respRet ;
	}
	//
	public void setResp(Resp1 resp) {
		this.resp1 = resp;
	}
	//
	@Override
	public String toString() {
		if ( null == resp1) return toStringBeforeResponse() ;
		return "ReqResp1 [req1=" + req1.toString() + ", resp1=" + resp1.toString() + "]";
	}
	//
	private String toStringBeforeResponse() {
		return "ReqResp1 [req1=" + req1.toString() + "  resp1= Not Completed=null ]";
	}
}
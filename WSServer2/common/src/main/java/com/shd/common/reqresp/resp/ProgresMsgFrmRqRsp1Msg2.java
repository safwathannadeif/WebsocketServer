package com.shd.common.reqresp.resp;

import java.time.Instant;

public class ProgresMsgFrmRqRsp1Msg2 {
	private long msg2Time1 ;
	private long msg2Time2 ;
	private String callingId ;
	private String className  ;
	private String comment ;
	private String allAsToStr ;
    private final String PROGRESSRESPMSGID   = "ProgressMsgfromRqRsp1Msg2" ;

	public ProgresMsgFrmRqRsp1Msg2() {
		
	}
    public ProgresMsgFrmRqRsp1Msg2(String hashCodeCallingId)
    {
    	msg2Time1 = Instant.now().getNano() ;
    	callingId  = hashCodeCallingId ;     
    }
    public void setAllAsToStrAfteAll()
    {    
    allAsToStr= this.toString();
    }
	public long getMsg2Time1() {
		return msg2Time1;
	}
	public void setMsg2Time1(long msg2Time1) {
		this.msg2Time1 = msg2Time1;
	}
	public long getMsg2Time2() {
		return msg2Time2;
	}
	public void setMsg2Time2(long msg2Time2) {
		this.msg2Time2 = msg2Time2;
	}
	public String getCallingId() {
		return callingId;
	}
	public void setCallingId(String callingId) {
		this.callingId = callingId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(Class<?> fromClass) {
		this.className = fromClass.getCanonicalName().trim();
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAllAsToStr() {
		return allAsToStr;
	}
	public void setAllAsToStr(String allAsToStr) {
		this.allAsToStr = allAsToStr;
	}
	public String getPROGRESSRESPMSGID() {
		return PROGRESSRESPMSGID;
	}
	@Override
	public String toString() {
		return "ProgresMsgFrmRqRsp1Msg2 [msg2Time1=" + msg2Time1 + ", msg2Time2=" + msg2Time2 + ", callingId="
				+ callingId + ", className=" + className + ", comment=" + comment + ", allAsToStr=" + allAsToStr
				+ ", PROGRESSRESPMSGID=" + PROGRESSRESPMSGID + "]";
	}
    
}
package com.shd.common.reqresp.resp;

import java.time.Instant;

public class ProgresMsgFrmRqRsp1Msg1 {
	
	private long msg1Time1 ;
	private long msg1Time2 ;
	private String callingId ;
	private String className  ;
	private String comment ;
	private String allAsToStr ;
    private final String PROGRESSRESPMSGID   = "ProgressMsgfromRqRsp1Msg1" ;
    
    public ProgresMsgFrmRqRsp1Msg1() 
    {
    	
    }
    public ProgresMsgFrmRqRsp1Msg1(String hashCodeCallingId)
    {
    	msg1Time1 = Instant.now().getNano() ;
    	callingId  = hashCodeCallingId ;     
    }
    public void setAllAsToStrAfteAll()
    {    
    allAsToStr= this.toString();
    }
	public long getMsg1Time1() {
		return msg1Time1;
	}
	public void setMsg1Time1(long msg1Time1) {
		this.msg1Time1 = msg1Time1;
	}
	public long getMsg1Time2() {
		return msg1Time2;
	}
	public void setMsg1Time2(long msg1Time2) {
		this.msg1Time2 = msg1Time2;
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
	public void setClassName(Class<?>   fromclass) {
		this.className = fromclass.getCanonicalName().trim();
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
		return "ProgressMsgfromRqrsp1Msg1 [msg1Time1=" + msg1Time1 + ", msg1Time2=" + msg1Time2 + ", callingId="
				+ callingId + ", className=" + className + ", comment=" + comment + ", allAsToStr=" + allAsToStr
				+ ", PROGRESSRESPMSGID=" + PROGRESSRESPMSGID + "]";
	}
	
    
}
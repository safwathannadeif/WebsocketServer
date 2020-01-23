package com.shd.common.msgelms ; 
public class Header {
private String uriRequest ;
private String reqResId ;
private MesgTypes msgType ;
private String  ClassType4jsonStrmsg ;

public String getClassType4jsonStrmsg() {
	return ClassType4jsonStrmsg;
}
public void setClassType4jsonStrmsg(String classType4jsonStrmsg) {
	ClassType4jsonStrmsg = classType4jsonStrmsg;
}
private ResponseMsgTypes respType = ResponseMsgTypes.UnknownType ;



public String getUriRequest() {
	return uriRequest;
}
public void setUriRequest(String uriRequest) {
	this.uriRequest = uriRequest;
}
public String getReqResId() {
	return reqResId;
}
public void setReqResId(String reqResId) {
	this.reqResId = reqResId;
}
public MesgTypes getMsgType() {
	return msgType;
}
public void setMsgType(MesgTypes msgType) {
	this.msgType = msgType;
}
public ResponseMsgTypes getRespType() {
	return respType;
}
public void setRespType(ResponseMsgTypes respType) {
	this.respType = respType;
}
@Override
public String toString() {
	return "Header [uriRequest=" + uriRequest + ", reqResId=" + reqResId + ", msgType=" + msgType
			+ ", ClassType4jsonStrmsg=" + ClassType4jsonStrmsg + ", respType=" + respType + "]";
}

}
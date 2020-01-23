package com.shd.common.msgelms;

public enum ResponseMsgTypes {
	finalRespType , cancelRespType, errorRespType, progressRespType, warningRespType , UnknownType ;
	
	public Boolean isEndOfResp(ResponseMsgTypes type)
	{
	if (  type == ResponseMsgTypes.cancelRespType  
		  || type == ResponseMsgTypes.errorRespType 
		  || type == ResponseMsgTypes.finalRespType ) 	return true ;
	return false ;
	}
	public Boolean makeCancel(ResponseMsgTypes type)
	{
	if (   type == ResponseMsgTypes.cancelRespType  
	    || type == ResponseMsgTypes.errorRespType ) return true ;
		   
	return false ;
	}
}


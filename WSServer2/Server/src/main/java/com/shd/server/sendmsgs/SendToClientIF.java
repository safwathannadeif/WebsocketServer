package com.shd.server.sendmsgs;

public interface SendToClientIF {

	public <T> void cancelRequest(T t);
	//
	public <T> void sendProgress(T t);
	//
	public <T> void sendError(T t);
	//
	public <T> void sendWarning(T t);

}
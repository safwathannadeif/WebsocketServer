package com.shd.server.sendmsgs;

public class ThredLocalContents {
	private static final ThreadLocal<SendToClientIF> threadLocal = new ThreadLocal<>();
	
	
	 public static void createObject(SendToClientIF sendClii) {	      
	      threadLocal.set(sendClii);	      
	  }
	 public static SendToClientIF getObject() {
	      return threadLocal.get();
	  }
	 public static void  removeObject() {
		  threadLocal.remove();
		  
	  }
}
// SendToClientIF ThredLocalContents.getObject
package com.shd.server.annoatations;



import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AWebSocket {
	public String WebSocketToProcess() default "";
}

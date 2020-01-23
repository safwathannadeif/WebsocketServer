package com.shd.common.reqresp.req;

public class Req1 {
	private int start = 1 ;
	private int end = 100 ;
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Req1 [start=" + start + ", end=" + end + "]";
	}
	

}

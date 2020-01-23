package com.shd.common.msgelms ;

public class Wrap {
	private Header header;
	private String jsonStrmsg;
	

	@Override
	public String toString() {
		return "Wrap [header=" + header + ", jsonStrmsg=" + jsonStrmsg + "]";
	}

	public Header getHeader() {
		return header;
	}

	public String getJsonStrmsg() {
		return jsonStrmsg;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	public void setJsonStrmsg(String jsonStrmsg) {
		this.jsonStrmsg = jsonStrmsg;
	}

}

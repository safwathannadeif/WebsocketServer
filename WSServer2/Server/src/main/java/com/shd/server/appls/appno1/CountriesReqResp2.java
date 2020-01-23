package com.shd.server.appls.appno1;

import com.shd.common.reqresp.resp.CountriesResp2;
import com.shd.server.annoatations.AWebSocket;
import com.shd.server.annoatations.WebSockURIReqResp;

@AWebSocket
public class CountriesReqResp2 {

	public CountriesReqResp2() {
		
	}
	@WebSockURIReqResp(valueURL = "/testCountey22-10") // WebSockURIReqResp
	public CountriesResp2 getListEuroZone()
	{
		CountriesResp2 countriesResp2 = new CountriesResp2() ;
		return countriesResp2.getListEuroZone() ;
	}
	
}

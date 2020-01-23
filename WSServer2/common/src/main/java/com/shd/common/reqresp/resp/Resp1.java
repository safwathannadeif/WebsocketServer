package com.shd.common.reqresp.resp;

import java.util.ArrayList;
import java.util.List;


public class Resp1 
{
private List<Employee> list =new ArrayList<Employee>();	

public Resp1() {

}

public List<Employee> getList() {
	return list;
}

public void setList(List<Employee> listi) {
	this.list = listi;
}
}
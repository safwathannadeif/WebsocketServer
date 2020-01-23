package com.shd.common.reqresp.req;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
public class  Req14ListOfStates
{
	String name;
	int population;
	private List<String> listOfStates = new ArrayList<String>();
	public Req14ListOfStates()
	{

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public List<String> getListOfStates() {
		return listOfStates;
	}
	public void setListOfStates(List<String> listOfStates) { 
		this.listOfStates = listOfStates;
	}
	public  String getReq1TestObjJsonStr() 
	{
		Req14ListOfStates req1Obj= getReq1TestObj() ;
		Gson gson = new Gson();
		String jsonStr = gson.toJson(req1Obj);  
		return jsonStr ;
	}
	//
	public  Req14ListOfStates getReq1TestObj() 
	{
		Req14ListOfStates req1Obj=new Req14ListOfStates();
		req1Obj.setName("USA");
		req1Obj.setPopulation(327*1000000);
		listOfStates.add("New York");
		listOfStates.add("Florida");
		listOfStates.add("California");
		listOfStates.add("Hawaii");
		listOfStates.add("Nevada");
		listOfStates.add("Texas");  
		listOfStates.add("Guinea");
		req1Obj.setListOfStates(listOfStates);  
		return req1Obj ;
	}
	@Override
	public String toString() {
		return "Req1 [name=" + name + ", population=" + population + ", listOfStates=" + listOfStates + "]";
	}

}

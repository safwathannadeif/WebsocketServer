package com.shd.server.testing;

public class Employee {
	public Employee ( String n, int i )
	{
		age= i ;
		name = n ;
	}
	private String name ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int age ;
	

}

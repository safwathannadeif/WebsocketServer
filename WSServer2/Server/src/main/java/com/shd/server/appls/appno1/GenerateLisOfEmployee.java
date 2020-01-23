package com.shd.server.appls.appno1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.shd.common.reqresp.resp.Employee;
import com.shd.server.Ref2Singleton;

public class GenerateLisOfEmployee {  /// Generate Lis Of Employee

	@FunctionalInterface 
	interface empFunction {
		Employee doIt(Employee emp, Integer ii ) ;
		}
	empFunction  empFunctioni = Employee::generateNewEmployee ; 
	  Employee nEmp = new Employee() ;		
	
    public  List<Employee> empListReport(int start , int end)
    {
		List<Integer>  inArry =IntStream.range(start, end).boxed().collect(Collectors.toList()) ;
        GenerateLisOfEmployee genertaeLisOfEmployeei = new GenerateLisOfEmployee() ;
        List<Employee> lisOfEmployee = inArry.stream().map( i-> genertaeLisOfEmployeei.empFunctioni.doIt(genertaeLisOfEmployeei.nEmp,i) ).collect(Collectors.toList()) ;
        Ref2Singleton.ONEINSREF.getServLogger().info("Start empListReport from GenerateLisOfEmployee:") ;
       // lisOfEmployee.forEach( System.out::println);
        Ref2Singleton.ONEINSREF.getServLogger().info("End empListReport from GenerateLisOfEmployee:") ;
        return lisOfEmployee ;
    }
}

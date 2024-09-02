package com.assignment.EmployeeManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.assignment.EmployeeManagement.models.Employee;
import com.assignment.EmployeeManagement.models.User;
import com.assignment.EmployeeManagement.services.EmployeeServices;


@Component
public class EmployeeManagementRunner implements CommandLineRunner {
	
	@Autowired
	EmployeeServices employeeServices;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//Generates list of employee using Supplier functional interface which generates dummy data and returns
		List<Employee> li = employeeServices.getEmployeesBySupplierInterface();
		
//		//Generates User from Employees. Uses Supplier to generate random password and Function to generate username using Employee
		List<User> users = employeeServices.getUsers(li);
//		users.stream().forEach(u->System.out.println(u.toString()));
		
//		//Filters Employees using predicate whose salary is 2000 above
//		filterEmployeesBySalary(li);
		
		
		
//		//Sort employees based on birth month
//		employeeServices.sortEmployeesBasedOnBirthMonth(li)
//		.stream()
//		.forEach(e->System.out.println(e.getFirstName()+" "+e.getDateOfBirth().getMonth()));
		
		
		//Creates 2 thread, one to print Employees and another to print Users
		employeeServices.printEmployeesAndUsers(li,users);
	}

	private void filterEmployeesBySalary(List<Employee> li) {
		
		List<Employee> filteredEmployees = employeeServices.filterEmployeesBySalaryUsingPredicate(li);
		
		System.out.println("----------------------List of Employees whose salary is more than 2000-----------------------------------");
		
		filteredEmployees.stream().forEach(e->System.out.println(e.getFirstName()+" "+e.getLastName()+" "+e.getSalary()));
		
		System.out.println("---------------------------------------------------------------------------------------------------------");
	}
	
	

}

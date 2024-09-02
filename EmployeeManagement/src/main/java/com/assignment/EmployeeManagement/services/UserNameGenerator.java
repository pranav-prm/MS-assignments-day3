package com.assignment.EmployeeManagement.services;

import com.assignment.EmployeeManagement.models.Employee;

@FunctionalInterface
public interface UserNameGenerator {
	public String generate(Employee employee);
}

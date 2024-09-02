package com.assignment.EmployeeManagement.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.assignment.EmployeeManagement.models.Employee;
import com.assignment.EmployeeManagement.models.User;

@Service
public class EmployeeServices {

	//generates Employees list using supplier
    public List<Employee> getEmployeesBySupplierInterface() {
        Supplier<List<Employee>> employeeSupplier = () -> Arrays.asList(
                new Employee(1, "Pranav", "Mutalik", 1800, LocalDate.of(1999, 7, 17)),
                new Employee(2, "Aisha", "Kumar", 1600, LocalDate.of(1998, 5, 22)),
                new Employee(3, "Raj", "Sharma", 1900, LocalDate.of(2000, 3, 11)),
                new Employee(4, "Neha", "Verma", 2000, LocalDate.of(1999, 12, 30)),
                new Employee(5, "Vikram", "Singh", 1700, LocalDate.of(1998, 11, 2)),
                new Employee(6, "Sonia", "Patel", 1500, LocalDate.of(1999, 6, 25)),
                new Employee(7, "Ravi", "Mehta", 1800, LocalDate.of(2000, 8, 17)),
                new Employee(8, "Priya", "Reddy", 1900, LocalDate.of(1998, 4, 13)),
                new Employee(9, "Manoj", "Ghosh", 1600, LocalDate.of(1999, 9, 5)),
                new Employee(10, "Aarti", "Joshi", 2000, LocalDate.of(2000, 1, 19)),
                new Employee(11, "Suresh", "Gupta", 2500, LocalDate.of(1998, 10, 21)),
                new Employee(12, "Rina", "Singh", 2200, LocalDate.of(1999, 2, 7)),
                new Employee(13, "Anil", "Kapoor", 2300, LocalDate.of(1998, 12, 14)),
                new Employee(14, "Kiran", "Bhat", 3400, LocalDate.of(1999, 8, 8)),
                new Employee(15, "Amit", "Joshi", 2700, LocalDate.of(2000, 11, 30))
        );
        return employeeSupplier.get();
    }
    
    //filters the Employess whose salary is more than 2000
	public List<Employee> filterEmployeesBySalaryUsingPredicate(List<Employee> li) {
		// TODO Auto-generated method stub
		Predicate<Employee> salaryBasedFilter = (emp)->emp.getSalary()>2000;
		return li.stream().filter(salaryBasedFilter).collect(Collectors.toList());
	}

	
	//generates Users list by mapping Employee list. Uses supplier to generate random password and 
	// and a user defined Functional interface called UserNameGenerator to generate username using employee
	public List<User> getUsers(List<Employee> li) {
		
		//Supplier to generate password
		Supplier<String> passwordGenerator = ()->{
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder password = new StringBuilder(16);
            for (int i = 0; i < 16; i++) {
                int index = random.nextInt(characters.length());
                password.append(characters.charAt(index));
            }
            return password.toString();
		};
		
		//User defined Functional interface that takes Employee and returns username
		UserNameGenerator userNameGenerator = (emp)->emp.getFirstName()+emp.getLastName()+emp.getDateOfBirth().getYear()+emp.getId();
		
		
		//Creates users using map function
		List<User> users = li.stream()
				.map(emp->new User(userNameGenerator.generate(emp), passwordGenerator.get()))
				.collect(Collectors.toList());
		
		
		
		return users;
	}
	
	
	//sorts employees based on the month of birth using comparator and returns the sorted list
	public List<Employee> sortEmployeesBasedOnBirthMonth(List<Employee> li){
		return li.stream()
				.sorted((a,b)->a.getDateOfBirth().getMonthValue()-b.getDateOfBirth().getMonthValue())
				.collect(Collectors.toList());
	}

	
	//Creates 2 thread, one to print Employees and another to print Users
	public void printEmployeesAndUsers(List<Employee> employees, List<User> users) {
		
		Runnable employeesPrinter = ()->{
			for(Employee emp : employees) {
				System.out.println(emp.toString());
				try {
					Thread.sleep(200);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		Runnable usersPrinter = ()->{
			for(User usr : users) {
				System.out.println(usr.toString());
				try {
					Thread.sleep(200);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		Thread employeePrintingThread = new Thread(employeesPrinter);
		Thread usersPrintingThread = new Thread(usersPrinter);
		
		employeePrintingThread.start();
		usersPrintingThread.start();
	}

	
	
	
}

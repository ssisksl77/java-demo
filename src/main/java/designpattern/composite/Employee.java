package designpattern.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Employee {
	private enum LEVEL {BOSS, MANAGER, DEVELOPER}
	
	private final String name;
	private final String dept;
	private final LEVEL level;
	private List<Employee> subEmployees;
	
	private Employee(String name, String dept, LEVEL level, List<Employee> subEmployees) {
		super();
		this.name = name;
		this.dept = dept;
		this.level = level;
		this.subEmployees = subEmployees;
	}
	
	public static Employee boss(String name, String dept, List<Employee> subEmployees) {
		return new Employee(name, dept, LEVEL.BOSS, subEmployees);
	}
	
	public static Employee manager(String name, String dept, List<Employee> subEmployees) {
		return new Employee(name, dept, LEVEL.MANAGER, subEmployees);
	}
	
	public static Employee developer(String name, String dept) {
		return new Employee(name, dept, LEVEL.DEVELOPER, new ArrayList<>());
//		return new Employee(name, dept, LEVEL.DEVELOPER, null);
	}
	
	public void printSubEmployeesBFS() {
		LinkedList<Employee> queue = new LinkedList<>(subEmployees);
		
		while(!queue.isEmpty()) {
			Employee e= queue.pop();
			System.out.println(e);
			queue.addAll(e.subEmployees);
		}
	}
	
	public void printSubEmployeesBFSRecursive() {
		printSubEmployeesBFSRecursiveHelper(this.subEmployees);
	}
	
	private void printSubEmployeesBFSRecursiveHelper(List<Employee> subEmployees) {
		if (subEmployees.isEmpty()) return;
		
		LinkedList<Employee> employees = new LinkedList<>(subEmployees);
		Employee e = employees.poll();
		System.out.println(e);
		
		employees.addAll(e.subEmployees);
		
		printSubEmployeesBFSRecursiveHelper(employees);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subEmployees == null) ? 0 : subEmployees.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subEmployees == null) {
			if (other.subEmployees != null)
				return false;
		} else if (!subEmployees.equals(other.subEmployees))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  level + " [name=" + name + ", dept=" + dept + "]";
	}
	
	
}

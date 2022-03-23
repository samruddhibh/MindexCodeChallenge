package com.mindex.challenge.data;

import java.util.List;

public class EmployeeStructure {

	private String empId;
	private String firstName;
	private String lastName;
	List<EmployeeStructure> hierarchy;
	
	public EmployeeStructure() {

	}
	
	public EmployeeStructure(String empId, String firstName, String lastName, List<EmployeeStructure> hierarchy) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hierarchy = hierarchy;
	}
	
	public String getEmpId() {
		return empId;
	}
	
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<EmployeeStructure> getHierarchy() {
		return hierarchy;
	}
	
	public void setHierarchy(List<EmployeeStructure> hierarchy) {
		this.hierarchy = hierarchy;
	}

}

package com.mindex.challenge.data;

public class ReportingStructure {
	
	private String employeeId;
	private EmployeeStructure empStructure;
	private int numberOfReports;
	
	public ReportingStructure() {
	}

	public ReportingStructure(String employeeId, EmployeeStructure empStructure, int numberOfReports) {
		super();
		this.employeeId = employeeId;
		this.empStructure = empStructure;
		this.numberOfReports = numberOfReports;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeStructure getEmpStructure() {
		return empStructure;
	}

	public void setEmpStructure(EmployeeStructure empStructure) {
		this.empStructure = empStructure;
	}

	public int getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
}

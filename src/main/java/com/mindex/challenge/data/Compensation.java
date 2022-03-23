package com.mindex.challenge.data;

import java.math.BigDecimal;

public class Compensation {
	
	private Employee employee;
	private BigDecimal salary;
	private String effectiveDate;
	
	public Compensation() {

	}

	public Compensation(Employee employeeId, BigDecimal salary, String effectiveDate) {
		super();
		this.employee = employeeId;
		this.salary = salary;
		this.effectiveDate = effectiveDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employeeId) {
		this.employee = employeeId;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}

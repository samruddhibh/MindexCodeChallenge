package com.mindex.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.EmployeeStructure;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private int count = 0;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    @Override
    public ReportingStructure readStructure(String id) {
    	LOG.debug("Fetching reporting structure for employee with id [{}]", id);
    	this.count = 0;
    	EmployeeStructure es = getStructure(id, new EmployeeStructure());
    	ReportingStructure rs = new ReportingStructure(id, es, this.count);
        return rs;
    }
    
    private EmployeeStructure getStructure(String id, EmployeeStructure es) {
    	LOG.debug("Invoked private method to fetch reporting structure for employee with id [{}]", id);
    	Employee employee = employeeRepository.findByEmployeeId(id);
    	es.setEmpId(employee.getEmployeeId());
    	es.setFirstName(employee.getFirstName());
    	es.setLastName(employee.getLastName());
    	List<EmployeeStructure> emps = new ArrayList<EmployeeStructure>();
    	EmployeeStructure empStr = null;
    	if(employee.getDirectReports() != null && !employee.getDirectReports().isEmpty()) {
    		for(Employee emp : employee.getDirectReports()) {
    			empStr = new EmployeeStructure();
        		this.count++;
        		// recursive call to getStructure() to get the hierarchy structure for every employee in the hierarchy
        		empStr = getStructure(emp.getEmployeeId(), empStr);
        		
        		if(empStr.getEmpId() != null) {
            		emps.add(empStr);
            	}
        	}
    	}
    	es.setHierarchy(emps);
		return es;
    }
}

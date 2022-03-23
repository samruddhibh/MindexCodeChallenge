package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationCreateUrl;
    private String compensationReadUrl;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	compensationCreateUrl = "http://localhost:" + port + "/compensation";
    	compensationReadUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateReadCompensation() {
    	
    	//Create Compensation
        Employee expectedEmployee = new Employee();
        expectedEmployee.setEmployeeId("b7839309-3348-463b-a7e3-5de1c168beb3");
        expectedEmployee.setFirstName("Paul");
        expectedEmployee.setLastName("McCartney");
        expectedEmployee.setDepartment("Engineering");
        expectedEmployee.setPosition("Developer I");
        
        Compensation expectedCompensation = new Compensation();
        expectedCompensation.setEmployee(expectedEmployee);
        expectedCompensation.setSalary(new BigDecimal("125000.00"));
        expectedCompensation.setEffectiveDate("03/22/2022");

        Compensation actualCompensation = restTemplate.postForEntity(compensationCreateUrl, expectedCompensation, Compensation.class).getBody();
        assertNotNull(actualCompensation);
        assertCompensationEquivalence(expectedCompensation, actualCompensation);


        //Read Compensation
        Employee expectedReadEmp = new Employee();
        expectedReadEmp.setEmployeeId("62c1084e-6e34-4630-93fd-9153afb65309");
        expectedReadEmp.setFirstName("Pete");
        expectedReadEmp.setLastName("Best");
        expectedReadEmp.setDepartment("Engineering");
        expectedReadEmp.setPosition("Developer II");
        
        Compensation expectedReadComp = new Compensation();
        expectedReadComp.setEmployee(expectedReadEmp);
        expectedReadComp.setSalary(new BigDecimal("125000.00"));
        expectedReadComp.setEffectiveDate("03/22/2022");

        Compensation readCompensation = restTemplate.getForEntity(compensationReadUrl, Compensation.class, expectedReadEmp.getEmployeeId()).getBody();
        assertNotNull(readCompensation);
        assertCompensationEquivalence(expectedReadComp, readCompensation);

    }

    private static void assertCompensationEquivalence(Compensation expectedCompensation, Compensation actualCompensation) {
        assertEquals(expectedCompensation.getEmployee().getEmployeeId(), actualCompensation.getEmployee().getEmployeeId());
        assertEquals(expectedCompensation.getEmployee().getFirstName(), actualCompensation.getEmployee().getFirstName());
        assertEquals(expectedCompensation.getEmployee().getLastName(), actualCompensation.getEmployee().getLastName());
        assertEquals(expectedCompensation.getEmployee().getPosition(), actualCompensation.getEmployee().getPosition());
        assertEquals(expectedCompensation.getEmployee().getDepartment(), actualCompensation.getEmployee().getDepartment());
        assertEquals(expectedCompensation.getEmployee().getDirectReports(), actualCompensation.getEmployee().getDirectReports());
        assertEquals(expectedCompensation.getEffectiveDate(), actualCompensation.getEffectiveDate());
        assertEquals(expectedCompensation.getSalary(), actualCompensation.getSalary());
    }
}

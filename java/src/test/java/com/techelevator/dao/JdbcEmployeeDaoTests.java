package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;


import org.junit.Test;


public class JdbcEmployeeDaoTests extends BaseDaoTests {

    private JdbcEmployeeDao dao;
    private User testUser;


    private static final Employee emp1 = new Employee(1,"joe","stalin","communism@russia.rs");
    private static final Employee emp2 = new Employee(2,"zac","attack","noob@russia.rs");
    private static final Employee emp3 = new Employee(3,"jack","testUser","pickofdestiny@russia.rs");

    private static final Employee RACHELLE = new Employee(2,"Rachelle R.","user2","rachelle@te.com");


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcEmployeeDao(jdbcTemplate);
        testUser = new User();
        testUser.setUsername("testUser");
    }

        @Test
        public void get_employee_from_username_returns_correct_employee() {
            Employee testEmployee = RACHELLE;
            Employee realEmployee = dao.getEmployeeFromUsername(RACHELLE.getUsername());
            Assert.assertEquals("Email should be " + testEmployee.getEmail(),testEmployee.getEmail(),realEmployee.getEmail());
        }
            // Mocking behavior of jdbcTemplate




}

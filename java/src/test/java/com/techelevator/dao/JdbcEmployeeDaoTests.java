package com.techelevator.dao;

import com.techelevator.model.Employee;
import com.techelevator.model.Request;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



public class JdbcEmployeeDaoTests extends BaseDaoTests {

    private JdbcEmployeeDao dao;
    private User testUser;


    private static final Employee emp1 = new Employee(1,"joe","stalin","communism@russia.rs");
    private static final Employee emp2 = new Employee(2,"zac","attack","noob@russia.rs");
    private static final Employee emp3 = new Employee(3,"jack","testUser","pickofdestiny@russia.rs");


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcEmployeeDao(jdbcTemplate);
        testUser = new User();
        testUser.setUsername("testUser");
    }

    // TODO write a test that checks getEmployeeIdFromUser(User user)....help steve
    //


        @Test
        public void testGetEmployeeFromUser() {

            Assert.assertEquals(emp3,dao.getEmployeeFromUser(testUser));
        }
            // Mocking behavior of jdbcTemplate




}

package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcEmployeeDaoTests extends BaseDaoTests {

    private JdbcEmployeeDao dao;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcEmployeeDao(jdbcTemplate);
    }

    // TODO write a test that checks getEmployeeIdFromUser(User user)


}

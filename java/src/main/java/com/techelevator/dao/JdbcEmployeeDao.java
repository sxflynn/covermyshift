package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcEmployeeDao implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    //TODO complete this select string
    private final String ALL_EMPLOYEE_COLUMNS = "SELECT ";

    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO implement this method
    @Override
    public int getEmployeeIdFromUser(User user) {
        return 0;
    }

    //TODO create mapRowsToEmployee
}

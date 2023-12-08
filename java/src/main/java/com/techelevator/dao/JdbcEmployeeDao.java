package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    //TODO complete this select string
    private final String ALL_EMPLOYEE_COLUMNS = "SELECT * FROM employee";

    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO implement this method (errorHandling not done) no test
    @Override
    public int getEmployeeIdFromUser(User user) {
        String sql = "SELECT employee_id FROM employee \n" +
                "WHERE username = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user.getUsername());
            while (result.next()) {
                int employeeId = result.getInt("employee_id");
                return employeeId;
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);

        }
        return 0;
    }

    //TODO create mapRowsToEmployee
}

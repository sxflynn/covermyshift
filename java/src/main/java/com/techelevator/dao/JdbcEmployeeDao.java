package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Employee;
import com.techelevator.model.Request;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    //TODO complete this select string
    private final String ALL_EMPLOYEE_COLUMNS = "SELECT employee_id, employee_name, username, email FROM employee ";

    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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



    @Override
    public Employee getEmployeeFromUser(User user) {
        Employee employee = new Employee();
        String sql = ALL_EMPLOYEE_COLUMNS + "WHERE username = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql,user.getUsername());
        employee = mapRowsToEmployee(row);
        return employee;
    }

    @Override
    public Employee getEmployeeFromUsername(String username) {
        Employee employee = new Employee();
        String sql = ALL_EMPLOYEE_COLUMNS + "WHERE username = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
            if ((result.next())) {
                employee = mapRowsToEmployee(result);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return employee;
    }



    private Employee mapRowsToEmployee(SqlRowSet rowSet) {
       Employee employee = new Employee();
        employee.setEmployeeId(rowSet.getInt("employee_id"));
        employee.setEmployeeName(rowSet.getString("employee_name"));
        employee.setUsername(rowSet.getString("username"));
        employee.setEmail(rowSet.getString("email"));

        return employee;
    }
}

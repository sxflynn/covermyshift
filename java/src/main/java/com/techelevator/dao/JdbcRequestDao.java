package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcRequestDao implements RequestDao {

    private final JdbcTemplate jdbcTemplate;
    private final String ALL_COLUMNS_WITH_EMPLOYEE_NAME = "SELECT request_id, employee.employee_id, employee.employee_name, date, request.message, is_emergency, is_pending, is_covered, is_approved from request \n" +
            "JOIN employee ON employee.employee_id = request.employee_id ";

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Request> getAllRequests() {
        List<Request> requestList = new ArrayList<>();
        String sql = ALL_COLUMNS_WITH_EMPLOYEE_NAME;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Request request = mapRowsToRequests(results);
                requestList.add(request);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return requestList;
    }

    @Override
    public Request getRequestByRequestId(int requestId) {
        Request requestById = null;
        String sql =  ALL_COLUMNS_WITH_EMPLOYEE_NAME + "WHERE request.request_id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, requestId);
            if ((result.next())) {
                requestById = mapRowsToRequests(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return requestById;
    }

    @Override
    public Request getRequestByEmployeeId(int employeeId) {
        Request requestById = new Request();
        String sql =  ALL_COLUMNS_WITH_EMPLOYEE_NAME + "WHERE request.employee_Id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, employeeId);
            if ((result.next())) {
                requestById = mapRowsToRequests(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return requestById;
    }


    @Override
    public Request createRequest(Request request) {
        int newId;
        Request newRequest = null;
        String sql = "INSERT INTO request(employee_id, date, is_emergency, is_pending, is_covered, is_approved, message) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING request_id;";
        try {
            newId = jdbcTemplate.queryForObject(sql, int.class, request.getEmployeeId(), request.getDate(), request.isEmergency(), request.isPending(), request.isCovered(), request.isApproved(), request.getMessage());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return getRequestByRequestId(newId);
    }

    private Request mapRowsToRequests(SqlRowSet result) {
        Request request = new Request();
        request.setRequestId(result.getInt("request_id"));
        request.setEmployeeName(result.getString("employee_name"));
        request.setEmployeeId(result.getInt("employee_id"));
        request.setDate(result.getDate("date").toLocalDate());
        request.setEmergency(result.getBoolean("is_emergency"));
        request.setMessage(result.getString("message"));
        request.setCovered(result.getBoolean("is_covered"));
        request.setPending(result.getBoolean("is_pending"));
        request.setApproved(result.getBoolean("is_approved"));

        return request;
    }

    //update request

    //            int rowAffected = jdbcTemplate.update(sql, request.getEmployeeId(), request.getDate(), request.isEmergency(), request.isPending(), request.isCovered(), request.isApproved(), request.getMessage());
//            if(rowAffected == 0) {
//        throw new DaoException("Zero rows affected, expected at least one");
//    } else {
//        newRequest = getRequestByRequestId(request.getRequestId());
//    }
}


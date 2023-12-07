package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRequestDao implements RequestDao {

    private final JdbcTemplate jdbcTemplate;
    private final String ALL_COLUMNS_WITH_EMPLOYEE_NAME = "SELECT request_id, employee.employee_id, employee.employee_name, date, request.manager_message, request.message,request.workplace_id, is_emergency, is_pending, is_covered, is_approved from request \n" +
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
    public List<Request> getCurrentAndFutureRequests() {
        List<Request> requestList = new ArrayList<>();
        String sql = ALL_COLUMNS_WITH_EMPLOYEE_NAME + "WHERE date >= CURRENT_DATE - INTERVAL '1 day';";
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
        String sql = "INSERT INTO request(employee_id, date, is_emergency, is_pending, is_covered, is_approved, message, workplace_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING request_id;";
        try {
            newId = jdbcTemplate.queryForObject(sql, int.class, request.getEmployeeId(), request.getDate(), request.isEmergency(), request.isPending(), request.isCovered(), request.isApproved(), request.getEmployeeMessage(),request.getWorkPlaceId());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return getRequestByRequestId(newId);
    }

    @Override
    public Request updateRequest(Request request) {
        Request requestUpdate = null;
        String sql = "UPDATE request " +
                "SET employee_id = ?, date = ?, message = ?, manager_message = ?, " +
                "workplace_id = ?, is_emergency = ?, is_pending = ?, is_covered = ?, is_approved = ? " +
                "WHERE request_id = ?";

        try {
            int numberOfRows = jdbcTemplate.update(sql, request.getEmployeeId(), request.getDate(), request.getEmployeeMessage(), request.getManagerMessage(),
                    request.getWorkPlaceId(),
                    request.isEmergency(),
                    request.isPending(),
                    request.isCovered(),
                    request.isApproved(),
                    request.getRequestId());

            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                requestUpdate = getRequestByRequestId(request.getRequestId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return requestUpdate;

    }

    @Override
    public int deleteRequestById(int requestId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM request WHERE request_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, requestId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return numberOfRows;

    }

    private Request mapRowsToRequests(SqlRowSet rowSet) {
        Request request = new Request();
        request.setRequestId(rowSet.getInt("request_id"));
        request.setEmployeeName(rowSet.getString("employee_name"));
        request.setEmployeeId(rowSet.getInt("employee_id"));
        if (rowSet.getObject("date")==null){
            request.setDate(null);
        } else {
            request.setDate(rowSet.getDate("date").toLocalDate());
        }
        request.setEmergency(rowSet.getBoolean("is_emergency"));
        request.setEmployeeMessage(rowSet.getString("message"));
        request.setCovered(rowSet.getBoolean("is_covered"));
        request.setPending(rowSet.getBoolean("is_pending"));
        request.setApproved(rowSet.getBoolean("is_approved"));
        request.setManagerMessage(rowSet.getString("manager_message"));
        request.setWorkPlaceId(rowSet.getInt("workplace_id"));

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


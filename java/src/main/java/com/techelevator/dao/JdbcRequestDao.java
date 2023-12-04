package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Request;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JdbcRequestDao implements RequestDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Request> getAllRequests() {
        List<Request> requestList = new ArrayList<>();
        String sql = "SELECT * FROM request\n";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Request request = mapRowRequest(results);
                requestList.add(request);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        //TODO
        return requestList;
    }

    public Request getRequestById(int employeeId) {
        Request requestById = new Request();
        String sql = "SELECT * FROM request WHERE employee_Id = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, employeeId);
            if ((result.next())) {
                requestById = mapRowRequest(result);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return requestById;
    }


    @Override
    public Request createRequest(Request request) {
        Request newRequest = null;
        String sql = "INSERT INTO request(employee_name, date, workplace_id, is_emergency, is_pending, is_approved) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int rowAffected = jdbcTemplate.update(sql,request.getEmployeeName(), Date.valueOf(request.getDate()),request.getWorkplaceId(), request.isEmergency(), request.isPending(), request.isApproved());
            if(rowAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                newRequest = getRequestById(request.getEmployeeId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        //TODO
        return newRequest;
    }


    //TODO create mapRowsToRequest
    private Request mapRowRequest(SqlRowSet result) {
        Request request = new Request();
        request.setRequestId(result.getInt("request_id"));
        request.setEmployeeId(result.getInt("employee_id"));
        request.setEmployeeName(result.getString("employee_name"));
        request.setDate(result.getDate("date").toLocalDate());
        request.setWorkplaceId(result.getInt("workplace_id"));
        request.setEmergency(result.getBoolean("is_emergency"));
        request.setPending(result.getBoolean("is_pending"));
        request.setApproved(result.getBoolean("is_approved"));

        return request;
    }
}


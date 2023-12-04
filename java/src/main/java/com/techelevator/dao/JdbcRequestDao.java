package com.techelevator.dao;

import com.techelevator.model.Request;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcRequestDao implements RequestDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Request> getAllRequests() {
        //TODO
        return null;
    }

    @Override
    public Request createRequest(Request request) {
        //TODO
        return null;
    }


    //TODO create mapRowsToRequest
}

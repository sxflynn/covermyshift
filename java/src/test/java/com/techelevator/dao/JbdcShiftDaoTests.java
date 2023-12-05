package com.techelevator.dao;

import com.techelevator.model.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JbdcShiftDaoTests extends BaseDaoTests {

    private JdbcShiftDao dao;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcShiftDao(jdbcTemplate);
    }

    @Test
    public void get_all_shifts_returns_shifts(){

    }



}

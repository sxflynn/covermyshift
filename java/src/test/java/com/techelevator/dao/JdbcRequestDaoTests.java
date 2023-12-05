package com.techelevator.dao;

import com.techelevator.model.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcRequestDaoTests extends BaseDaoTests{

    //create DUMMY REQUEST
    //CREATE DUMMY EMPLOYEE

    private static final Request REQUEST_1_NOID = new Request(0,1,"Steve C.", LocalDate.parse("2023-12-01"),false,true,false,"My message",false);

    private static final Request REQUEST_1 = new Request(1,1,"Steve C.", LocalDate.parse("2023-12-01"),false,true,false,"My message",false);
    private static final Request REQUEST_2 = new Request(2,1,"Steve C.", LocalDate.parse("2023-12-02"),false,true,false, "My message",false);

    private static final Request FUTURE_REQUEST = new Request(3,1,"Steve C.", LocalDate.parse("2024-12-02"),false,true,false, "My message",false);


    private JdbcRequestDao dao;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcRequestDao(jdbcTemplate);
    }

    @Test
    public void get_request_by_id_returns_correct_request(){
        Request testRequest = REQUEST_2;
        Request realRequest = dao.getRequestByRequestId(2);
        assertRequestsMatch(testRequest,realRequest);
    }

    @Test
    public void get_future_request_only_returns_future_requests(){
        Request testRequest = FUTURE_REQUEST;
        List<Request> testRequestList = new ArrayList<>();
        testRequestList.add(testRequest);
        List<Request> realRequestList = dao.getCurrentAndFutureRequests();
        assertRequestsMatch(testRequestList.get(0),realRequestList.get(0));
    }



    @Test
    public void get_request_by_id_with_invalid_id_returns_null_request(){
        Request realrequest = dao.getRequestByRequestId(99);
        Assert.assertNull("Request should be null",realrequest);
    }

    @Test
    public void create_request_should_returns_new_request(){
        Request testRequest = REQUEST_1_NOID;
        Request realRequest = dao.createRequest(testRequest);
        int requestId = realRequest.getRequestId();
        testRequest.setRequestId(requestId);
        Assert.assertTrue("Request ID should be greater than 0, but is " + realRequest.getRequestId(),requestId > 0);
        Assert.assertNotNull("Returned request should not be null",realRequest);
        assertRequestsMatch(testRequest,realRequest);
    }

    @Test
    public void get_all_requests_returns_request(){
        List<Request> testRequesList = new ArrayList<>();
        testRequesList.add(REQUEST_1);
        testRequesList.add(REQUEST_2);

        List<Request> realRequestList = dao.getAllRequests();

        Assert.assertEquals("Expected 2 requests, but got " + realRequestList.size(),testRequesList.size(),realRequestList.size());
        assertRequestsMatch(testRequesList.get(0),realRequestList.get(0));
        assertRequestsMatch(testRequesList.get(1),realRequestList.get(1));
    }

    public void assertRequestsMatch(Request requestExpected, Request realRequest){
        Assert.assertEquals("Expected date: " + requestExpected.getDate() + ", but was: " + realRequest.getDate(), requestExpected.getDate(), realRequest.getDate());
        Assert.assertEquals("Expected employee ID: " + requestExpected.getEmployeeId() + ", but was: " + realRequest.getEmployeeId(), requestExpected.getEmployeeId(), realRequest.getEmployeeId());
        Assert.assertEquals("Expected employee name: " + requestExpected.getEmployeeName() + ", but was: " + realRequest.getEmployeeName(), requestExpected.getEmployeeName(), realRequest.getEmployeeName());
        Assert.assertEquals("Expected request ID: " + requestExpected.getRequestId() + ", but was: " + realRequest.getRequestId(), requestExpected.getRequestId(), realRequest.getRequestId());
        Assert.assertEquals("Expected approved status: " + requestExpected.isApproved() + ", but was: " + realRequest.isApproved(), requestExpected.isApproved(), realRequest.isApproved());
        Assert.assertEquals("Expected emergency status: " + requestExpected.isEmergency() + ", but was: " + realRequest.isEmergency(), requestExpected.isEmergency(), realRequest.isEmergency());
        Assert.assertEquals("Expected message: " + requestExpected.getMessage() + ", but was: " + realRequest.getMessage(), requestExpected.getMessage(), realRequest.getMessage());
        Assert.assertEquals("Expected pending status: " + requestExpected.isPending() + ", but was: " + realRequest.isPending(), requestExpected.isPending(), realRequest.isPending());
        Assert.assertEquals("Expected covered status: " + requestExpected.isCovered() + ", but was: " + realRequest.isCovered(), requestExpected.isCovered(), realRequest.isCovered());
    }



//    public Request getAllRequests();

    // GetRequestById

    // CreateRequest
//    public Request createRequest(Request request);

}

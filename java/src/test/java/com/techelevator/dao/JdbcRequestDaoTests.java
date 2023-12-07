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

    private static final Request REQUEST_1_NOID = new Request(
            0,
            1,
            "Steve C.",
            LocalDate.parse("2023-12-01"),
            false,
            true,
            false,
            "My message",
            false,
            1 // Default workPlaceId
    );

    private static final Request REQUEST_1 = new Request(
            1,
            1,
            "Steve C.",
            LocalDate.parse("2023-12-01"),
            false,
            true,
            false,
            "My message",
            false,
            1 // Default workPlaceId
    );

    private static final Request REQUEST_1_APPROVED = new Request(
            1,
            1,
            "Steve C.",
            LocalDate.parse("2023-12-01"),
            false,
            false,
            true,
            "My message",
            false,
            1 // Default workPlaceId
    );

    private static final Request REQUEST_2 = new Request( //nonemergency
            2,
            1,
            "Steve C.",
            LocalDate.parse("2023-12-02"),
            false,
            true,
            false,
            "I want to chill on the beach this day",
            false,
            1
    );

    private static final Request REQUEST_2_DENIED_NOTEMERGENCY = new Request(
            2,
            1,
            "Steve C.",
            LocalDate.parse("2023-12-02"),
            false,
            false,
            false,
            "I want to chill on the beach this day",
            true,
            1
    );

    private static final Request FUTURE_REQUEST = new Request( //add manager message
            3,
            2,
            "Rachelle R.",
            LocalDate.parse("2024-12-02"),
            false,
            true,
            false,
            "My message",
            false,
            1
    );

    private static final Request FUTURE_REQUEST_DENIED = new Request( //no message
            3,
            2,
            "Rachelle R.",
            LocalDate.parse("2024-12-02"),
            false,
            true,
            false,
            "My message",
            false,
            1
    );



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
        List<Request> testRequestList = new ArrayList<>();
        testRequestList.add(REQUEST_1);
        testRequestList.add(REQUEST_2);
        testRequestList.add(FUTURE_REQUEST);

        List<Request> realRequestList = dao.getAllRequests();

        Assert.assertEquals("Expected 4 requests, but got " + realRequestList.size(),4,realRequestList.size());
        assertRequestsMatch(testRequestList.get(0),realRequestList.get(0));
        assertRequestsMatch(testRequestList.get(1),realRequestList.get(1));
    }
@Test
    public void update_request_to_denied_nonemergency_returns_message(){
        Request testRequest = REQUEST_2;
        Request deniedTestRequest = REQUEST_2_DENIED_NOTEMERGENCY;
        Request realRequest = dao.getRequestByRequestId(deniedTestRequest.getRequestId());
        dao.updateRequest(deniedTestRequest);
        assertRequestsMatch(deniedTestRequest,realRequest);
    }

    @Test
    public void update_future_request_to_denied_no_message(){
        Request testRequest = FUTURE_REQUEST;
        Request deniedTestRequest = FUTURE_REQUEST_DENIED;
        deniedTestRequest.setManagerMessage("No");
        Request realRequest = dao.getRequestByRequestId(deniedTestRequest.getRequestId());
        dao.updateRequest(deniedTestRequest);
        assertRequestsMatch(deniedTestRequest,realRequest);
    }

    @Test
    public void update_request_to_approved_changes_request(){
        Request testRequest = REQUEST_1;
        Request approvedTestRequest = REQUEST_1_APPROVED;
        Request realRequest = dao.getRequestByRequestId(approvedTestRequest.getRequestId());
        dao.updateRequest(approvedTestRequest);
        assertRequestsMatch(approvedTestRequest,realRequest);
    }
@Test
    public void delete_request_by_id_deletes_correct_request(){
        Request testRequest = REQUEST_2;
        int rowsAffected = dao.deleteRequestById(testRequest.getRequestId());
        Assert.assertEquals(1,rowsAffected);
        Assert.assertNull(dao.getRequestByRequestId(testRequest.getRequestId()));
    }

    public void assertRequestsMatch(Request requestExpected, Request realRequest){
        Assert.assertEquals("Expected date: " + requestExpected.getDate() + ", but was: " + realRequest.getDate(), requestExpected.getDate(), realRequest.getDate());
        Assert.assertEquals("Expected employee ID: " + requestExpected.getEmployeeId() + ", but was: " + realRequest.getEmployeeId(), requestExpected.getEmployeeId(), realRequest.getEmployeeId());
        Assert.assertEquals("Expected employee name: " + requestExpected.getEmployeeName() + ", but was: " + realRequest.getEmployeeName(), requestExpected.getEmployeeName(), realRequest.getEmployeeName());
        Assert.assertEquals("Expected request ID: " + requestExpected.getRequestId() + ", but was: " + realRequest.getRequestId(), requestExpected.getRequestId(), realRequest.getRequestId());
        Assert.assertEquals("Expected approved status: " + requestExpected.isApproved() + ", but was: " + realRequest.isApproved(), requestExpected.isApproved(), realRequest.isApproved());
        Assert.assertEquals("Expected emergency status: " + requestExpected.isEmergency() + ", but was: " + realRequest.isEmergency(), requestExpected.isEmergency(), realRequest.isEmergency());
        Assert.assertEquals("Expected employee message: " + requestExpected.getEmployeeMessage() + ", but was: " + realRequest.getEmployeeMessage(), requestExpected.getEmployeeMessage(), realRequest.getEmployeeMessage());
        Assert.assertEquals("Expected manager message: " + requestExpected.getManagerMessage() + ", but was: " + realRequest.getManagerMessage(), requestExpected.getManagerMessage(), realRequest.getManagerMessage());
        Assert.assertEquals("Expected pending status: " + requestExpected.isPending() + ", but was: " + realRequest.isPending(), requestExpected.isPending(), realRequest.isPending());
        Assert.assertEquals("Expected covered status: " + requestExpected.isCovered() + ", but was: " + realRequest.isCovered(), requestExpected.isCovered(), realRequest.isCovered());
        Assert.assertEquals("Expected workPlaceId: " + requestExpected.getWorkPlaceId() + ", but was: " + realRequest.getWorkPlaceId(), requestExpected.getWorkPlaceId(), realRequest.getWorkPlaceId());
        if (requestExpected.getShiftId()!=null){
            Assert.assertEquals("Expected shiftId list size: " + requestExpected.getShiftId().size() + ", but was: " + realRequest.getShiftId().size(), requestExpected.getShiftId().size(), realRequest.getShiftId().size());
        }
    }





//    public Request getAllRequests();

    // GetRequestById

    // CreateRequest
//    public Request createRequest(Request request);

}

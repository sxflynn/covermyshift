package com.techelevator.dao;

import com.techelevator.model.Request;

import java.util.List;

public interface RequestDao {

    // GetAllRequests
    public List<Request> getAllRequests();

    // Get Request By RequestId
    public Request getRequestByRequestId(int requestId);

    public List<Request> getCurrentAndFutureRequests();

    // CreateRequest
    public Request createRequest(Request request);

    // UpdateRequestByRequest

    // DeleteRequestById

    // GetRequestByEmployeeId
    public Request getRequestByEmployeeId(int employeeId);

    //LATER -- GetAllRequestsByEmployee
    //LATER -- GetAllRequestByWorkplace
}
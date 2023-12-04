package com.techelevator.dao;

import com.techelevator.model.Request;

import java.util.List;

public interface RequestDao {

    // GetAllRequests
    public List<Request> getAllRequests();

    // GetRequestById

    // CreateRequest
    public Request createRequest(Request request);

    // UpdateRequestByRequest

    // DeleteRequestById


    //LATER -- GetAllRequestsByEmployee
    //LATER -- GetAllRequestByWorkplace
}

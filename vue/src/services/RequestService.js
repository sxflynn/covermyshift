import axios from 'axios';


export default {
  // Fetch all requests
  list() {
    return axios.get('/request');
  },

  // Create a new request
  create(request) {
    return axios.post('/request', request);
  },

  // Update a request
  update(request) {
    return axios.put(`/request`, request);
  },

  // Fetch current and future requests
  getCurrentAndFutureRequests() {
    return axios.get('/request/current');
  },

  deleteRequestById(requestId){
    return axios.delete(`/request/delete/${requestId}`);
}


};

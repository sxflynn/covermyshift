import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
  // Fetch all requests
  list() {
    return http.get('/request');
  },

  // Create a new request
  create(request) {
    return http.post('/request', request);
  },

  // Update a request
  update(request) {
    return http.put(`/request`, request);
  },

  // Fetch current and future requests
  getCurrentAndFutureRequests() {
    return http.get('/request/current');
  },

};

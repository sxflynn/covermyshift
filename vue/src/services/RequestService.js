import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {

  list() {
    return http.get('/request');
  },

  get(id) {
    return http.get(`/request/${id}`);
  },

  update(id, coverReq) {
    return http.put(`//${id}`, coverReq);
  },
  create(coverReq) {
  return http.post('/request', coverReq);
},
  delete(id) {
    return http.delete(`/request/${id}`);
  }
}

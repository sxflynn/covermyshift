import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000/shift"
});

export default {
  getAllShifts() {
    return http.get('/all');
  },
  getAllCurrentShifts() {
    return http.get('/current');
  },
  updateShift(shift) {
    return http.put('', shift);
  },
  getAllShiftsByEmployeeId(employeeId) {
    return http.get(`/employee/${employeeId}`);
  },
  getShiftByShiftId(shiftId) {
    return http.get(`/shifts/${shiftId}`);
  },
  getAllUncoveredShifts() {
    return http.get('/uncovered');
  },
  getAllShiftsByRequestId(requestId) {
    return http.get(`/request/${requestId}`);
  }
};

import axios from 'axios';


export default {
  getAllShifts() {
    return axios.get('/shift/all');
  },
  getAllCurrentShifts() {
    return axios.get('/current');
  },
  updateShift(shift) {
    return axios.put('/shift', shift);
  },
  getAllShiftsByEmployeeId(employeeId) {
    return axios.get(`/employee/${employeeId}`);
  },
  getShiftByShiftId(shiftId) {
    return axios.get(`/shift/${shiftId}`);
  },
  getAllUncoveredShifts() {
    return axios.get('/shift/uncovered');
  },
  getAllShiftsByRequestId(requestId) {
    return axios.get(`shift/request/${requestId}`);
  }
};

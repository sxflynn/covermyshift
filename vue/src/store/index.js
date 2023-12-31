import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import RequestService from '../services/RequestService';
import ShiftService from '../services/ShiftService';
import AuthService from '../services/AuthService';
import EmailService from '../services/EmailService';


export function createStore(currentToken, currentUser, currentEmployee) {

  let store = _createStore({
    state: {
      listReqArr: [],
      listAllShiftArr: [],
      listShiftArr: [],
      listUncoveredShiftsArr: [],
      token: currentToken || '',
      user: currentUser || {},
      loggedInEmployee: currentEmployee || {}
    },
    mutations: {
      SET_ALL_CURRENT_SHIFTS_ARR(state, allShifts) {
        state.listAllShiftArr = allShifts;
        console.log("state.listAllShiftArr is ", state.listAllShiftArr)
      },
      SET_UNCOVERED_SHIFTS_ARR(state, uncoveredShifts) {
        state.listUncoveredShiftsArr = uncoveredShifts;
      },
      SET_LIST_REQ_ARR(state, list) {
        state.listReqArr = list;
      },
      UPDATE_REQUEST_SUCCESS(state, responseData) {
        state.listReqArr.push(responseData);
      },
      UPDATE_REQUEST_FAILURE(state, error) {
        console.error('Request failed:', error);
      },
      SET_LIST_SHIFT_ARR(state, list) {
        state.listShiftArr = list;
      },
      UPDATE_SHIFT_SUCCESS(state, responseData) {
        const index = state.listShiftArr.findIndex(shift => shift.id === responseData.id);
        if (index !== -1) {
          state.listShiftArr.splice(index, 1, responseData);
        } else {
          console.error('Shift not found in listShiftArr');
        }
      },
      UPDATE_SHIFT_FAILURE(state, error) {
        console.error('Shift update failed:', error);
      },
      DELETE_REQUEST(state, requestId) {

        const index = state.listReqArr.findIndex(req => req.requestId === requestId);

        if (index !== -1) {
          state.listReqArr.splice(index, 1);
        } else {
          console.error('Request not found: ', requestId);
        }
      },
      SET_EMPLOYEE_INFO(state, employeeData) {
        state.loggedInEmployee = employeeData;
        localStorage.setItem('loggedInEmployee', JSON.stringify(employeeData));
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        localStorage.removeItem('loggedInEmployee')
        state.token = '';
        state.user = {};
        state.loggedInEmployee = {};
        axios.defaults.headers.common = {};
        state.listReqArr = [],
        state.listAllShiftArr = [],
        state.listShiftArr = [],
        state.listUncoveredShiftsArr = [],
        state.loggedInEmployee = {}
      },

    },
    actions: {
      deleteRequestById({ commit }, requestId) {
        return RequestService.deleteRequestById(requestId)
          .then(response => {
            commit('DELETE_REQUEST', requestId);
          })
          .catch(error => {
            console.error('Error deleting request', error);
            throw error;
          });
      },
      fetchAllUncoveredShifts({ commit }) {
        return ShiftService.getAllUncoveredShifts()
          .then(response => {
            commit('SET_UNCOVERED_SHIFTS_ARR', response.data);
          })
          .catch(error => {
            console.error('Error fetching uncovered shifts:', error);
            throw error;
          });
      },
      fetchMyIdentity({ commit }) {
        return AuthService.whoami()
          .then(response => {
            commit('SET_EMPLOYEE_INFO', response.data);
          })
          .catch(error => {
            console.error('Error fetching identity:', error);
            throw error;
          })
      },
      fetchListReqArr({ commit }) {
        return RequestService.list()
          .then(response => {
            commit('SET_LIST_REQ_ARR', response.data);
          })
          .catch(error => {
            console.error('Error fetching requests:', error);
            throw error;
          })
      },
      createNewRequest({ commit }, { requestData, sendEmail }) {
        console.log("create new request requestData is ", requestData)
        return RequestService.create(requestData)
          .then(response => {
            if (sendEmail) {
              EmailService.sendNewRequestEmail(this.state.loggedInEmployee, response, sendEmail)
                .then(function (response) {
                  // Fire notification that email was sent
                }, function (error) {
                  console.error('Email failed to send', error);
                });
            }
            commit('UPDATE_REQUEST_SUCCESS', response.data);
            return response;
          })
          .catch(error => {
            commit('UPDATE_REQUEST_FAILURE', error);
            throw error;
          });
      },
      updateRequest({ commit, state }, requestData) {
        return RequestService.update(requestData)
          .then(response => {
            const index = state.listReqArr.findIndex(req => req.requestId === requestData.requestId);
            if (index !== -1) {
              let updatedRequests = [...state.listReqArr];
              updatedRequests[index] = response.data;
              commit('SET_LIST_REQ_ARR', updatedRequests);
            } else {
              console.error('Request not found in listReqArr');
            }
            return response;
          })
          .catch(error => {
            commit('UPDATE_REQUEST_FAILURE', error);
            throw error;
          })
      },
      fetchListShiftArr({ commit }) {
        return ShiftService.getAllShifts()
          .then(response => {
            if (response.status === 204) {
              commit('SET_LIST_SHIFT_ARR', []);
            } else if (response.status === 200) {
              commit('SET_LIST_SHIFT_ARR', response.data);
            }
          })
          .catch(error => {
            console.error('Error fetching shifts:', error);
            throw error;
          });
      },
      fetchCurrentListShiftArr({ commit }) {
        return ShiftService.getAllCurrentShifts()
          .then(response => {
            if (response.status === 204) {
              commit('SET_ALL_CURRENT_SHIFTS_ARR', []);
            } else if (response.status === 200) {
              commit('SET_ALL_CURRENT_SHIFTS_ARR', response.data);
            }
          })
          .catch(error => {
            console.error('Error fetching shifts:', error);
            throw error;
          });
      },
      updateShift({ commit }, shiftData) {
        return ShiftService.updateShift(shiftData)
          .then(response => {
            commit('UPDATE_SHIFT_SUCCESS', response.data);
            // TODO fix bug where it adds shift to store instead of update
            return response;
          })
          .catch(error => {
            commit('UPDATE_SHIFT_FAILURE', error);
            throw error;
          });
      },

      fetchCurrentAndFutureRequests({ commit }) {
        console.log("fetchCurrentAndFutureRequests - Starting to fetch requests");

        return RequestService.getCurrentAndFutureRequests()
          .then(response => {
            if (response.status === 204) {
              commit('SET_ALL_CURRENT_SHIFTS_ARR', []);
            } else if (response.status === 200) {
              commit('SET_ALL_CURRENT_SHIFTS_ARR', response.data);
            }
          })
          .catch(error => {
            console.error('Error fetching current and future requests:', error);
            throw error;
          });
      },


    },
  });
  return store;
}

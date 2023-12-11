import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import RequestService from '../services/RequestService';
import ShiftService from '../services/ShiftService';
import AuthService from '../services/AuthService';

export function createStore(currentToken, currentUser, currentEmployee) {

  let store = _createStore({
    state: {
      listReqArr: [],
      listShiftArr: [],
      token: currentToken || '',
      user: currentUser || {},
      loggedInEmployee: currentEmployee || {}
    },
    mutations: {
      SET_LIST_REQ_ARR(state, list) {
        state.listReqArr = list;
        console.log("listReqArr is now size ", state.listReqArr.length)
        console.log('this.$store.state.listReqArr is ', state.listReqArr);
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
        console.log("UPDATE_SHIFT_SUCCESS responseData is ", responseData)
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
      },

    },
    actions: {
      fetchMyIdentity({ commit }) {
        return AuthService.whoami()
          .then(response => {
            console.log("who am I:", response.data); // return employee object
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
            console.log("Fetched data:", response.data); // Debugging line

            commit('SET_LIST_REQ_ARR', response.data);
          })
          .catch(error => {
            console.error('Error fetching requests:', error);
            throw error;
          })
      },
      createNewRequest({ commit }, requestData) {
        return RequestService.create(requestData)
          .then(response => {
            commit('UPDATE_REQUEST_SUCCESS', response.data);
            console.log("createNewRequest response is ", response)
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
              // Replace the request at the found index with the updated data
              let updatedRequests = [...state.listReqArr];
              updatedRequests[index] = response.data;

              // Commit a mutation to update listReqArr with the updated list of requests
              commit('SET_LIST_REQ_ARR', updatedRequests);
            } else {
              // Optionally handle the case where the request is not found
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
            commit('SET_LIST_SHIFT_ARR', response.data);
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
      // Correctly placed the fetchCurrentAndFutureRequests action within the actions object
      fetchCurrentAndFutureRequests({ commit }) {
        return RequestService.getCurrentAndFutureRequests()
          .then(response => {
            commit('SET_LIST_REQ_ARR', response.data);
          })
          .catch(error => {
            console.error('Error fetching current and future requests:', error);
            throw error;
          });
      },
      // other actions...
    },
  });
  return store;
}

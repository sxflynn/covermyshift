import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import RequestService from '../services/RequestService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      listReqArr: [],
      token: currentToken || '',
      user: currentUser || {}
    },
    mutations: {
      SET_LIST_REQ_ARR(state,list){
        state.listReqArr = list;
        console.log("listReqArr is now size ", state.listReqArr.length)
      },
      UPDATE_REQUEST_SUCCESS(state, responseData) {
        state.listReqArr.push(responseData);
      },
      UPDATE_REQUEST_FAILURE(state, error) {
        console.error('Request failed:', error);
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
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      LISTSHIFTS(state){
          // TODO - Do an add, then navigate Home on success.
          // For errors, call handleErrorResponse
          RequestService.list()
            .then((response) => {
              this.state.listReqArr = response.data;
              if (response.status === 201 || response.status === 200) {
                this.$router.push({ name: "DashboardView" });
              }
            })
            .catch((error) => {
              // this.handleErrorResponse(error, "adding");
            });
        },
      
    },
    actions: {
      fetchListReqArr({commit}){
        return RequestService.list()
        .then(response =>{
          commit('SET_LIST_REQ_ARR',response.data);
        })
        .catch(error=>{
          console.error('Error fetching requests:',error);
          throw error;
        })
      },
      createNewRequest({ commit }, requestData) {
        return RequestService.create(requestData)
          .then(response => {
            commit('UPDATE_REQUEST_SUCCESS', response.data);
            return response;
          })
          .catch(error => {
            commit('UPDATE_REQUEST_FAILURE', error);
            throw error;
          });
      },

      // other actions...
    },
  });
  return store;
}

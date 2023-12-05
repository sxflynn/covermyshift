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
              console.log(response.data);
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
  });
  return store;
}

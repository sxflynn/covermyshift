<template>
    <v-card flat>
      <v-card-title class="d-flex align-center pe-2">
        <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;
        List of Requests
  
        <v-spacer></v-spacer>
  
        <v-text-field
          v-model="search"
          prepend-inner-icon="mdi-magnify"
          density="compact"
          label="Search"
          single-line
          flat
          hide-details
          variant="solo-filled"
        ></v-text-field>
      </v-card-title>
  
      <v-divider></v-divider>
      <v-data-table v-model:search="search" :items="requests">
        <template v-slot:header.stock>
          <div class="text-end">Stock</div>
        </template>
  
        <!-- <template v-slot:item.image="{ item }">
          <v-card class="my-2" elevation="2" rounded>
            <v-img
              :src="`https://cdn.vuetifyjs.com/docs/images/graphics/gpus/${item.image}`"
              height="64"
              cover
            ></v-img>
          </v-card>
        </template> -->
  
        <template v-slot:item.approved="{ item }">
          <div class="text-end">
            <v-chip
              :color="item.approved ? 'green' : 'red'"
              :text="item.approved ? 'Approved' : 'Unapproved'"
              class="text-uppercase"
              label
              size="large"
            ></v-chip>
          </div>
        </template>
        <template v-slot:item.requestType="{ item }">
          <div class="text-end">
            <v-chip
              :color="item.approved ? 'red' : 'blue'"
              :text="item.approved ? 'Emergency' : 'Vacation'"
              class="text-uppercase"
              label
              size="x-small"
            ></v-chip>
          </div>
        </template>
      </v-data-table>
    </v-card>
    <div>
    <!-- <ul class="shiftList">
      <span id="nameColumn">Name</span>
      <span id="dateColumn"> Date</span>
      <li
        v-for="item in this.$store.state.listReqArr"
        v-bind:key="item"
        class="listRow"
      >
        <span id="shiftName">{{ item.employeeName }}</span> :
        <span id="shiftDate">{{ item.date }}</span>
        <span id="shiftEmergency">{{ item.emergency }}</span>
        <span id="shiftMessage">{{ item.message }}</span>
        <v-btn id="accept">Accept</v-btn>
        <v-btn id="reject">Reject</v-btn>
      </li>
    </ul> -->
  </div>
  </template>
  <script>
    export default {
      data () {
        return {
          search: '',
          requests: [
          {
          "requestId": 1,
          "employeeId": 1,
          "employeeName": "Steve C.",
          "date": "2023-12-01",
          "message": "hello",
          "emergency": false,
          "covered": false,
          "approved": false,
          "pending": true
      }, {
          "requestId": 1,
          "employeeId": 1,
          "employeeName": "Steve C.",
          "date": "2023-12-10",
          "message": "My message",
          "emergency": false,
          "covered": false,
          "approved": false,
          "pending": true
      }, {
          "requestId": 1,
          "employeeId": 1,
          "employeeName": "Steve C.",
          "date": "2023-12-15",
          "message": "My message",
          "emergency": false,
          "covered": false,
          "approved": false,
          "pending": true
      }
          ],
        }
      }, created() {
    this.$store.commit("LISTSHIFTS");
  },
    }
  </script>
  <style>
  </style>
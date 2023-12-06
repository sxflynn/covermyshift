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
      <v-data-table v-model:search="search" :items="requests" >
        <template v-slot:header="{ headers }">
    <thead>
      <tr>
        <th v-for="header in headers" :key="header.value">
          <div class="text-end">
            {{ header.value === 'Emergency' ? 'Request Type' : header.text }}
            {{ header.value === 'EmployeeName' ? 'Name' :header.text }}
          </div>
        </th>
      </tr>
    </thead>
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
          <div class="approved">
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
        <template v-slot:item="{ item }">
      <tr>
        <td :class="{ 'custom-emergency-font': item.emergency }">
          <div class="text-end">
            {{ item.emergency ? 'Emergency' : 'Vacation' }}
          </div>
        </td>
        <!-- Add more columns as needed -->
      </tr>
    </template>
      </v-data-table>
    </v-card>
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
      }
    }
  </script>
  <style scoped>
  .approved{
    font-family: fantasy;
    font-style: italic;
  }
  .custom-emergency-font {
  font-family:cursive
  
}
  </style>
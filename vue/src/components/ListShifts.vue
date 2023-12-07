<template>
    <v-card flat>
      <v-card-title class="d-flex align-center pe-2">
        <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;
        List of Requests
  
        <v-spacer></v-spacer>
  
        <v-text-field v-model="search" prepend-inner-icon="mdi-magnify" density="compact" label="Search" single-line flat
          hide-details variant="solo-filled"></v-text-field>
      </v-card-title>
  
      <v-divider></v-divider>
      <!-- TODO: Add custom headers using the headers prop -->
      <!-- TODO: Customize the items-per-page -->
      <v-data-table v-model:search="search" :items="requests" :items-per-page="1000">
  
        <template v-slot:item.shiftId="{ item }">
          <div class="text-end">
            <v-chip :text="item.shiftId"
              class="text-lowercase" label size="large"></v-chip>
          </div>
        </template>
  
        <template v-slot:item.employeeId="{ item }">
          <div class="text-end">
            <v-chip :text="item.employeeId"
              class="text-lowercase" label size="large"></v-chip>
          </div>
        </template>
  
        <template v-slot:item.shiftOwnerName="{ item }">
          <div class="text-end">
            <v-chip :text="item.shiftOwnerName"
            label size="large"></v-chip>
          </div>
        </template>
  
  
        <template v-slot:item.shiftVolunteerName="{ item }">
          <div class="text-end">
            <v-chip :text="item.shiftVolunteerName"
              class="text-lowercase" label size="large"></v-chip>
          </div>
        </template>
  
        <template v-slot:item.startTime="{ item }">
          <div class="text-end">
            <v-chip :text="item.startTime"
            label size="large"></v-chip>
          </div>
        </template>
        <template v-slot:item.endTime="{ item }">
          <div class="text-end">
            <v-chip :text="item.endTime"
            label size="large"></v-chip>
          </div>
        </template>
  
       
        
        <template v-slot:item.covered="{ item }">
          <div class="text-end">
            <v-chip :color="item.covered ? 'green' : 'red'" :text="item.covered ? 'Covered' : 'Uncovered'"
              class="text-uppercase" label size="large"></v-chip>
          </div>
        </template>
      </v-data-table>
    </v-card>
  </template>
  
  <script>
  export default {
    data() {
      return {
        headers:[
        { text: 'Request ID', value: 'requestId', align: 'start' },
        { text: 'Employee ID', value: 'employeeId', align: 'start' },
        { text: 'Employee Name', value: 'employeeName', align: 'start' },
        { text: 'Date', value: 'date', align: 'start' },
        { text: 'Message', value: 'message', align: 'start' },
        { text: 'Emergency', value: 'emergency', align: 'center' },
        { text: 'Covered', value: 'covered', align: 'center' },
        { text: 'Approved', value: 'approved', align: 'center' },
        { text: 'Pending', value: 'pending', align: 'center' },
        ],
        search: '',
  
        requests: [
    {
        "shiftId": 1,
        "shiftOwnerId": 1,
        "shiftOwnerName": "Steve C.",
"shiftVolunteerId" : null,
"shiftVolunteerName":"",
        "startTime": "2022-12-05T08:00:00",
        "endTime": "2022-12-05T16:00:00",
        "covered": false
    },
    {
        "shiftId": 3,
        "shiftOwnerId": 2,
        "shiftOwnerName": "Rachelle R.",
"shiftVolunteerId":null,
"shiftVolunteerName":"",
        "startTime": "2024-12-07T08:00:00",
        "endTime": "2024-12-07T16:00:00",
        "covered": false
    }

],
      }
    }, 
    computed:{
      reversedRequests(){
        console.log(this.requests);
        return [this.requests].reverse();
      }
    }
  }
  
  
  </script>
  <style></style>
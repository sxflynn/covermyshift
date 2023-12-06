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
    <v-data-table v-model:search="search" :items="reversedRequests" :items-per-page="1000">

      <template v-slot:item.requestId="{ item }">
        <div class="text-end">
          <v-chip :text="item.requestId"
            class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeId="{ item }">
        <div class="text-end">
          <v-chip :text="item.employeeId"
            class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeName="{ item }">
        <div class="text-end">
          <v-chip :text="item.employeeName"
          label size="large"></v-chip>
        </div>
      </template>


      <template v-slot:item.date="{ item }">
        <div class="text-end">
          <v-chip :text="item.date"
            class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.message="{ item }">
        <div class="text-end">
          <v-chip :text="item.message"
          label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.emergency="{ item }">
        <div class="text-end">
          <v-chip :color="item.emergency ? 'red' : 'blue'" :text="item.emergency ? 'Emergency' : 'Vacation'"
            class="text-uppercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.approved="{ item }">
        <div class="text-end">
          <v-chip :color="item.approved ? 'green' : 'red'" :text="item.approved ? 'Approved' : 'Unapproved'"
            class="text-uppercase" label size="large"></v-chip>
        </div>
      </template>
      
      <template v-slot:item.covered="{ item }">
        <div class="text-end">
          <v-chip :color="item.covered ? 'green' : 'red'" :text="item.covered ? 'Covered' : 'Uncovered'"
            class="text-uppercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.pending="{ item }">
        <div class="text-end">
          <v-chip :color="item.pending ? 'red' : 'green'" :text="item.pending ? 'Pending' : 'Finalized'"
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
          "requestId": 1,
          "employeeId": 1,
          "employeeName": "Steve C.",
          "date": "2023-12-01",
          "message": "hello",
          "emergency": false,
          "covered": false,
          "approved": false,
          "pending": true
        },
      ],
    }
  }, 
  computed:{
    reversedRequests(){
      return [...this.$store.state.listReqArr].reverse();
    }
  }
}



</script>
<style></style>
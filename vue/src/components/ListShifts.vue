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
    <v-data-table v-model:search="search" :items="shifts" :items-per-page="1000">

      <template v-slot:item.shiftId="{ item }">
        <div class="text-end">
          <v-chip :text="item.shiftId" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeId="{ item }">
        <div class="text-end">
          <v-chip :text="item.employeeId" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.shiftOwnerName="{ item }">
        <div class="text-end">
          <v-chip :text="item.shiftOwnerName" label size="large"></v-chip>
        </div>
      </template>


      <template v-slot:item.shiftVolunteerName="{ item }">
        <div class="text-end">
          <v-chip :text="item.shiftVolunteerName" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.startTime="{ item }">
        <div class="text-end">
          <v-chip :text="item.startTime" label size="large"></v-chip>
        </div>
      </template>
      <template v-slot:item.endTime="{ item }">
        <div class="text-end">
          <v-chip :text="item.endTime" label size="large"></v-chip>
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
      headers: [
        { text: 'Shift ID', value: 'shiftId', align: 'start' },
        { text: 'Shift Owner ID', value: 'shiftOwnerId', align: 'start' },
        { text: 'Shift Owner Name', value: 'shiftOwnerName', align: 'start' },
        { text: 'Shift Volunteer ID', value: 'shiftVolunteerId', align: 'start' },
        { text: 'Shift Volunteer', value: 'shiftVolunteerName', align: 'start' },
        { text: 'Start Time', value: 'startTime', align: 'center' },
        { text: 'End Time', value: 'endTime', align: 'center' },
        { text: 'Covered', value: 'covered', align: 'center' }
      ],
      search: "",

      shifts: [
        {
          shiftId: 1,
          shiftOwnerId: 1,
          shiftOwnerName: "",
          shiftVolunteerId: null,
          shiftVolunteerName: "",
          startTime: "",
          endTime: "",
          covered: false
        },
      ],
    }
  },
  computed: {
    reversedRequests() {
      console.log(this.shifts);
      return [this.shifts].reverse();
    }
  }
}


</script>
<style></style>
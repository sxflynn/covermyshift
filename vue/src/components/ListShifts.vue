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
    <v-data-table v-model:search="search" :headers="headers" :items="processedRequests" :items-per-page="1000" class="elevation-1">

      <template v-slot:item.shiftId="{ item }">
        <div class="text-end">
          <v-chip :text="item.shiftId" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.shiftOwnerId="{ item }">
        <div class="text-end">
          <v-chip :text="item.shiftOwnerId" class="text-lowercase" label size="large"></v-chip>
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

      <template v-slot:item.action="{ item }">
      <v-btn color="primary" :disabled="item.covered" @click="onButtonClick(item)">{{ item.covered ? 'Already Claimed' : 'Claim This Shift' }}</v-btn>
    </template>

    </v-data-table>
  </v-card>
</template>
  
<script>
export default {
  data() {
    return {
      headers: [
        // { title: 'Shift ID', key: 'shiftId', align: 'start' },
        // { title: 'Shift Owner ID', key: 'shiftOwnerId', align: 'start' },
        { title: 'Shift Owner', key: 'shiftOwnerName', align: 'start' },
        // { title: 'Shift Volunteer ID', key: 'shiftVolunteerId', align: 'start' },
        { title: 'Shift Volunteer', key: 'shiftVolunteerName', align: 'start' },
        { title: 'Start Time', key: 'startTime', align: 'center' },
        { title: 'End Time', key: 'endTime', align: 'center' },
        { title: 'Covered', key: 'covered', align: 'center' },
        {title: 'Claim', value: 'action', sortable: false}
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
  mounted() {
    this.$store.dispatch("fetchListShiftArr");
  },
  computed: {
    processedRequests() {
      // Create a shallow copy of the array and reverse it
      let reversedArray = [...this.$store.state.listShiftArr].reverse();
      console.log("Reversed array is ", reversedArray); // Debugging line
      return reversedArray;
    }
  }
}


</script>
<style></style>
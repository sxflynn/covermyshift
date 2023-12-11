<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;
      List of Shifts

      <v-spacer></v-spacer>

      <v-text-field v-model="search" prepend-inner-icon="mdi-magnify" density="compact" label="Search" single-line flat
        hide-details variant="solo-filled"></v-text-field>
    </v-card-title>

    <v-divider></v-divider>
    <!-- TODO: Add custom headers using the headers prop -->
    <!-- TODO: Customize the items-per-page -->
    <v-data-table v-model:search="search" :headers="headers" :items="processedRequests" :items-per-page="1000"
      class="elevation-1">

      <template v-slot:item.shiftOwnerName="{ item }">
        <div class="text-center">
          <v-chip variant="text" :text="item.shiftOwnerName" label size="large"></v-chip>
        </div>
      </template>


      <template v-slot:item.shiftVolunteerName="{ item }">
        <div class="text-center">
          <v-chip variant="text" :text="item.shiftVolunteerName" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.startTime="{ item }">
        <div class="text-center">
          <v-chip variant="text" :text="formatDateTime(item.startTime, item.endTime)" label size="large"></v-chip>
        </div>
      </template>


      <template v-slot:item.covered="{ item }">
        <div class="text-center">
          <v-chip :color="item.covered ? 'green' : 'red'" :text="item.covered ? 'Covered' : 'Uncovered'"
            class="text-uppercase" label :size="chipSize"></v-chip>
        </div>
      </template>

      <template v-slot:item.action="{ item }">
        <v-dialog width="500">
          <template v-slot:activator="{ props }">
            <v-btn color="primary" v-bind="props" :disabled="item.covered">{{ item.covered ? 'Already Claimed' :
          'Claim This Shift' }}</v-btn>
          </template>

          <template v-slot:default="{ isActive }">
            <v-card class="ma-2">
              <v-card-title class="headline">Claim this shift</v-card-title>
              <v-container fluid>

                <v-row>
                  <v-col cols="6" class="text-end"><strong>Shift Owner Name:</strong></v-col>
                  <v-col cols="6" class="text-start">{{ item.shiftOwnerName }}</v-col>
                </v-row>


                <v-row>
                  <v-col cols="6" class="text-end"><strong>Start time:</strong></v-col>
                  <v-col cols="6" class="text-start">{{ item.startTime }}</v-col>
                </v-row>
                <v-row>
                  <v-col cols="6" class="text-end"><strong>End Time:</strong></v-col>
                  <v-col cols="6" class="text-start">{{ item.endTime }}</v-col>
                </v-row>


                <v-row>
                  <v-col cols="6" class="text-end"><strong>Covered yet?:</strong></v-col>
                  <v-col cols="6" class="text-start">
                    <v-chip small :color="item.covered ? 'blue' : 'red'">
                      {{ item.covered ? "Covered" : "Uncovered" }}
                    </v-chip>
                  </v-col>
                </v-row>
  
              </v-container>
              <v-card-actions>
                <v-btn variant="tonal" color="green" rounded text="Claim Shift" @click="updateShift(item, isActive)"
                  class="ma-1">
                  Claim Shift
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn text="Cancel" @click="isActive.value = false" class="ma-1"></v-btn>
              </v-card-actions>
          


            </v-card>
            
          </template>
        </v-dialog>
       
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
        { title: 'Date/Time', key: 'startTime', align: 'center' },
        // { title: 'End Time', key: 'endTime', align: 'center' },
        { title: 'Covered', key: 'covered', align: 'center' },
        { title: 'Claim', value: 'action', sortable: false }
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
    },
},
  methods: {
        formatDateTime(startTime, endTime) {
          const start = new Date(startTime);
          const end = new Date(endTime);
          const dateOptions = { weekday: 'short', month: 'short', day: 'numeric', hour: 'numeric', minute: 'numeric', hour12: true };
          let formattedStart = new Intl.DateTimeFormat('en-US', dateOptions).format(start);
          const timeOptions = { hour: 'numeric', minute: 'numeric', hour12: true };
          let formattedEnd = new Intl.DateTimeFormat('en-US', timeOptions).format(end);
          console.log(`${formattedStart} - ${formattedEnd}`)
          return `${formattedStart} - ${formattedEnd}`;
        },
        updateShift(item, isActive){
          item.covered = true;
          item.shiftVolunteerId = 1; // TODO CHANGE TO FUTURE STATE WHICH HAS EMPLOYEE INFO
          this.$store
          .dispatch("updateShift", item)
          .then((response) =>{
            isActive.value = false;
            console.log("response is ", response);
          })
          .catch((error) => {
          console.error("Error updating shift: ", error);
        });
        }
      }
}


</script>
<style></style>
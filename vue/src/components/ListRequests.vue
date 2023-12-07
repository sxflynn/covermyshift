<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;

      <v-spacer></v-spacer>List of Requests (TODO: Headers issue, specific
      column display, font and style change of data)

      <v-text-field v-model="search" prepend-inner-icon="mdi-magnify" density="compact" label="Search" single-line flat
        hide-details variant="solo-filled"></v-text-field>
    </v-card-title>

    <v-divider></v-divider>
    <!-- TODO: Add custom headers using the headers prop -->
    <!-- TODO: Customize the items-per-page -->
    <v-data-table v-model:search="search" :items="processedRequests" :headers="headers" :items-per-page="1000">
      <template v-slot:item.requestId="{ item }">
        <div class="text-end">
          <v-chip variant="text" :text="item.requestId" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeId="{ item }">
        <div class="text-end">
          <v-chip variant="text" :text="item.employeeId" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeName="{ item }">
        <div class="text-end">
          <v-chip variant="text" :text="item.employeeName" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.date="{ item }">
        <div class="text-end">
          <v-chip variant="text" :text="item.date" class="text-lowercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeMessage="{ item }">
        <div class="text-end">
          <v-chip :text="item.employeeMessage" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.emergency="{ item }">
        <div class="text-end">
          <v-chip :color="item.emergency ? 'red' : 'blue'" :text="item.emergency ? 'Emergency' : 'Vacation'"
            class="text-uppercase" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.approved="{ item }">
        <v-dialog width="500">
          <template v-slot:activator="{ props }">
            <v-btn :color="item.approved ? 'green' : 'orange'" v-bind="props" :text="item.approved ? 'Approved' : 'Accept / Decline'">
            </v-btn>
          </template>

          <template v-slot:default="{ isActive }">
            <v-card class="ma-2">
              <v-card-title class="headline">
                Request Off
              </v-card-title>

              <v-card-subtitle>
                <v-chip small color="primary" class="ma-2">
                  {{ item.employeeName }} - {{ item.date }} ({{ item.emergency ? 'Emergency' : 'Vacation' }})
                </v-chip>
              </v-card-subtitle>

              <v-card-text>
                {{item.employeeMessage}}
              </v-card-text>

              <v-card-actions>
                <v-btn variant="tonal" color="green" rounded text="Accept" @click="acceptRequest(item, isActive)" class="ma-1">
                  Accept
                </v-btn>
                <v-dialog width="500">
                  <template v-slot:activator="{ props }">
                    <v-btn v-bind="props" variant="tonal" color="red" rounded text="Decline" class="ma-1">
                      Decline
                    </v-btn>
                  </template>

                  <template v-slot:default="{ isActive }">
                    <v-card>
                      <v-card-title class="headline">
                        Reasoning for Decline
                      </v-card-title>
                      <v-card-text>
                        <v-text-field label="Message (optional)" outlined dense></v-text-field>
                      </v-card-text>

                      <v-card-actions>
                        <v-btn variant="tonal" color="red" rounded text="Send & Decline" class="ma-1"></v-btn>
                        <v-spacer></v-spacer>
                        <v-btn text="Cancel" @click="isActive.value = false" class="ma-1"></v-btn>
                      </v-card-actions>
                    </v-card>
                  </template>
                </v-dialog>
                <v-spacer></v-spacer>
                <v-btn text="Cancel" class="ma-1" @click="isActive.value = false"></v-btn>
              </v-card-actions>
            </v-card>
          </template>

        </v-dialog>
      </template>

      <template v-slot:item.covered="{ item }">
        <div class="text-end">
          <v-chip :color="item.covered ? 'green' : 'red'" :text="item.covered ? 'Covered' : 'Uncovered'"
            class="text-uppercase" label size="large" variant="outlined"></v-chip>
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
      headers: [
        { title: "Request ID", key: "requestId", align: "start" },
        { title: "Employee ID", key: "employeeId", align: "start" },
        { title: "Employee Name", key: "employeeName", align: "start" },
        { title: "Date", key: "date", align: "start" },
        { title: "Message", key: "employeeMessage", align: "start" },
        { title: "Emergency/Vacation", key: "emergency", align: "center" },
        { title: "Covered", key: "covered", align: "center" },
        { title: "Approved", key: "approved", align: "center" },
        { title: "Pending", key: "pending", align: "center" },
      ],
      search: "",

      requests: [
        {
          requestId: 1,
          employeeId: 1,
          employeeName: "",
          date: "",
          employeeMessage: "",
          emergency: false,
          covered: false,
          approved: false,
          pending: true,
        },
      ],
    };
  },
  mounted() {
    this.$store.dispatch('fetchListReqArr');
  },
  computed: {
    processedRequests() {
      // Create a shallow copy of the array and reverse it
      let reversedArray = [...this.$store.state.listReqArr].reverse();
      console.log("Reversed array is ", reversedArray); // Debugging line
      return reversedArray;
    },
  },
  methods:{
    acceptRequest(item, isActive){
      item.approved = true;
      item.pending = false;
      console.log("RequestID is", item.requestId);
      this.$store.dispatch('updateRequest', item)
      .then(response =>{
        isActive.value = false; 
        console.log("response is ",response);
      })
      .catch(error =>{
        console.error("Error updating shift: ",error);
      })
    }
  }
};
</script>
<style></style>
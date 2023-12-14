<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;
      <div class="sameFont" id="headerText">
      My Requests
    </div>
      <v-spacer></v-spacer>

      <v-text-field v-model="search" prepend-inner-icon="mdi-magnify" density="compact" label="Search" single-line flat
        hide-details variant="solo-filled"></v-text-field>
    </v-card-title>

    <v-divider></v-divider>
    <v-data-table v-model:search="search" :items="this.$store.state.listReqArr" :headers="headers" :items-per-page="1000"
      :sort-by="sortBy" :sort-desc="sortDesc" class="sameFont">

      <template v-show="!isUser" v-slot:item.employeeName="{ item }">
        <v-chip variant="text" :text="item.employeeName" label size="large"></v-chip>
      </template>

      <template v-slot:item.date="{ item }">
        <v-chip variant="text" :text="item.date" class="text-lowercase" label size="large"></v-chip>
      </template>

      <template v-slot:item.employeeMessage="{ item }">
        <div class="text-start">
          <v-chip variant="text" :text="item.employeeMessage" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.emergency="{ item }">
        <v-chip :color="item.emergency ? 'red' : 'light-blue'" :text="item.emergency ? 'Emergency' : 'Vacation'"
          class="text-uppercase" label size="large"></v-chip>
      </template>

      <template v-slot:item.approved="{ item }">
        <v-chip :color="item.approved ? 'green' : (item.pending ? 'orange' : 'red')"
          :text="item.approved ? 'Approved' : (item.pending ? 'Not yet approved' : 'Unapproved')" class="text-uppercase"
          label size="large"></v-chip>
      </template>

      <template v-slot:item.pending="{ item }">
        <v-chip :color="item.pending ? 'red' : 'green'" :text="item.pending ? 'Pending' : 'Finalized'"
          class="sameFont" label size="large"></v-chip>
      </template>


      <template v-slot:item.action="{ item }">
        <v-dialog width="1000" height="1100">
          <template v-slot:activator="{ props }">
            <v-btn class="sameFont" color="light-blue-lighten-4" v-if="item.pending" v-bind="props" text="Edit">
            </v-btn>
          </template>

          <template v-slot:default="{ isActive }">
  <v-card class="sameFont">
    <v-card-title class="headline" id="editHeader">Edit Time Off Request</v-card-title>
   
    
    <v-row>
      <!-- Left side content -->
      <v-col>
    <v-container fluid class="sameFont">
      <v-row>
        <v-radio-group v-model="item.emergency">
          <v-col style="display: inline-block;">
            <v-radio
              label="Emergency"
              :value="true"
              color="red"
            ></v-radio>
          </v-col>
          <v-col style="display: inline-block;">
            <v-radio
              label="Vacation"
              :value="false"
              color="blue"
            ></v-radio>
          </v-col>
        </v-radio-group>
      </v-row>
      <v-row>
        <v-textarea label="Message" outlined dense v-model="item.employeeMessage"></v-textarea>
      </v-row>
    </v-container>
    <v-text-field v-if="!isUser" label="Message to employee" outlined dense v-model="item.managerMessage"></v-text-field>
  </v-col>
      <!-- Right side with date picker -->
      <v-col cols="auto">
<!-- {{ item.date }} (Type: {{ typeof item.date }}) -->
<v-date-picker
              elevation="5"
              class="sameFont"
              show-adjacent-months
              color="light-blue-lighten-4"
              :bind="item.date"
              v-model="requests.date"
            >
            </v-date-picker>
   
      </v-col>
    </v-row>


  
    <v-card-actions >
      <v-btn v-if="isUser" variant="tonal" color="green"
        @click="editRequest(item, isActive)" class="ma-1">
        Update & Save
      </v-btn> 
      <v-dialog width="500">
  <template v-slot:activator="{ props }">
    <v-btn variant="tonal" color="red" v-bind="props" text="Delete"> </v-btn>
  </template>

  <template v-slot:default="{ isActive }">
    <v-card title="Confirmation" class="sameFont">
      <v-card-text class="sameFont">
        ARE YOU SURE YOU WANT TO DELETE THIS REQUEST?
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn v-if="isUser" variant="tonal" color="red" rounded text="Delete Now"
        @click="deleteRequest(item.requestId, isActive)" class="ma-1">
        Yes, Delete
        </v-btn>

        <v-btn
          text="Cancel"
          @click="isActive.value = false"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </template>
</v-dialog>

 
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
        // { title: "Request ID", key: "requestId", align: "start" },
        // { title: "Employee ID", key: "employeeId", align: "start" },
        // { title: "Employee Name", key: "employeeName", align: "center" },
        { title: "Date", key: "date", align: "center" },
        { title: "Message", key: "employeeMessage", align: "start" },
        { title: "Emergency/Vacation", key: "emergency", align: "center" },
        { title: "Approved", key: "approved", align: "center" },
        { title: "Make a change", value: 'action', align: "center" },

      ],
      search: "",
      sortBy: ['pending', 'emergency', 'date'],
      sortDesc: [true, true, false],

      requests: [
        {
          requestId: null,
          employeeId: null,
          employeeName: "",
          date: new Date(Date.now()),
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
    this.$store.dispatch("fetchListReqArr");
  },
  computed: {
    displayDate(item){
      return item.date;
    },
    displayRequests() {
      let requestList = this.$store.state.listReqArr;
      return requestList;
    },
    isUser() {
      console.log("My authority is ", this.$store.state.user.authorities[0].name)
      return this.$store.state.user.authorities[0].name === 'ROLE_USER';
    },
  },
  methods: {
    editRequest(item, isActive) {
      item.date = new Date(this.requests.date).toISOString().slice(0, 10);
      this.$store
        .dispatch("updateRequest", item)
        .then((response) => {
          isActive.value = false;
        })
        .catch((error) => {
          console.error("Error updating shift: ", error);
        });
    },
    declineRequest(item, isActive) {
      item.approved = false;
      item.pending = false;
      this.$store.dispatch("updateRequest", item)
        .then((response) => {
          isActive.value = false;
          console.log("response is ", response);
        })
        .catch((error) => {
          console.error("Error updating shift: ", error);
        });
    },
    deleteRequest(requestId, isActive){
      console.log("deleteRequest() Request Id in delete method is ", requestId);
      this.$store.dispatch("deleteRequestById",requestId)
      .then((response)=>{
        isActive.value = false;
        console.log("Successfully deleted request ID ", requestId);
      })
      .catch((error)=>{
        console.error("Error deleting shift: ",error);
      })
    }

  },
};
</script>
<style>
.sameFont{
  font-family: "League Spartan";
}
#headerText{
  font-size: x-large;
}
#editHeader{
  font-size: xx-large;
}



</style>
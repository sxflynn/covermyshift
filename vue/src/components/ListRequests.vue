<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-video-input-component"></v-icon> &nbsp; List of Requests
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
    <!-- TODO: Add custom headers using the headers prop -->
    <!-- TODO: Customize the items-per-page -->
    <v-data-table
      v-model:search="search"
      :items="this.$store.state.listReqArr"
      :headers="headers"
      :items-per-page="1000"
      :sort-by="sortBy"
      :sort-desc="sortDesc"
      class="elevation-1"
    >
      <template v-show="!isUser" v-slot:item.employeeName="{ item }">
        <v-chip
          variant="text"
          :text="item.employeeName"
          label
          size="large"
        ></v-chip>
      </template>

      <template v-slot:item.date="{ item }">
        <v-chip
          variant="text"
          :text="item.date"
          class="text-lowercase"
          label
          size="large"
        ></v-chip>
      </template>

      <template v-slot:item.employeeMessage="{ item }">
        <div class="text-start">
          <v-chip
            variant="text"
            :text="item.employeeMessage"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.managerMessage="{ item }">
        <div class="text-start">
          <v-chip
            variant="text"
            :text="item.managerMessage"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.emergency="{ item }">
        <v-chip
          :color="item.emergency ? 'red' : 'blue'"
          :text="item.emergency ? 'Emergency' : 'Vacation'"
          class="text-uppercase"
          label
          size="large"
        ></v-chip>
      </template>

      <template v-slot:item.approved="{ item }">
        <v-chip
          :color="item.approved ? 'green' : item.pending ? 'orange' : 'red'"
          :text="
            item.approved
              ? 'Approved'
              : item.pending
              ? 'Not yet approved'
              : 'Unapproved'
          "
          class="text-uppercase"
          label
          size="large"
        ></v-chip>
      </template>

      <template v-slot:item.pending="{ item }">
        <v-chip
          :color="item.pending ? 'red' : 'green'"
          :text="item.pending ? 'Pending' : 'Finalized'"
          class="text-uppercase"
          label
          size="large"
        ></v-chip>
      </template>

      <template v-slot:item.action="{ item }">
        <v-dialog width="500">
          <template v-slot:activator="{ props }">
            <span v-if="isUser"></span>
            <span v-else></span>
            <v-btn v-bind="props" :text="isUser ? 'Edit' : 'Open'"> </v-btn>
          </template>

          <template v-slot:default="{ isActive }">
            <v-card class="ma-2">
              <v-card-title class="headline">Time Off Request</v-card-title>
              <v-container fluid>
                <v-row>
                  <v-col cols="6" class="text-end"
                    ><strong>Employee Name:</strong></v-col
                  >
                  <v-col cols="6" class="text-start">{{
                    item.employeeName
                  }}</v-col>
                </v-row>

                <v-row>
                  <v-col cols="6" class="text-end"
                    ><strong>Date:</strong></v-col
                  >
                  <v-col cols="6" class="text-start">{{ item.date }}</v-col>
                </v-row>

                <v-row>
                  <v-col cols="6" class="text-end"
                    ><strong>Type:</strong></v-col
                  >
                  <v-col cols="6" class="text-start">
                    <v-chip small color="primary">
                      {{ item.emergency ? "Emergency" : "Vacation" }}
                    </v-chip>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6" class="text-end"
                    ><strong>Employee Message:</strong></v-col
                  >
                  <v-col cols="6" class="text-start">{{
                    item.employeeMessage
                  }}</v-col>
                </v-row>
              </v-container>

              <v-text-field
                v-if="!isUser"
                label="Message to employee "
                outlined
                dense
                v-model="item.managerMessage"
              ></v-text-field>

              <v-card-actions>
                <v-btn
                  v-if="isUser"
                  variant="tonal"
                  color="light-blue"
                  rounded
                  text="Edit"
                  @click="editRequest(item, isActive)"
                  class="ma-1"
                >
                  Edit</v-btn
                >
                <div v-else>
                  <v-btn
                    variant="tonal"
                    color="green"
                    rounded
                    text="Accept"
                    @click="acceptRequest(item, isActive)"
                    class="ma-1"
                  >
                    Accept
                  </v-btn>
                  <v-btn
                    variant="tonal"
                    color="red"
                    rounded
                    text="Decline"
                    @click="declineRequest(item, isActive)"
                    class="ma-1"
                  >
                    {{
                      item.managerMessage ? "Decline with Message" : "Decline"
                    }}
                  </v-btn>
                </div>
                <v-spacer></v-spacer>
                <v-btn
                  text="Cancel"
                  @click="isActive.value = false"
                  class="ma-1"
                ></v-btn>
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
        {
          title: "Employee Name",
          key: "employeeName",
          align: "center",
        },
        { title: "Date", key: "date", align: "center" },
        { title: "Message", key: "employeeMessage", align: "start" },
        { title: "Manager's Message", key: "managerMessage", align: "start" },
        { title: "Emergency/Vacation", key: "emergency", align: "center" },
        { title: "Approved", key: "approved", align: "center" },
        { title: "Approve/Deny", value: "action", align: "center" },
      ],
      search: "",
      sortBy: ["pending", "emergency", "date"],
      sortDesc: [true, true, false],

      requests: [
        {
          requestId: null,
          employeeId: null,
          employeeName: "",
          date: "",
          employeeMessage: "",
          managerMessage: "",
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
    displayRequests() {
      let requestList = this.$store.state.listReqArr;
      return requestList;
    },
    isUser() {
      console.log(
        "My authority is ",
        this.$store.state.user.authorities[0].name
      );
      return this.$store.state.user.authorities[0].name === "ROLE_USER";
    },
  },
  methods: {
    //TODO CREATE EDIT REQUEST FUNCTION
    acceptRequest(item, isActive) {
      item.approved = true;
      item.pending = false;
      console.log("RequestID is", item.requestId);
      this.$store
        .dispatch("updateRequest", item)
        .then((response) => {
          isActive.value = false;
          this.$store.dispatch("fetchAllUncoveredShifts");
        })
        .catch((error) => {
          console.error("Error updating shift: ", error);
        });
    },
    declineRequest(item, isActive) {
      item.approved = false;
      item.pending = false;
      console.log("RequestID is", item.requestId);
      console.log("Manager message is", item.managerMessage);
      this.$store
        .dispatch("updateRequest", item)
        .then((response) => {
          isActive.value = false;
          console.log("response is ", response);
        })
        .catch((error) => {
          console.error("Error updating shift: ", error);
        });
    },
  },
};
</script>
<style scoped>
.custom-header {
  font-family: "Your-Desired-Font", sans-serif; /* Replace 'Your-Desired-Font' with the actual font name */
  cursor: pointer;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #f5f5f5;
}
</style>
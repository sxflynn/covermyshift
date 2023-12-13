<template>
<<<<<<< HEAD
  <v-card>
    <v-tabs v-model="tab" bg-color="cyan" class="v-slide-group-item--active">
=======
  <v-container v-if="!isUser" id="data">
    <v-row justify="center">
      <v-col cols="12" sm="3">
        <v-card height="8rem"  elevation="3" color="orange" class="text-h3" title="Emergency Requests">
          <v-card-text class="text-h3 text-center">{{ this.$store.state.listReqArr.length }}</v-card-text>
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card height="8rem"  color="yellow" title="Unclaimed Shifts">
          <v-card-text class="text-h3 text-center">0</v-card-text>
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card height="8rem" title="Percent called off">
          <v-card-text class="text-h3 text-center">12%</v-card-text>
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card height="8rem"  color = "red" title="Rejected Requests">
          <v-card-text class="text-h3 text-center">2</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>

  <v-card color="light-blue-lighten-5">
    <v-tabs v-model="tab" bg-color="light-blue-lighten-4">
      <v-tab v-if="!isUser" value="allrequests">All Requests</v-tab>
>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
      <v-tab value="shifts">Shifts to Cover</v-tab>
      <v-tab v-if="isUser" value="myshifts">My Shifts</v-tab>
      <v-tab v-if="isUser" value="myrequests">My Requests</v-tab>
    </v-tabs>
<<<<<<< HEAD

=======
>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
    <v-card-text>
      <v-window v-model="tab">
        <v-window-item v-if="!isUser" value="allrequests">
          <list-requests />
        </v-window-item>
        <v-window-item value="shifts">
<<<<<<< HEAD
=======

>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
          <list-shifts-to-cover />
        </v-window-item>

        <v-window-item value="myshifts">
          <list-shifts />
<<<<<<< HEAD
=======

>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
        </v-window-item>



        <v-window-item value="myrequests">
          <my-requests />
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>
<script>
import ListShifts from "../components/ListShifts.vue";
import ListRequests from "../components/ListRequests.vue";
import ListShiftsToCover from "../components/ListShiftsToCover.vue";
import MyRequests from "../components/MyRequests.vue";

export default {
  components: {
    ListShifts,
    ListRequests,
    ListShiftsToCover,
<<<<<<< HEAD
  },

  data() {
=======
    MyRequests
  },

  data() {

>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
    return {
      tab: null,
      showShifts: true,
      showForm: false,
      buttonText: "Show Form",
      coverReq: {
        requestID: 0,
        employeeID: 1,
        employeeName: "",
        date: "",
        workplaceID: null,
        isEmergency: null,
        isPending: null,
        isCovered: null,
        isApproved: null,
      },
    };
  },
  computed: {
    isUser() {
<<<<<<< HEAD
      console.log(
        "My authority is ",
        this.$store.state.user.authorities[0].name
      );
      return this.$store.state.user.authorities[0].name === "ROLE_USER";
=======
      return this.$store.state.user.authorities[0].name === 'ROLE_USER';
>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
    },
  },
};
</script>
<<<<<<< HEAD
<style scoped>
=======
<style>
>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
.v-sheet--offset {
  top: -24px;
  position: relative;
}
<<<<<<< HEAD
.v-slide-group-item--active {
  background-color: orangered;
}
=======
>>>>>>> 210dbab9f59fa15fe8843be8738ef6874d9d31c5
</style>
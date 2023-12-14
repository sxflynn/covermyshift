<template>
   <div class="text-h4">Data Dashboard</div>
  <v-container v-if="!isUser" id="data">
    <v-row justify="center">
      <v-col cols="12" sm="3">
        <v-card
          height="8rem"
          elevation="3"
          color="red-lighten-1"
          class="text-h3 text-center"
          title="Emergency Requests"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
          >
            {{ calcEmergencies }}</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          color="blue-lighten-4"
          title="Unclaimed Shifts"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
          >
            {{ calcUnclaimedShifts }}</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          title="Shifts Covered"
          color="grey-lighten-4"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >{{ calcPercentOfTotalShiftsHaveVolunteer }}%</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          color="blue-grey-lighten-3"
          title="Rejected Requests"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >{{ calcRejected }}</v-card-text
          >
        </v-card>
      </v-col>
    </v-row>
  </v-container>
  <v-container v-else id="data">
    <v-row justify="center">
      <v-col cols="12" sm="3">
        <v-card
          height="8rem"
          elevation="3"
          color="red-lighten-1"
          class="text-h3 text-center"
          title="Emergency Requests"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
          >
            {{ calcEmergencies }}</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          color="blue-lighten-4"
          title="Unclaimed Shifts"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
          >
            {{ calcUnclaimedShifts }}</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          title="Vacation Days"
          color="grey-lighten-4"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >12</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          color="blue-grey-lighten-3"
          title="Shifts claimed"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >2</v-card-text
          >
        </v-card>
      </v-col>
    </v-row>
  </v-container>

  <v-card color="light-blue-lighten-5">
    <v-tabs v-model="tab" bg-color="light-blue-lighten-4">
      <v-tab v-if="!isUser" value="allrequests">All Requests</v-tab>
      <v-tab value="shifts">Shifts to Cover</v-tab>
      <v-tab value="myshifts">{{ isUser ? "My Shifts" : "All Shifts" }}</v-tab>
      <v-tab v-if="isUser" value="myrequests">My Requests</v-tab>
      <v-tab v-if="!isUser" value="datapoints">Data</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="tab">
        <v-window-item v-if="!isUser" value="allrequests">
          <list-requests />
        </v-window-item>
        <v-window-item value="shifts">
          <list-shifts-to-cover />
        </v-window-item>

        <v-window-item value="myshifts">
          <list-shifts />
        </v-window-item>

        <v-window-item value="myrequests">
          <my-requests />
        </v-window-item>
        <v-window-item value="datapoints">
          <data-points />
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
import DataPoints from "../components/Datapoints.vue";


export default {
  components: {
    ListShifts,
    ListRequests,
    ListShiftsToCover,
    MyRequests,
    DataPoints
  },

  data() {
    return {
      unclaimedShifts: null,
      emergencyReqs: null,
      rejectedReqs: null,
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
    isLoggedIn() {
      return this.$store.state.user.username !== undefined;
    },
    isUser() {
      return this.$store.state.user.authorities[0].name === "ROLE_USER";
    },
    calcEmergencies() {
      let reqArr = this.$store.state.listReqArr;
      let emergencyCounter = 0;
      reqArr.forEach((req) => {
        if (req.emergency === true) {
          emergencyCounter++;
        }
      });
      return emergencyCounter;
    },
    calcUnclaimedShifts() {
      return this.$store.state.listUncoveredShiftsArr.length;
    },
    calcPercentOfTotalShiftsHaveVolunteer() {
      if (this.$store.state.listUncoveredShiftsArr.length === 0) {
          return 0;
      }
      return (
        100 -
        (
          this.$store.state.listUncoveredShiftsArr.length /
          this.$store.state.listAllShiftArr.length
        ).toPrecision(1) *
          100
      );
    },
    calcRejected() {
      let reqArr = this.$store.state.listReqArr;
      let emergencyCounter = 0;
      reqArr.forEach((req) => {
        if (req.approved === false && req.pending === false) {
          emergencyCounter++;
        }
      });
      return emergencyCounter;
    },
  },
  mounted() {
    this.$store.dispatch("fetchAllUncoveredShifts");
    this.$store.dispatch("fetchCurrentListShiftArr"); //ok
    this.$store.dispatch("fetchCurrentAndFutureRequests"); //ok
  },
  watch: {},
};
</script>
<style>
.v-sheet--offset {
  top: -24px;
  position: relative;
}
</style>
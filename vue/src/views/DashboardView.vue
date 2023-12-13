<template>
  <v-container v-if="!isUser" id="data">
    <v-row justify="center">
      <v-col cols="12" sm="3">
        <v-card
          height="8rem"
          elevation="3"
          color="orange"
          class="text-h3 text-center"
          title="Emergency Requests"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >{{ calcEmergencies() }}</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          color="red"
          title="Unclaimed Shifts"
        >
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >{{ calcUnclaimedShifts() }}</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card class="text-center" height="8rem" title="Percent called off">
          <v-card-text
            class="text-h3 mt-4"
            style="display: flex; align-items: center; justify-content: center"
            >12%</v-card-text
          >
        </v-card>
      </v-col>
      <v-col elevation="3" cols="12" sm="3">
        <v-card
          class="text-center"
          height="8rem"
          color="yellow"
          title="Rejected Requests"
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
    MyRequests,
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
    isUser() {
      return this.$store.state.user.authorities[0].name === "ROLE_USER";
    },
    calcAdminData() {
      console.log("calcAdminData is working");
      return this.calcEmergencies();
    },
  },
  methods: {
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
      let shiftArr = this.$store.state.listShiftArr;
      console.log("Shifts Arr " + shiftArr);
    },
  },
  mounted() {
    this.calcUnclaimedShifts();
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
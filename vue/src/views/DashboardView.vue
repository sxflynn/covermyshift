<template>
  <v-card>
    <v-tabs v-model="tab" bg-color="cyan" class="v-slide-group-item--active">
      <v-tab value="shifts">Shifts to Cover</v-tab>
      <v-tab v-if="isUser" value="myshifts">My Shifts</v-tab>
      <v-tab v-if="isUser" value="myrequests">My Requests</v-tab>
      <v-tab v-else value="myrequests">All Requests</v-tab>
    </v-tabs>

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="shifts">
          <list-shifts-to-cover />
        </v-window-item>

        <v-window-item value="myshifts">
          <list-shifts />
        </v-window-item>

        <v-window-item value="myrequests">
          <list-requests />
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>
<script>
import ListShifts from "../components/ListShifts.vue";
import ListRequests from "../components/ListRequests.vue";
import ListShiftsToCover from "../components/ListShiftsToCover.vue";

const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"],
];

export default {
  components: {
    ListShifts,
    ListRequests,
    ListShiftsToCover,
  },

  data() {
    return {
      width: 2,
      radius: 10,
      padding: 8,
      lineCap: "round",
      gradient: gradients[5],
      value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
      gradientDirection: "top",
      gradients,
      labels: ["12am", "3am", "6am", "9am", "12pm", "3pm", "6pm", "9pm"],
      value: [200, 675, 410, 390, 310, 460, 250, 240],
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
      console.log(
        "My authority is ",
        this.$store.state.user.authorities[0].name
      );
      return this.$store.state.user.authorities[0].name === "ROLE_USER";
    },
  },
};
</script>
<style scoped>
.v-sheet--offset {
  top: -24px;
  position: relative;
}
.v-slide-group-item--active {
  background-color: orangered;
}
</style>
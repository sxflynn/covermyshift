<template>

  <v-card>
    <v-tabs v-model="tab" bg-color="primary">
      <v-tab value="shifts">Shifts to Cover</v-tab>
      <v-tab v-if="isUser" value="myshifts">My Shifts</v-tab>
      <v-tab v-if="isUser" value="myrequests">My Requests</v-tab>
      <v-tab v-else value="myrequests">All Requests</v-tab>
    </v-tabs>

   
  

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="shifts">
          <list-shifts-to-cover/>
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

export default {
  components: {
    ListShifts,
    ListRequests,
    ListShiftsToCover
  },

  data() {
    
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
      console.log("My authority is ", this.$store.state.user.authorities[0].name)
      return this.$store.state.user.authorities[0].name === 'ROLE_USER';
    },
  }
}
</script>
<style>
 .v-sheet--offset {
    top: -24px;
    position: relative;
  }</style>
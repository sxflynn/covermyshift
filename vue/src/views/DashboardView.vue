<template>

<v-card>
    <v-tabs
      v-model="tab"
      bg-color="primary"
    >
      <v-tab value="shifts">Shifts to Cover</v-tab>
      <v-tab v-if="displayAuthority === 'User'" value="myshifts">My Shifts</v-tab>
      <v-tab v-if="displayAuthority === 'User'" value="myrequests">My Requests</v-tab>
      <v-tab v-else value="myrequests">All Requests</v-tab>
    </v-tabs>

    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="shifts">
          <list-shifts/>
        </v-window-item>

        <v-window-item value="myshifts">
          <list-shifts/>
        </v-window-item>

        <v-window-item value="myrequests">
          <list-requests/>
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>

</template>
<script>
import ListShifts from "../components/ListShifts.vue";
import ListRequests from "../components/ListRequests.vue";

export default {
  components: {
    ListShifts,
    ListRequests
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
      computed:{
        tabText() {
    return this.displayAuthority === 'Admin' ? 'All Requests' : 'My Requests';
        },
        displayAuthority() {
      if (this.$store.state.user && this.$store.state.user.authorities[0].name) {
        const authority = this.$store.state.user.authorities[0].name;
        if (authority === 'ROLE_USER') {
          return 'User';
        }
        if (authority === 'ROLE_ADMIN') {
          return 'Admin';
        }
      }
      return '';
    }
      }
    };
}
}
</script>
<style></style>
<template>
  <div>
    <h1>I am the dashboard</h1>
    <list-shifts v-bind:shift="shift" v-show="showShifts" />
    <h1>I exist</h1>
    <button @click="addRequest" v-show="showShifts">Make Request</button>
    <button @click="addRequest" v-show="showForm">Show Shifts</button>
    <cover-form v-show="showForm" />
  </div>
</template>
<script>
import ListShifts from "../components/ListShifts.vue";
import CoverForm from "../components/CoverForm.vue";

export default {
  components: {
    ListShifts,
    CoverForm,
  },
  methods: {
    addRequest() {
      this.showForm = !this.showForm;
      this.showShifts = !this.showShifts;
    },
  },
  data() {
    return {
      showShifts: true,
      showForm: false,
      shift: {
        isCovered: true,
        shiftOwnerId: 1,
        shiftOwnerName: "Steve",
        shiftDate: "2023-12-01",
        companyId: 1,
      },

      employee: {
        employeeId: 1,
        employeeName: "Steve",
        username: "user",
        email: "user@user.com",
        companyId: 1,
      },
    };
  },
};
</script>
<style scoped>
</style>
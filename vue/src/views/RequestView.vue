<template>

    <list-requests v-show="!showForm" class="listShiftsClass" />

</template>
<script>
import ListRequests from "../components/ListRequests.vue";

export default {
  components: {
    ListRequests
  },

  data() {
    return {
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
  methods: {
    addRequest() {
      if (this.showForm) {
        this.buttonText = "Show Form";
        this.showForm = !this.showForm;
      } else if (!this.showForm) {
        this.buttonText = "List Shifts";
        this.showForm = !this.showForm;
      }
    },
  },
};
</script>
<style scoped>
#dashboardContent {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(3, 1fr);
  row-gap: 10px;
  column-gap: 10px;
  align-items: center;
  justify-content: center;
  grid-template-areas:
    ". . requestShiftBtn"
    "listShiftsClass listShiftsClass listShiftsClass"
    "coverFormClass coverFormClass coverFormClass";
}
.requestShiftBtn {
  float: left;
}
.listShiftsClass {
  grid-area: listShiftsClass;
}
.coverFormClass {
  grid-area: coverFormClass;
}
</style>
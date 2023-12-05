<template>
  <button @click="addRequest" class="requestShiftBtn">{{ buttonText }}</button>
  <div id="dashboardContent">
    <list-shifts
      v-bind:coverReq="coverReq"
      v-show="!showForm"
      class="listShiftsClass"
    />
  </div>
  <cover-form v-show="showForm" class="coverFormClass" />
</template>
<script>
import ListShifts from "../components/ListShifts.vue";
import CoverForm from "../components/CoverForm.vue";
import RequestService from "../services/RequestService";

export default {
  components: {
    ListShifts,
    CoverForm,
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
    handleErrorResponse(error, verb) {
      if (error.response) {
        if (error.response.status == 404) {
          this.$router.push({ name: "NotFoundView" });
        } else {
          this.$store.commit(
            "SET_NOTIFICATION",
            `Error ${verb} request. Response received was "${error.response.statusText}".`
          );
        }
      } else if (error.request) {
        this.$store.commit(
          "SET_NOTIFICATION",
          `Error ${verb} request. Server could not be reached.`
        );
      } else {
        this.$store.commit(
          "SET_NOTIFICATION",
          `Error ${verb} request. Request could not be created.`
        );
      }
    },
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
  created() {
    console.log("I am alive");
    // TODO - Do an add, then navigate Home on success.
    // For errors, call handleErrorResponse
    RequestService.list()
      .then((response) => {
        console.log(response.data);
        this.coverReq = response.data;
        if (response.status === 201 || response.status === 200) {
          this.$router.push({ name: "DashboardView" });
        }
      })
      .catch((error) => {
        this.handleErrorResponse(error, "adding");
      });
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
  float: right;
}
.listShiftsClass {
  grid-area: listShiftsClass;
}
.coverFormClass {
  grid-area: coverFormClass;
}
</style>
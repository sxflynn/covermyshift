<template>
  <button @click="addRequest" class="requestShiftBtn">{{ buttonText }}</button>
  <div id="dashboardContent">
    <list-shifts v-bind:shift="shift" v-show="!showForm" class="listShiftsClass"/>
    <cover-form v-show="showForm" class="coverFormClass" />
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
  
  data() {
    return {
      showShifts: true,
      showForm: false,
      buttonText: "Show Form",
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
  methods: {
    addRequest() {
      if (this.showForm) {
        this.buttonText = "Show Form"
        this.showForm = !this.showForm

      } else if(!this.showForm) {
        this.buttonText = "List Shifts"
        this.showForm = !this.showForm
      }
    },
  },
};
</script>
<style scoped>
#dashboardContent{
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
          "coverFormClass coverFormClass coverFormClass"


}
.requestShiftBtn{
  float: right ;

}
.listShiftsClass{
  grid-area: listShiftsClass;

}
.coverFormClass{
  grid-area: coverFormClass;
}
</style>
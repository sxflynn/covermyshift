<template>

    <list-shifts/>

</template>
<script>
import ListShifts from "../components/ListShifts.vue";

export default {
  components: {
    ListShifts
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
}
}
</script>
<style></style>
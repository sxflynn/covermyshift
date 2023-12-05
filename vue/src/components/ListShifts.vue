<template>
  <div>
    <ul class="shiftList">
      <span id="nameColumn">Name</span>
      <span id="dateColumn"> Date</span>
      <li
        v-for="item in this.$store.state.listReqArr"
        v-bind:key="item"
        class="listRow"
      >
        <span id="shiftName">{{ item.employeeName }}</span> :
        <span id="shiftDate">{{ item.date }}</span>
        <span id="shiftEmergency">{{ item.emergency }}</span>
        <span id="shiftMessage">{{ item.message }}</span>
        <v-btn id="accept">Accept</v-btn>
        <v-btn id="reject">Reject</v-btn>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showShifts: true,
      showForm: false,
      buttonText: "Show Form",
    };
  },
  methods: {
    // handleErrorResponse(error, verb) {
    //   if (error.response) {
    //     if (error.response.status == 404) {
    //       this.$router.push({ name: "NotFoundView" });
    //     } else {
    //       this.$store.commit(
    //         "SET_NOTIFICATION",
    //         `Error ${verb} request. Response received was "${error.response.statusText}".`
    //       );
    //     }
    //   } else if (error.request) {
    //     this.$store.commit(
    //       "SET_NOTIFICATION",
    //       `Error ${verb} request. Server could not be reached.`
    //     );
    //   } else {
    //     this.$store.commit(
    //       "SET_NOTIFICATION",
    //       `Error ${verb} request. Request could not be created.`
    //     );
    //   }
    // },
  },
  created() {
    this.$store.commit("LISTSHIFTS");
  },
};
</script>

<style>
#accept {
  background-color: rgb(56, 249, 66);

  border-radius: 5px;
  border: 1px solid transparent;
  padding: 0em 0.2em;
  font-size: 1em;
  font-weight: 1000;
  font-family: inherit;
  cursor: pointer;
  transition: border-color 0.25s;
  margin-left: 2.2%;
}
#reject {
  background-color: rgb(255, 56, 25);
  border-radius: 5px;
  border: 1px solid transparent;
  padding: 0em 0.2em;
  font-size: 1em;
  font-weight: 1000;
  font-family: inherit;
  cursor: pointer;
  transition: border-color 0.25s;
}
#dateColumn {
  text-underline-position: below;
  margin-left: 1%;
}
</style>
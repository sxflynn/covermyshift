<template>
  <h2>Submit a time off request</h2>

  <v-form v-on:submit.prevent="submitForm">
    <v-text-field label="Employee ID" v-model="coverReq.employeeId" outlined dense></v-text-field>
    <v-text-field label="Name" v-model="coverReq.employeeName" outlined dense></v-text-field>
    <v-date-picker show-adjacent-months v-model="coverReq.date"></v-date-picker>
    <v-checkbox label="Is this an Emergency?" v-model="coverReq.emergency"></v-checkbox>
    <v-text-field label="Reason for request (optional)" v-model="coverReq.message" outlined dense></v-text-field>
    <v-btn class="mr-4" color="primary" type="submit">Submit</v-btn>
    <v-btn color="error" type="button" v-on:click="cancelForm">Cancel</v-btn>
  </v-form>
</template>

<script>
import RequestService from "../services/RequestService";
export default {
  data() {
    return {
      coverReq: {
        requestId: null,
        // employeeId will come from principal *later*
        employeeId: 1,
        employeeName: "",
        date: null,
        message: "",
        covered: null,
        approved: null,
        emergency: null,
        pending: true,
      }
    };
  },
  methods: {
    submitForm() {
      console.log(this.coverReq.employeeName);
      // TODO: Do client-side form validation
      //Form isn't valid, user must update and submit again.

      // Check for add or edit

      // TODO - Do an add, then navigate Home on success.
      // For errors, call handleErrorResponse
      RequestService.create(this.coverReq)
        .then((response) => {
          console.log(response.data);
          if (response.status === 201 || response.status === 200) {
            this.$router.push({ name: "DashboardView" });
          }
        })
        .catch((error) => {
          // this.handleErrorResponse(error, "adding");
        });

      // FOR LATER TO ADD UPDATES?????
      // else {

      //   // TODO - Do an edit, then navigate back to Topic Details on success
      //   // For errors, call handleErrorResponse
      //     RequestService.update(this.coverReq.id, this.edit).then(response => {
      //       console.log(response.data)
      //     if (response.status === 201 || response.status ===200 || response.status===204){
      //       this.$router.push({name: 'TopicDetailsView'})
      //     }
      //   }).catch(error => {
      //     console.log(error )
      //     this.handleErrorResponse(error, "updating")
      //   })

      // }
    },
    cancelForm() {
      // Go back to previous page
      // this.$router.back();
      this.coverReq = {};
    },
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
};
</script>

<style scoped>
button,
input,
select,
textarea {
  background-color: transparent;
  border-style: dashed;
}
</style>
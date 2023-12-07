<template>
   <h2>Submit a time off request</h2>


<div class="d-flex justify-center align-center">
<v-form v-on:submit.prevent="submitForm">
  <v-text-field label="Employee ID" v-model="coverReq.employeeId" outlined dense></v-text-field>
  <v-text-field label="Name" v-model="coverReq.employeeName" outlined dense></v-text-field>
  <v-date-picker elevation="12" show-adjacent-months v-model="coverReq.date"></v-date-picker>
  <v-checkbox
  label= "Is this an emergency?" 
  v-model="coverReq.emergency"
  />
  <v-text-field label="Reason for request (optional)" v-model="coverReq.message" outlined dense></v-text-field>
  <v-btn class="mr-4" color="primary" type="submit">Submit</v-btn>
  <v-btn color="error" type="button" v-on:click="cancelForm">Cancel</v-btn>
</v-form>
</div>

</template>

<script>
export default {
  data() {
    return {
      coverReq: {
        requestId: null,
        // employeeId will come from principal *later*
        employeeId: 1,
        employeeName: "Steve C.",
        date: null,
        message: "",
        covered: false,
        approved: false,
        emergency: false,
        pending: true,
      }
    };
  },
  watch:{
    'coverReq.emergency'(newVal){
      // console.log('Emergency: ', newVal)
    }
  },
  methods: {
    submitForm() {
      this.$store.dispatch('createNewRequest',this.coverReq)
      .then(response => {
        this.$router.push({name:'requestview'});
      })
      .catch(error=>{
        console.error('Failed to submit', error)
      }),
      this.$store.dispatch('fetchListReqArr');
      // TODO: Do client-side form validation
      //Form isn't valid, user must update and submit again.

      // Check for add or edit

      // TODO - Do an add, then navigate Home on success.
      // For errors, call handleErrorResponse
      // RequestService.create(this.coverReq)
      //   .then((response) => {
      //     console.log(response.data);
      //     if (response.status === 200) {
      //       this.$store.commit('UPDATE_REQUEST_SUCCESS', response.data);
      //       this.$router.push({ name: 'home' });
      //     }
      //   })
      //   .catch((error) => {
      //     console.error(error);
      //     this.$store.commit('UPDATE_REQUEST_FAILURE', error);
      //   });

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
      this.coverReq = {
        requestId: null,
        // employeeId will come from principal *later*
        employeeId: 1,
        // employeeName will come from principal *later*
        employeeName: "Steve C.",
        date: null,
        message: "",
        covered: false,
        approved: false,
        emergency: false,
        pending: true,
      };
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

<style>
.v-selection-control__input input{
  opacity: 0.5 !important;
}
</style>
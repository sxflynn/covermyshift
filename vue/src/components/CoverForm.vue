<template>
  <form v-on:submit.prevent="submitForm">
    <div class="field">
      <label for="name">Name</label>
      <input
        type="text" id="name" name="name" v-model="coverReq.employeeName"/>
      &nbsp;
      <label for="date">Date</label>
      <input type="text" id="date" name="date" v-model="coverReq.date" />

      &nbsp;
      <label for="Emergency">Emergency</label>
      <input type="checkbox" id="emergency" name="emergency" v-model="coverReq.isEmergency" />
     
      &nbsp;
      <label for="Message">Reason for request (optional)</label>
      <input type="text" id="message" name="message" v-model="coverReq.isEmergency" />

      
    </div>
    <div class="actions">
      <button class="btn-submit" type="submit">Submit</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">
        Cancel
      </button>
    </div>
  </form>
</template>

<script>
import RequestService from "../services/RequestService";
export default {
  data() {
    return {
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
    submitForm() {
      console.log(this.coverReq.employeeName);
      // TODO: Do client-side form validation
      //Form isn't valid, user must update and submit again.

      // Check for add or edit

      if (this.coverReq.id === 0) {
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
            this.handleErrorResponse(error, "adding");
          });
      }
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
  },
};
</script>

<style scoped>
</style>
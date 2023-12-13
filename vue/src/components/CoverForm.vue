<template>
  <div class="d-flex justify-center align-center">
   

    <v-form v-on:submit.prevent="submitForm" :rules="dateRules">
      <v-container>
        <v-row>
          <v-col cols="12" md="8">
            <v-row>
              <v-col cols="12">
                <v-textarea label="Reason for request (optional)" v-model="coverReq.employeeMessage" outlined
                  dense></v-textarea>
                <v-checkbox label="Is this an emergency?" v-model="coverReq.emergency"></v-checkbox>
              </v-col>
              <v-col cols="12">
                <v-btn variant="tonal" class="mr-4" color="green" type="submit">Submit</v-btn>
                <v-btn color="error" type="button" v-on:click="cancelForm"
                  >Cancel</v-btn
                >
              </v-col>
            </v-row>
          </v-col>

          <v-col cols="12" md="4">
            <v-date-picker
              elevation="5"
              show-adjacent-months
              color="light-blue-lighten-4"
              :rules="dateRules"
              v-model="coverReq.date"
            >
            </v-date-picker>
          </v-col>
        </v-row>
      </v-container>
    </v-form>



    <!-- <v-snackbar
      v-model="snackbar.display"
      :timeout="snackbar.timeout"
    >
      {{ snackbar.text }}

      <template v-slot:actions>
        <v-btn
          color="blue"
          variant="text"
          @click="snackbar.display = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar> -->
    
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
  data() {
    return {
      snackbar: {
        display:false,
        text: "Snackbar message",
        timeout:2000
      },
      coverReq: {
        requestId: null,
        employeeId: this.$store.state.loggedInEmployee.employeeId,
        employeeName: this.$store.state.loggedInEmployee.employeeName,
        date: null,
        employeeMessage: "",
        covered: false,
        approved: false,
        emergency: false,
        pending: true,
      },
      dateRules: [
        (value) => {
          if (value) return true;
          return "A date is required";
        },
      ]
    };
  },
  methods: {
    submitForm() {
      console.log("Setting snackbar to true");
      this.snackbar = true;
      console.log("this.coverReq prior to dispatch is ", this.coverReq);
      this.$store
        .dispatch("createNewRequest", this.coverReq)
        .then((response) => {
          console.log("this is the .then after the dispatch")
          this.$router.push({ name: "dashboardMyrequests" });
        })
        .catch((error) => {
          console.error("Failed to submit", error);
        });
        this.$store.dispatch("fetchListReqArr");
    },
    cancelForm() {
      // Go back to previous page
      // this.$router.back();
      this.coverReq = {
        requestId: null,
        employeeId: this.$store.state.employeeId,
        employeeName: this.$store.state.employeeName,
        date: null,
        message: "",
        covered: false,
        approved: false,
        emergency: false,
        pending: true,
      };
    },
  },
  computed:{
    ...mapState(['snackbar'])
  }
};
</script>

<style>
.v-selection-control__input input {
  opacity: 1 !important;
}
</style>
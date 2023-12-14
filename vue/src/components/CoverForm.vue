<template>
  <div class="d-flex flex-column justify-center align-center">
   
    <div class="text-h4" id="title">Request Time Off</div>
    <v-container>
    <v-form v-on:submit.prevent="submitForm" :rules="dateRules">
      <v-container>
        <v-row>
          <v-col cols="12" md="8">
            <v-row>
              <v-col cols="12">
                <v-textarea label="Reason for request (optional)" v-model="coverReq.employeeMessage" outlined
                  dense></v-textarea>
                <v-checkbox label="Is this an emergency?" v-model="coverReq.emergency"></v-checkbox>
                <v-checkbox label="Send email notification?" v-model="sendEmail"></v-checkbox>
                

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
  </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sendEmail: false,
      coverReq: {
        requestId: null,
        employeeId: this.$store.state.loggedInEmployee.employeeId,
        employeeName: this.$store.state.loggedInEmployee.employeeName,
        date: new Date(Date.now()),
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

      const payload = {
      requestData: this.coverReq,
      sendEmail: this.sendEmail
    };

      this.$store.dispatch("createNewRequest", payload)
        .then((response) => {
          this.$router.push({ name: "dashboard" });
          return this.$store.dispatch("fetchListReqArr");
        })
        .catch((error) => {
          console.error("Failed to submit", error);
        });
        this.$store.dispatch("fetchListReqArr");
    },
    cancelForm() {
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
};
</script>

<style>
.v-selection-control__input input {
  /* checkbox hack do not change */
  opacity: 1 !important;
  /* checkbox hack do not change */
}


</style>
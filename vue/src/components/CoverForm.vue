<template>
  <div class="d-flex justify-center align-center">
    <v-form v-on:submit.prevent="submitForm" :rules="dateRules">
      <v-container>
        <v-row>
          <v-col cols="12" md="8">
            <v-row>
              <v-col cols="12">
                <v-textarea
                  label="Reason for request (optional)"
                  v-model="coverReq.employeeMessage"
                  outlined
                  dense
                ></v-textarea>
                <v-checkbox
                  label="Is this an emergency?"
                  v-model="coverReq.emergency"
                ></v-checkbox>
              </v-col>
              <v-col cols="12">
                <v-btn variant="tonal" class="mr-4" color="green" type="submit">Submit</v-btn>
                <v-btn  color="error" type="button" v-on:click="cancelForm"
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
  </div>
</template>

<script>
// import emailjs from '@emailjs/browser';


export default {
  data() {
    return {
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
      ],
    };
  },
  methods: {
    sendRequestEmail() {

      const fromName = this.$store.state.employeeName; // Example: this.userName or this.form.name
      const emergency = this.coverReq.emergency ? "This is an emergency request!" : ""
      const message = "The employee, " + this.$store.state.employeeName + "has requested time off for the day of " +
        this.date +
        ". They have included the following message: " +
        this.coverReq.message + ". " + emergency; // Example: this.form.message
      const replyTo = this.$store.state.loggedInEmployee.email; // Example: this.form.email
      const templateParams = {
        from_name: fromName,
        message: message,
        reply_to: replyTo
      };


      emailjs.send('service_xsowi2y', 'template_r7geovx', templateParams)
        .then(function (response) {
          console.log('SUCCESS!', response.status, response.text);
        }, function (error) {
          console.log('FAILED...', error);
        });
    },
    submitForm() {
      console.log("this.coverReq prior to dispatch is ", this.coverReq);
      this.$store
        .dispatch("createNewRequest", this.coverReq)
        .then((response) => {
          this.$router.push({ name: "dashboard" });
        })
        .catch((error) => {
          console.error("Failed to submit", error);
        }),
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
};
</script>

<style>
.v-selection-control__input input {
  opacity: 0.5 !important;
}
</style>
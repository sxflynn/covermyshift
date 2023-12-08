<template>
   <h2>Submit a time off request</h2>


<div class="d-flex justify-center align-center">
  <v-form v-on:submit.prevent="submitForm">
    <v-container>
  <v-row>
    <!-- Column for Form -->
    <v-col cols="12" md="8">
      <v-form v-on:submit.prevent="submitForm">
        <v-row>
          <!-- Form Fields -->
          <v-col cols="12">
            <v-text-field label="Employee ID" v-model="coverReq.employeeId" outlined dense></v-text-field>
            <v-text-field label="Name" v-model="coverReq.employeeName" outlined dense></v-text-field>
            <v-checkbox label="Is this an emergency?" v-model="coverReq.emergency"></v-checkbox>
            <v-text-field label="Reason for request (optional)" v-model="coverReq.employeeMessage" outlined dense></v-text-field>
          </v-col>

          <!-- Buttons -->
          <v-col cols="12">
            <v-btn class="mr-4" color="primary" type="submit">Submit</v-btn>
            <v-btn color="error" type="button" v-on:click="cancelForm">Cancel</v-btn>
          </v-col>
        </v-row>
      </v-form>
    </v-col>

    <!-- Column for Date Picker -->
    <v-col cols="12" md="4">
      <v-date-picker elevation="5" show-adjacent-months color="primary" v-model="coverReq.date">
      </v-date-picker>
    </v-col>
  </v-row>
</v-container>


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
        employeeId: null,
        employeeName: "",
        date: null,
        employeeMessage: "",
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
    
  },
};
</script>

<style>
.v-selection-control__input input{
  opacity: 0.5 !important;
}
</style>
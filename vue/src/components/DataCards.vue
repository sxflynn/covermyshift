<template>

    <!-- Admin v-container -->
   <v-container v-if="!isUser" id="data">
     <v-row justify="center">
       <v-col cols="12" sm="3">
         <v-card
           height="8rem"
           elevation="3"
           color="red-lighten-1"
           class="cardTop"
           title="Emergency Requests"
         id="cardTop">
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
           >
             {{ calcEmergencies }}</v-card-text
           >
         </v-card>
       </v-col>
       <v-col elevation="3" cols="12" sm="3">
         <v-card
           class="cardTop"
           height="8rem"
           color="blue-lighten-4"
           title="Unclaimed Shifts"
         >
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
           >
             {{ calcUnclaimedShifts }}</v-card-text
           >
         </v-card>
       </v-col>
       <v-col elevation="3" cols="12" sm="3">
         <v-card
           class="cardTop"
           height="8rem"
           title="Shifts Covered"
           color="grey-lighten-4"
         >
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
             >{{ calcPercentOfTotalShiftsHaveVolunteer }}%</v-card-text
           >
         </v-card>
       </v-col>
       <v-col elevation="3" cols="12" sm="3">
         <v-card
           class="cardTop"
           height="8rem"
           color="blue-grey-lighten-3"
           title="Rejected Requests"
         >
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
             >{{ calcRejected }}</v-card-text
           >
         </v-card>
       </v-col>
     </v-row>
   </v-container>
   
      <!-- Teacher v-container -->
   <v-container v-else id="data">
     <v-row justify="center">
       <v-col cols="12" sm="3">
         <v-card
           height="8rem"
           elevation="3"
           color="red-lighten-1"
           class="cardTop"
           title="Emergency Requests"
          
         >
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
           >
             {{ calcEmergencies }}</v-card-text
           >
         </v-card>
       </v-col>
       <v-col elevation="3" cols="12" sm="3">
         <v-card
           class="cardTop"
           height="8rem"
           color="blue-lighten-4"
           title="Unclaimed Shifts"
           
         >
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
           >
             {{ calcUnclaimedShifts }}</v-card-text
           >
         </v-card>
       </v-col>
       <v-col elevation="3" cols="12" sm="3">
         <v-card
           class="cardTop"
           height="8rem"
           title="Vacation Days"
           color="grey-lighten-4"
         >
         <!-- Hard coded data for capstone demo -->
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
             >{{ 12 - calcApprovedRequests }}</v-card-text
           >
         </v-card>
       </v-col>
       <v-col elevation="3" cols="12" sm="3">
         <v-card
           class="cardTop"
           height="8rem"
           color="blue-grey-lighten-3"
           title="Shifts covered this year"
         >
         <!-- Hard coded data for capstone demo -->
           <v-card-text
             class="text-h3 mt-4"
             style="display: flex; align-items: center; justify-content: center"
             >{{5 + calcShiftVolunteers}}</v-card-text
           >
         </v-card>
       </v-col>
     </v-row>
   </v-container>
 
 </template>
 <script>
 export default {
   components: {

   },
 
   data() {
     return {
       unclaimedShifts: null,
       emergencyReqs: null,
       rejectedReqs: null,
       tab: null,
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
   },
   computed: {
     isUser() {
       return this.$store.state.user.authorities[0].name === "ROLE_USER";
     },
     calcEmergencies() {
       let reqArr = this.$store.state.listReqArr;
       let emergencyCounter = 0;
       reqArr.forEach((req) => {
         if (req.emergency === true) {
           emergencyCounter++;
         }
       });
       return emergencyCounter;
     },
     calcShiftVolunteers() {
       const shiftArr = this.$store.state.listShiftArr;
       let volunteerCounter = 0;
       shiftArr.forEach((shift) => {
         if (shift.shiftVolunteerId) {
            volunteerCounter++;
         }
       });
       return volunteerCounter;
     },
     calcApprovedRequests() {
       let reqArr = this.$store.state.listReqArr;
       let approvedCounter = 0;
       reqArr.forEach((req) => {
         if (req.approved === true) {
           approvedCounter++;
         }
       });
       return approvedCounter;
     },
     calcUnclaimedShifts() {
       return this.$store.state.listUncoveredShiftsArr.length;
     },
     calcPercentOfTotalShiftsHaveVolunteer() {
       if (this.$store.state.listUncoveredShiftsArr.length === 0) {
           return 0;
       }
       return (
         100 -
         (
           this.$store.state.listUncoveredShiftsArr.length /
           this.$store.state.listAllShiftArr.length
         ).toPrecision(1) *
           100
       );
     },
     calcRejected() {
       let reqArr = this.$store.state.listReqArr;
       let emergencyCounter = 0;
       reqArr.forEach((req) => {
         if (req.approved === false && req.pending === false) {
           emergencyCounter++;
         }
       });
       return emergencyCounter;
     },
   },
   mounted() {
     this.$store.dispatch("fetchAllUncoveredShifts");
     this.$store.dispatch("fetchCurrentListShiftArr"); 
     this.$store.dispatch("fetchCurrentAndFutureRequests"); 
   },
   watch: {},
 };
 </script>
 <style>
 .v-sheet--offset {
   top: -24px;
   position: relative;
 }
 .cardTop{
  font-family: "League Spartan";
  text-align: center;
  
 }
 </style>
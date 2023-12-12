<template>
  <v-app id="capstone-app">
    <v-container fluid>

      <div id="nav">
        <v-app-bar>
          <router-link class="no-link-style" v-bind:to="{ name: 'home' }">
            <v-img :width="350" aspect-ratio="16/9" cover src="../src/assets/coverlogosmall.png"></v-img>
          </router-link>
          <v-spacer></v-spacer>
          <div v-if="isLoggedIn">
            <v-avatar color="info">
              {{ displayAuthority }}
            </v-avatar>
            <v-chip variant="text" id="welcome">{{ displayWelcome }}</v-chip>
            <router-link v-if="isLoggedIn" v-bind:to="{ name: 'form' }">
              <v-btn color="black" class="navBtn" size="large">Request Off</v-btn>
            </router-link>
            <router-link v-bind:to="{ name: 'dashboard' }">
              <v-btn color="black" class="navBtn" size="large">Dashboard</v-btn>
              <v-badge v-if="numberOfUncoveredShifts>0" :content="numberOfUncoveredShifts" offset-y="-20" offset-x="10" bordered="true" color="red">
              </v-badge>
            </router-link>

            <router-link v-bind:to="{ name: $store.state.token != '' ? 'logout' : 'login' }">
              <v-btn color="black" variant="" class="navBtn" size="large">{{ $store.state.token != '' ? 'Logout' :
                'Login' }}</v-btn>
            </router-link>
          </div>

          <router-link v-else v-bind:to="{ name: 'register' }">
            <v-btn color="blue" variant="outlined" class="navBtn">Sign Up</v-btn>
          </router-link>
        </v-app-bar>

      </div>
      <v-divider :thickness="6" color="info"></v-divider>

      <div class="router-view-container">
        <router-view />
      </div>


    </v-container>
    <footer @click="handleClick" style="color: rgb(40, 56, 61); cursor: pointer; display: flex; justify-content: space-evenly; word-spacing: 5px; padding: 20px; font-size: 10px;">
      <p>Copyright</p>
      <p>Information</p>
      <p>Privacy</p>
      <p>Tech Elevator</p>
      <p>Contact</p>
    </footer>
     </v-app>
</template>
<script>
export default {
  components: {},
  methods: {
    handleClick() {
      console.log("Paragraph clicked!");
    },
    printPage() {
      window.print();
    },
  },
  computed: {
    numberOfUncoveredShifts(){
      console.log("this.$store.state.listUncoveredShiftsArr.length is", this.$store.state.listUncoveredShiftsArr.length);
      return this.$store.state.listUncoveredShiftsArr.length;
        },
    isLoggedIn() {
      console.log("The username is ", this.$store.state.user.username)
      return this.$store.state.user.username !== undefined;
    },
    displayWelcome() {
      const user = this.$store.state.user;
      if (user && user.username && user.authorities && user.authorities.length > 0) {
        return `Welcome, ${this.displayEmployeeName}`

      }
      return '';
    },
    displayEmployeeName() {
      // Check if user object exists and has a username property
      console.log("this.$store.state.loggedInEmployee: ", this.$store.state.loggedInEmployee)
      if (this.$store.state.loggedInEmployee) {
        return this.$store.state.loggedInEmployee.employeeName;
      }
      return '';
    },
    displayUsername() {
      // Check if user object exists and has a username property
      if (this.$store.state.user && this.$store.state.user.username) {
        return this.$store.state.user.username;
      }
      return '';
    },
    displayAuthority() {
      if (this.$store.state.user && this.$store.state.user.authorities[0].name) {
        const authority = this.$store.state.user.authorities[0].name;
        if (authority === 'ROLE_USER') {
          return 'User';
        }
        if (authority === 'ROLE_ADMIN') {
          return 'Boss';
        }
      }
      return '';
    },
  },



};
</script>
<style>
#logo {

  font-family: "Changa One", sans-serif;
  font-weight: bold;
  font-size: large;
}

.no-link-style {
  text-decoration: none;
  color: inherit;
  /* Inherits the color from the parent element */
}

.router-view-container {
  margin-top: 64px;

  max-width: 1350px;
  /* Set the maximum width */
  margin-left: auto;
  /* Automatic margin on the left */
  margin-right: auto;
  /* Automatic margin on the right */

}

.navBtn {
  font-family: "League Spartan";

  font-weight: bolder;

}




#welcome {
  font-family: "League Spartan";
  text-transform: uppercase;
}
</style>



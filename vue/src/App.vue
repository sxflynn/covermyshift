<template>
  <v-app id="capstone-app">
    <v-container fluid>

      <div id="nav">
        <v-app-bar>
          <router-link class="no-link-style" v-bind:to="{ name: 'home' }">
            <v-img :width="350" aspect-ratio="16/9" cover src="../src/assets/coverlogosmall.png"></v-img>
            <!-- <div id="logo">Cover My Shift</div> -->
          </router-link>
          <!-- <v-img :width="300" aspect-ratio="16/9" cover
              src="../src/assets/CoverMyShiftLOGO#1.png"></v-img> -->
          <v-spacer></v-spacer>
          <div v-if="isLoggedIn">
            <v-chip v-if="isLoggedIn" id="welcome">{{ displayWelcome }}</v-chip>
            <router-link v-if="isLoggedIn" v-bind:to="{ name: 'form' }">
              <v-btn color="black" class="navBtn" size="large">Submit Request</v-btn>
            </router-link>
            <router-link v-if="isLoggedIn" v-bind:to="{ name: 'requestview' }">
              <v-btn color="black" class="navBtn" size="large">View Requests</v-btn>
            </router-link>
            <router-link v-if="isLoggedIn" v-bind:to="{ name: 'teacherview' }">
              <v-btn color="black" class="navBtn" size="large">View Shifts</v-btn>
            </router-link>
  
            <router-link v-if="isLoggedIn" v-bind:to="{ name: $store.state.token != '' ? 'logout' : 'login' }">
              <v-btn color="black" variant="outlined" class="navBtn" size="large">{{ $store.state.token != '' ? 'Logout' :
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
    
  </v-app>
</template>
<script>
export default {
  components: {},
  computed: {
    isLoggedIn() {
      console.log("The username is ", this.$store.state.user.username)
      return this.$store.state.user.username !== undefined;
    },
    displayWelcome() {
      const user = this.$store.state.user;
      if (user && user.username && user.authorities && user.authorities.length > 0) {
        return `Welcome, ${this.displayEmployeeName}`

        // const username = user.username;
        // const authority = user.authorities[0].name;
        // let role = '';
        // if (authority === 'ROLE_USER') {
        //   role = 'User';
        // } else if (authority === 'ROLE_Admin') {
        //   role = 'Admin';
        // }

        // return `Welcome! ${username}. You have access level ${role}.`;
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
        if (authority === 'ROLE_Admin') {
          return 'Admin';
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

  /* Adjust based on the actual height of your v-app-bar */
  .centered-container {
    max-width: 1280px;
    /* Set the maximum width */
    margin-left: auto;
    /* Automatic margin on the left */
    margin-right: auto;
    /* Automatic margin on the right */
  }

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



<template>
  <v-app id="capstone-app">
    <v-container fluid>

      <div id="nav">
        <v-app-bar>
          <router-link class="no-link-style" v-bind:to="{ name: 'home' }">
            <v-img :width="350" aspect-ratio="16/9" cover src="../src/assets/coverlogosmall.png"></v-img>
          </router-link>
          <router-link v-if="isLoggedIn" v-bind:to="{ name: 'about' }">
              <v-btn color="black" class="navBtn" size="large">About Us</v-btn>
            </router-link>
          <v-spacer></v-spacer>
          <div v-if="isLoggedIn">
            <v-avatar color="light-blue-lighten-4">
              {{ displayAuthority }}
            </v-avatar>
            <v-chip variant="text" id="welcome">{{ displayWelcome }}</v-chip>
            <router-link v-if="isLoggedIn" v-bind:to="{ name: 'form' }">
              <v-btn color="black" class="navBtn" size="large">Request Off</v-btn>
            </router-link>
            <router-link v-bind:to="{ name: 'dashboard' }">
              <v-btn color="black" class="navBtn" size="large">Dashboard</v-btn>
              <v-badge v-if="numberOfUncoveredShifts > 0" :content="numberOfUncoveredShifts" offset-y="-20" offset-x="10"
                bordered=true color="red">
              </v-badge>
            </router-link>

            <router-link v-bind:to="{ name: $store.state.token != '' ? 'logout' : 'login' }">
              <v-btn color="black" class="navBtn" size="large">{{ $store.state.token != '' ? 'Logout' :
                'Login' }}</v-btn>
            </router-link>
          </div>

          <router-link v-else v-bind:to="{ name: 'register' }">
            <v-btn color="black" variant="outlined" size="large" class="navBtn">Sign Up</v-btn>
          </router-link>
        </v-app-bar>

      </div>
      <v-divider :thickness="6" color="info"></v-divider>

      <div class="router-view-container">
        <router-view />
      </div>


    </v-container>
    <v-footer color="white" id="footer">
      <ul class="footer-list">
        <li>Copyright</li>
      <li>Information</li>
      <li>Privacy</li>
      <li>Tech Elevator</li>
      <li>Contact</li>
      </ul>

    </v-footer>
  </v-app>
</template>
<script>
export default {
  components: {},
  methods: {
    handleClick() {
      console.log("Paragraph clicked!");
    },
  },
  computed: {
    numberOfUncoveredShifts() {
      console.log("badge icon now ", this.$store.state.listUncoveredShiftsArr.length);
      return this.$store.state.listUncoveredShiftsArr.length;
    },
    isLoggedIn() {
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
  flex-grow: 1;;
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

#footer {
  font-family: "League Spartan";
    text-transform: uppercase;
  cursor: pointer;
  display: flex;
  justify-content: space-evenly;
  word-spacing: 5px;
  padding: 20px;
  font-size: 1rem;
  align-items: end; /* Aligns items vertically */
}

  /* Additional styles for the horizontal list */
  .footer-list {
    list-style: none; /* Removes bullet points */
    display: flex; /* Enables flexbox */
    justify-content: center; /* Centers the list items */
    
    padding: 0; /* Removes default padding */
    margin: 0; /* Removes default margin */
  }

  .footer-list li {
    margin-right: 30px; /* Adds spacing between list items */
  }

  .footer-list li:last-child {
    margin-right: 0; /* Removes margin for the last list item */
  }


#welcome {
  font-family: "League Spartan";
  text-transform: uppercase;
}



</style>



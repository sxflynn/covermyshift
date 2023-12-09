<template>
  <v-app id="capstone-app">
    <v-container fluid>

      <div id="nav">
        <v-app-bar>
          <router-link class="no-link-style" v-bind:to="{ name: 'home' }">
            <div id="logo">Cover My Shift</div>
          </router-link>
          <v-spacer></v-spacer>
         {{displayWelcome}}


          <router-link v-bind:to="{ name: 'form' }">
            <v-btn>Submit Request</v-btn>
          </router-link>
          <router-link v-bind:to="{ name: 'requestview' }">
            <v-btn>View Requests</v-btn>
          </router-link>
          <router-link v-bind:to="{ name: 'teacherview' }">
            <v-btn>View Shifts</v-btn>
          </router-link>

          <router-link v-bind:to="{ name: $store.state.token != '' ? 'logout' : 'login' }">
            <v-btn variant="outlined">{{ $store.state.token != '' ? 'Logout' : 'Login' }}</v-btn>
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
    displayWelcome() {
    const user = this.$store.state.user;
    console.log("token is " ,this.$store.state.token)
    if (user && user.username && user.authorities && user.authorities.length > 0) {
      const username = user.username;
      const authority = user.authorities[0].name;

      let role = '';
      if (authority === 'ROLE_USER') {
        role = 'User';
      } else if (authority === 'ROLE_Admin') {
        role = 'Admin';
      }

      return `Welcome! ${username}. You have access level ${role}.`;
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
      if (this.$store.state.user && this.$store.state.user.authorities[0].name){
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
</style>

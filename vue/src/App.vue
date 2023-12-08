<template>
  <v-app id="capstone-app">
<v-container fluid>
  
      <div id="nav">
       <v-app-bar> 
        <router-link class="no-link-style" v-bind:to="{ name: 'home' }">
          <div id="logo">Cover My Shift</div>
        </router-link>
        <v-spacer></v-spacer>

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
  
      <router-view />
</v-container>
  </v-app>
</template>
<script>
export default {
  components: {},
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
  color: inherit; /* Inherits the color from the parent element */
}
</style>

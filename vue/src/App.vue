<template>
  <div id="capstone-app">
    <div id="nav">
      <div id="logo">Cover My Shift</div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'form' }">Form</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'requestview' }">Requests</router-link>&nbsp;|&nbsp;
      
      <router-link v-bind:to="{ name: $store.state.token != '' ? 'logout' : 'login' }">
        {{ $store.state.token != '' ? 'Logout' : 'Login' }}
      </router-link>
    </div>

    <router-view />
  </div>
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
  font-size: xx-large;
}
</style>

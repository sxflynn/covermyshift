<template>
  <div id="capstone-app">
    <div id="nav">
      <div id="logo">
      Cover My Shift 
      </div>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'dashboard'}">Dashboard</router-link>
    </div>
  
    <router-view />
  </div>
</template>
<script>



export default{


components: {
  

}
}

</script>
<style>
#logo {
  font-family: 'Changa One', sans-serif;
  font-weight: bold;
  font-size: xx-large;
 

}

</style>

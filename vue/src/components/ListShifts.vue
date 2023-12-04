<template>
  <div>
    <h1>I am the list</h1>
    <li v-bind="shift" v-for="item in shift" v-bind:key="item">
      {{ shift.shiftOwnerName }} : {{ shift.shiftDate }}
      <button>Accept</button>
      <button>Reject</button>
    </li>
  </div>
</template>

<script>
export default {
  props: ["shift"],
};
</script>

<style>
</style>
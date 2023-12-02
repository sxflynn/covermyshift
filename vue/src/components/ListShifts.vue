<template>
  <div>
  <li v-bind="shift" v-for="item in shift" v-bind:key="item"  >{{ shift}}</li>
</div>
</template>

<script>
export default {
    props: ['shift']

}
</script>

<style>

</style>
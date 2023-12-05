<template>
  <div>
    <ul class="shiftList">
      <span id="nameColumn">Name</span>
      <span id="dateColumn"> Date</span>

      <li
        v-bind="coverReq"
        v-for="item in coverReq"
        v-bind:key="item"
        class="listRow"
      >
        <span id="shiftName">{{ coverReq }}</span> :
        <span id="shiftDate">{{ coverReq }}</span>
        <button id="accept">Accept</button>
        <button id="reject">Reject</button>
      </li>
    </ul>
  </div>
</template>

<script>
import RequestService from "../services/RequestService";
export default {
  props: ["coverReq"],
  methods: {
    
  },
 
};
</script>

<style>
#accept {
  background-color: rgb(56, 249, 66);

  border-radius: 5px;
  border: 1px solid transparent;
  padding: 0em 0.2em;
  font-size: 1em;
  font-weight: 1000;
  font-family: inherit;
  cursor: pointer;
  transition: border-color 0.25s;
  margin-left: 2.2%;
}
#reject {
  background-color: rgb(255, 56, 25);
  border-radius: 5px;
  border: 1px solid transparent;
  padding: 0em 0.2em;
  font-size: 1em;
  font-weight: 1000;
  font-family: inherit;
  cursor: pointer;
  transition: border-color 0.25s;
}
#dateColumn {
  text-underline-position: below;
  margin-left: 1%;
}
</style>
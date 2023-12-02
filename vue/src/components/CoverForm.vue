<template>
    <h1>I am the form</h1>
    <form v-on:submit.prevent="submitForm">
    <div class="field">
      <label for="date">Date</label>
      <input type="text" id="date" name="date"/>
    </div>
    <div class="actions">
      <button class="btn-submit" type="submit">Submit</button>
      <button class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
    </div>
  </form>
  
</template>

<script>
export default {
    data(){
        return{
            coverRequest: {
                shift: {
    isCovered : true,
    shiftOwnerId : null,
    shiftVolunteerId : null,
    shiftDate : "",
    companyId : null
},

    employee: {
    employeeId : null,
    employeeName : "",
    username : "",
    email : "",
    companyId : null
            }
        }
    }

}
}
</script>

<style>

</style>
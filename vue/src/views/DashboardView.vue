<template>
    <div>
        <h1>Hello</h1>
        <list-shifts v-bind:shift="shift"/> <h1>I exist</h1>
    </div>

    <div> 
    <!-- <li v-bind="shift" v-for="item in shift" v-bind:key="item"  >{{ shift}}</li> -->
    </div>

</template>
<script>
import ListShifts from '../components/ListShifts.vue';

export default{
    components: {
        ListShifts
    },
    data(){
        return {
        test: [],
    shift: {
    isCovered : true,
    shiftOwnerId : 1,
    shiftVolunteerId : null,
    shiftDate : "2023-12-01",
    companyId : 1
},

    employee: {
    employeeId : 1,
    employeeName : "Steve",
    username : "user",
    email : "user@user.com",
    companyId : 1
}
    }
}
}
</script>
<style scoped></style>
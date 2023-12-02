<template>
    <div>
        <h1>I am the dashboard</h1>
        <list-shifts v-bind:shift="shift"/> <h1>I exist</h1>

    </div>
    

    <div> 
        <cover-form/>
    </div>

</template>
<script>
import ListShifts from '../components/ListShifts.vue';
import CoverForm from '../components/CoverForm.vue';


export default{
    components: {
        ListShifts,
        CoverForm
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
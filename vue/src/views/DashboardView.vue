<template>
<<<<<<< HEAD
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
=======
    <div></div>
    <!-- 
    Contains components: CoverForm, ListShifts -->

    <!-- Fake JSON -->
    const shift = {
    shiftId : 1,
    covered : true,
>>>>>>> 853fc47699ec2ef94cd6ede53ad2c6fb6b6864ab
    shiftOwnerId : 1,
    shiftOwnerName : "Steve",
    shiftDate : "2023-12-01",
    companyId : 1
<<<<<<< HEAD
},

    employee: {
=======
    }

const request = {
    employeeId : 1,
    employeeName : "Steve",
    date : "2023-12-01"
}

    const employee = {
>>>>>>> 853fc47699ec2ef94cd6ede53ad2c6fb6b6864ab
    employeeId : 1,
    employeeName : "Steve",
    username : "user",
    email : "user@user.com",
    companyId : 1
<<<<<<< HEAD
}
    }
}
}
</script>
<style scoped></style>
=======
    }
</template>
<script></script>
<style></style>
>>>>>>> 853fc47699ec2ef94cd6ede53ad2c6fb6b6864ab

<template>
  <div id="login">
    <v-row>
      <v-col cols="14" md="7">
        <v-img :width="700" aspect-ratio="16/9" cover src="../src/assets/coverlogosmall-cropped.png" id="logo"></v-img>
       
        <!-- Left column with images -->
        <v-img :width="900" src="../src/assets/classroom-optimized.jpg" id="classroom" label="rounded-xl">></v-img>
      </v-col>
      <v-col cols="12" md="5">
        <!-- Right column with form and content -->

        <div id="formWithLogo">
          <v-form @submit.prevent="login">
          <v-img class="mx-auto pa-12 pb-8" max-width="450" max-height="1000"
            src="../src/assets/slogancropped.png"></v-img>

          <v-card v-if="!isLoggedIn" class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
            <div class="allText">Account</div>

            <v-text-field class="allText" v-model="user.username" density="compact" placeholder="Username"
              prepend-inner-icon="mdi-email-outline" variant="outlined"></v-text-field>

            <div class="allText">
              Password

            </div>

            <v-text-field class="allText" v-model="user.password" :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
              :type="visible ? 'text' : 'password'" density="compact" placeholder="Enter your password"
              prepend-inner-icon="mdi-lock-outline" variant="outlined"
              @click:append-inner="visible = !visible"  @input="invalidCredentials = false"></v-text-field>

          
              <v-alert v-if="invalidCredentials" icon="mdi-firework" type="error" closable title="Wrong username or password"></v-alert>
           
  
            <v-btn @click="login" :block=true color="green" size="large" variant="tonal" type="submit" class="allText mb-2" >
           
              Log In
            </v-btn>
       
            <router-link class="text-decoration-none" v-bind:to="{ name: 'register' }">
              <v-btn :block=true color="blue" size="large" variant="tonal" type="submit" class="allText">
           
           Sign Up Now
         </v-btn>
              
      
            </router-link>

          </v-card>
          <v-img v-else :width="500" src="../src/assets/logEyes.png" id="loggedIn" label="rounded-xl"></v-img>
          
        </v-form>
        </div>
      </v-col>
    </v-row>

  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      visible: false,
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      console.log("clicked submit");
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

            authService.whoami()
              .then(employeeResponse => {
                if (employeeResponse.status === 200) {
                  this.$store.commit("SET_EMPLOYEE_INFO", employeeResponse.data);
                }
              })
              .catch(employeeError => {
                console.error('Error fetching employee info:', employeeError);
                // Handle error (e.g., show notification or log error)
              });

            
            const authority = this.$store.state.user.authorities[0].name;
            if (authority === 'ROLE_USER') {

              this.$router.push("/dashboard");
            } else if (authority === 'ROLE_ADMIN') {

              this.$router.push("/dashboard#allrequests");
            }
            
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
            // I want to 
          }
        });
    }
  },
  computed:{
    isLoggedIn() {
      return this.$store.state.user.username !== undefined;
    },
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

.allText {
  font-family: "League Spartan";
}

#rightImg {
  margin-top: -200px;
  margin-left: -50px;

}

#classroom{
  margin-top:2.5rem;
  margin-left: 10px;
  border-radius: 10%;

}
#coverMessage{
  font-family:"League Spartan" ;
  font-size: xx-large;
  font-weight: 600;
}

#formWithLogo {
  margin-top: 45px;
  
}
#loggedIn{
  margin-left: 25px;

  
}
</style>
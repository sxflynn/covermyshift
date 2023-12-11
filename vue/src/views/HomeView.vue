<template>
  <div id="login">
    <v-row>
      <v-col cols="14" md="7">
        <v-img :width="700" aspect-ratio="16/9" cover src="../src/assets/coverlogosmall-cropped.png"></v-img>
       
        <!-- Left column with images -->
        <v-img :width="900" aspect-ratio="16/9" cover src="../src/assets/classroomrounded.png" id="classroom" rounded="lg"></v-img>
      </v-col>
      <v-col cols="12" md="5">
        <!-- Right column with form and content -->

        <div id="formWithLogo">
          <v-img class="mx-auto pa-12 pb-8" max-width="450" max-height="1000"
            src="../src/assets/slogancropped.png"></v-img>

          <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
            <div class="text-subtitle-1 text-medium-emphasis">Account</div>

            <v-text-field v-model="user.username" density="compact" placeholder="Username"
              prepend-inner-icon="mdi-email-outline" variant="outlined"></v-text-field>

            <div class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between">
              Password

            </div>

            <v-text-field v-model="user.password" :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
              :type="visible ? 'text' : 'password'" density="compact" placeholder="Enter your password"
              prepend-inner-icon="mdi-lock-outline" variant="outlined"
              @click:append-inner="visible = !visible"></v-text-field>

            <v-card class="mb-12" color="surface-variant" variant="tonal">
              <v-card-text class="text-subtitle-2">
                Warning: After 3 consecutive failed login attempts, you account will
                be temporarily locked out for three hours
              </v-card-text>
            </v-card>

            <v-btn @click="login" block="" color="green" size="large" variant="tonal" type="submit" class="allText">
              Log In
            </v-btn>

            <v-card-text class="text-center">
              <a class="text-blue text-decoration-none" href="#" rel="noopener noreferrer" target="_blank">
                Sign up now <v-icon icon="mdi-chevron-right"></v-icon>
              </a>
            </v-card-text>
          </v-card>
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

            const username = this.$store.state.user.username;
            const authority = this.$store.state.user.authorities[0].name;
            if (authority === 'ROLE_USER') {

              this.$router.push("/teacherview");
            } else if (authority === 'ROLE_ADMIN') {

              this.$router.push("/requestview");
            }
            
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
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
  margin-top: -100px;
  margin-left: -18px;
  

}
#coverMessage{
  font-family:"League Spartan" ;
  font-size: xx-large;
  font-weight: 600;
}

#formWithLogo {
  margin-top: 30px;
}
</style>
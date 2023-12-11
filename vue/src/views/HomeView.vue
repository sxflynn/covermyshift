<template>
  <v-app id="capstone app">
    <div id="login">
      <v-row>
        <v-col cols="12" md="6">
          <!-- Left column with images -->
          <v-img :width="700" aspect-ratio="16/9" cover src="../src/assets/TwirlBlue.png"></v-img>
        </v-col>
        <v-col cols="12" md="6">
          <!-- Right column with form and content -->
          <v-img :width="700" aspect-ratio="16/9" cover
              src="../src/assets/coverlogosmall.png" id="rightImg"></v-img>
          <form v-on:submit.prevent="login">
            <h1 class="allText">LOGIN</h1>
            <div role="alert" v-if="invalidCredentials">Invalid username and password!</div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
            <div class="form-input-group">
              <label for="username">Username</label>
              <input type="text" id="username" v-model="user.username" required autofocus />
            </div>
            <div class="form-input-group">
              <label for="password">Password</label>
              <input type="password" id="password" v-model="user.password" required />
            </div>
            <button type="submit">Sign in</button>
            <p>
              <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
            </p>
          </form>
        </v-col>
      </v-row>
    
    </div>

  </v-app>
  <div>
    <v-img
      class="aboveForm"
      max-width="600"
      max-height="800"
      src="../src/assets/coverlogosmall.png"
    ></v-img>

    <v-card
      class="mx-auto pa-12 pb-8"
      elevation="8"
      max-width="448"
      rounded="lg"
    >
      <div class="text-subtitle-1 text-medium-emphasis">Account</div>

      <v-text-field
        density="compact"
        placeholder="Email address"
        prepend-inner-icon="mdi-email-outline"
        variant="outlined"
      ></v-text-field>

      <div
        class="text-subtitle-1 text-medium-emphasis d-flex align-center justify-space-between"
      >
        Password

        <a
          class="text-caption text-decoration-none text-blue"
          href="#"
          rel="noopener noreferrer"
          target="_blank"
        >
          Forgot login password?</a
        >
      </div>

      <v-text-field
        :append-inner-icon="visible ? 'mdi-eye-off' : 'mdi-eye'"
        :type="visible ? 'text' : 'password'"
        density="compact"
        placeholder="Enter your password"
        prepend-inner-icon="mdi-lock-outline"
        variant="outlined"
        @click:append-inner="visible = !visible"
      ></v-text-field>

      <v-card class="mb-12" color="surface-variant" variant="tonal">
        <v-card-text class="allText">
          Warning: After 3 consecutive failed login attempts, you account will
          be temporarily locked for three hours. If you must login now, you can
          also click "Forgot login password?" below to reset the login password.
        </v-card-text>
      </v-card>

      <v-btn block=""  color="green" size="large" variant="tonal" class="allText">
        Log In
      </v-btn>

      <v-card-text class="text-center">
        <a
          class="text-blue text-decoration-none"
          href="#"
          rel="noopener noreferrer"
          target="_blank"
        >
          Sign up now <v-icon icon="mdi-chevron-right"></v-icon>
        </a>
      </v-card-text>
    </v-card>
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


            this.$router.push("/");
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
.allText{
  font-family: "League Spartan";
}
#rightImg{
  margin-top: -200px;
  margin-left: -50px;
  
}
.aboveForm{
  margin-left: 370px;
  margin-bottom: -100px;
}

</style>
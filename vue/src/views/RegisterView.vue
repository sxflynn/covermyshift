<template>
  <v-card class="mx-auto pa-12 pb-8" elevation="8" max-width="448" rounded="lg">
    <h1>Create Account</h1>
    <v-alert v-if="registrationErrors" type="error">{{ registrationErrorMsg }}</v-alert>

    <div v-on:submit.prevent="register">
      <v-text-field
        v-model="user.username" label="Username"  dense placeholder="Enter your username" outlined required
        autofocus
          ></v-text-field>

      <v-text-field
        v-model="user.password" label="Password" dense
        placeholder="Enter your password" outlined
        type="password" required
      ></v-text-field>

      <v-text-field  v-model="user.confirmPassword" label="Confirm Password" dense
        placeholder="Confirm your password" outlined type="password" required
      ></v-text-field>

      <v-btn type="submit" color="primary">Create Account</v-btn>
      <p><router-link v-bind:to="{ name: 'home' }">Already have an account? Log in</router-link></p>
    </div>
  </v-card>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>

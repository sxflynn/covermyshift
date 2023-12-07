<template>
  <v-app>
    <v-container class="hometitle">
      <v-row id="hometitle1">
        <v-card><h1>Cover</h1></v-card>
      </v-row>
    </v-container>
    <v-container>
      <v-row id="hometitle2">
        <v-card><h1>My</h1></v-card>
      </v-row>
    </v-container>
    <v-container>
      <v-row id="hometitle3">
        <v-card><h1>Shift</h1></v-card>
      </v-row>
    </v-container>
    <v-container>
      <v-row>
        <v-card
          id="home-description"
          title="Description"
          text="..."
          variant="tonal"
        ></v-card>
      </v-row>
      <v-container class="register-area">
        <v-card id="register-button"><v-btn>Register</v-btn></v-card>
        <v-card><v-text-field clearable label="Email"></v-text-field></v-card>
      </v-container>
    </v-container>
    <v-container class="login">
      <v-row id="general-login-btn">
        <v-card><v-btn>Login</v-btn></v-card>
      </v-row>
      <v-col id="teacher-login-btn">
        <v-card><v-btn>Teacher</v-btn></v-card>
      </v-col>
      <v-col id="admin-login-btn">
        <v-card><v-btn>Admin</v-btn></v-card>
      </v-col>
    </v-container>
    <container>
      <v-data-table :items="items"></v-data-table>
    </container>
  </v-app>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      items: [
        {
          name: "African Elephant",
          species: "Loxodonta africana",
          diet: "Herbivore",
          habitat: "Savanna, Forests",
        },
      ],
    };
  },
};
</script>


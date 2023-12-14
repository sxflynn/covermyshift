<template>
  <div id="about">
    <v-row>
      <v-col id="leftSideAbout" cols="14" md="7">
        <v-img
          :width="700"
          aspect-ratio="16/9"
          cover
          src="../src/assets/coverlogosmall-cropped.png"
          id="logo"
        ></v-img>

        <!-- Left column with images -->
        <v-img
          :width="900"
          src="../src/assets/classroom-optimized.jpg"
          id="classroom"
          label="rounded-xl"
          >></v-img
        >
      </v-col>
      <v-col id="rightSideAbout" md="5" align-self="center" class="">
        <v-container>
          <v-card elevation="6" rounded="xl" class="ml-12">
            <v-card-title class="mx-auto pa-12 pb-5 text-center text-h4">
              Mission Statement
            </v-card-title>
            <v-card-text class="text-body-1 text-large-emphasis pa-12 pb-8">
              <p>
                <strong> Cover My Shift </strong> was borne out of a passion for
                helping teachers curtail unnecessary tedium in order to focus on
                what is truly meaningful for teachers: engaging and mentoring
                their students.
              </p>
            </v-card-text>
            <v-card-text>
              <strong>Developers:</strong>
              <v-chip class="mx-1">Anisa M.</v-chip>
              <v-chip class="mx-1">Gregg S.</v-chip>
              <v-chip class="mx-1">Niko T.</v-chip>
              <v-chip>Stephen F.</v-chip>
            </v-card-text>
          </v-card>
        </v-container>
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
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      console.log("clicked submit");
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);

            authService
              .whoami()
              .then((employeeResponse) => {
                if (employeeResponse.status === 200) {
                  this.$store.commit(
                    "SET_EMPLOYEE_INFO",
                    employeeResponse.data
                  );
                }
              })
              .catch((employeeError) => {
                console.error("Error fetching employee info:", employeeError);
                // Handle error (e.g., show notification or log error)
              });

            const authority = this.$store.state.user.authorities[0].name;
            if (authority === "ROLE_USER") {
              this.$router.push("/dashboard");
            } else if (authority === "ROLE_ADMIN") {
              this.$router.push("/dashboard#allrequests");
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
            // I want to
          }
        });
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

.allText {
  font-family: "League Spartan";
}

#rightImg {
  margin-top: -200px;
  margin-left: -50px;
}

#classroom {
  margin-top: 2.5rem;
  margin-left: 25px;
  border-radius: 10%;
}
#coverMessage {
  font-family: "League Spartan";
  font-size: xx-large;
  font-weight: 600;
}

#formWithLogo {
  margin-top: 45px;
}

#rightSideAbout {
  margin-top: 200px;
  margin-right: -20px;
}
</style>
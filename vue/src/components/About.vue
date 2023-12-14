<template>
  <div id="about">
    <v-row>
      <v-col id="leftSideAbout" cols="14" md="7">
        <v-img :width="700" aspect-ratio="16/9" cover src="../src/assets/coverlogosmall-cropped.png" id="logo"></v-img>

        <!-- Left column with images -->
        <v-img :width="900" src="../src/assets/classroom-optimized.jpg" id="classroom" label="rounded-xl">></v-img>


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
              <a href="https://www.linkedin.com/in/anisa-mohamud/" target="_blank">
                <v-chip class="mx-1">Anisa M.</v-chip>
              </a>
              <a href="https://www.linkedin.com/in/gregg-sanders-developer/" target="_blank">
                <v-chip class="mx-1">Gregg S.</v-chip>
              </a>
              <a href="https://www.linkedin.com/in/nikotatakis/" target="_blank">
                <v-chip class="mx-1">Niko T.</v-chip>
              </a>
              <a href="https://www.linkedin.com/in/sxflynn/">
                <v-chip class="mx-0.9 hover-effect">Stephen F.</v-chip>
              </a>
            </v-card-text>
          </v-card>
        </v-container>

      </v-col>
    </v-row>
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      images: ['../src/assets/classroom-optimized.jpg', '../src/assets/screenshot.png'],
      window: 0
    }
  }
}
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

.hover-effect:hover {
  background-color: #E0E0E0; /* Example hover color */
  cursor: pointer; /* Changes the cursor to indicate it's clickable */
}
</style>
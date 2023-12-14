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
        <!-- <v-img
          :width="900"
          src="../src/assets/classroom-optimized.jpg"
          id="classroom"
          label="rounded-xl"
          >></v-img
        > -->

        <v-window
    v-model="window"
    show-arrows
  >
    <v-window-item v-for="image in images" :key="image">
      <v-card :width="900" class="d-flex justify-center align-center">
        <span class="text-h2">     <v-img
          :width="900"
          :src=image
          id="classroom"
          label="rounded-xl"
          ></v-img
        >
</span>
      </v-card>
    </v-window-item>
  </v-window>



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
export default {
    data(){
        return{
            images:['../src/assets/classroom-optimized.jpg','../src/assets/screenshot.png'],
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
</style>
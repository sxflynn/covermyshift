<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;
      List of Requests

      <v-spacer></v-spacer>

      <v-text-field
        v-model="search"
        prepend-inner-icon="mdi-magnify"
        density="compact"
        label="Search"
        single-line
        flat
        hide-details
        variant="solo-filled"
      ></v-text-field>
    </v-card-title>

    <v-divider></v-divider>
    <v-data-table v-model:search="search" :items="requests">
      <template v-slot:header.stock>
        <div class="text-end">Stock</div>
      </template>

      <!-- <template v-slot:item.image="{ item }">
        <v-card class="my-2" elevation="2" rounded>
          <v-img
            :src="`https://cdn.vuetifyjs.com/docs/images/graphics/gpus/${item.image}`"
            height="64"
            cover
          ></v-img>
        </v-card>
      </template> -->

      <template v-slot:item.approved="{ item }">
        <div class="text-end">
          <v-chip
            :color="item.approved ? 'green' : 'red'"
            :text="item.approved ? 'Approved' : 'Unapproved'"
            class="text-uppercase"
            label
            size="small"
          ></v-chip>
        </div>
      </template>
    </v-data-table>
  </v-card>
</template>
<script>
  export default {
    data () {
      return {
        search: '',
        requests: [
    {
        "requestId": 1,
        "employeeId": 1,
        "employeeName": "Steve C.",
        "date": "2023-12-01",
        "message": "My message",
        "emergency": false,
        "covered": false,
        approved: true,
        "pending": true
    },
    {
        "requestId": 2,
        "employeeId": 1,
        "employeeName": "Steve C.",
        "date": "2023-12-02",
        "message": "My message",
        "emergency": false,
        "covered": false,
        "approved": false,
        "pending": true
    },
    {
        "requestId": 3,
        "employeeId": 2,
        "employeeName": "Rachelle R.",
        "date": "2024-12-02",
        "message": "My message",
        "emergency": false,
        "covered": false,
        "approved": false,
        "pending": true
          },
        ],
      }
    },
  }
</script>
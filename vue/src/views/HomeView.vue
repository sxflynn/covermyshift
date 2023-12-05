<template>
  <v-app>
    <v-container>
      <v-data-table :headers="headers" :items="items" class="elevation-1">
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>My Data Table</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-toolbar>
        </template>
      </v-data-table>
    </v-container>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      headers: [
        { text: "ID", value: "id" },
        { text: "Name", value: "name" },
        { text: "Age", value: "age" },
        // Add more headers as needed
      ],
      items: [
        { id: 1, name: "John Doe", age: 25 },
        { id: 2, name: "Jane Doe", age: 30 },
        // Add more rows as needed
      ],
    };
  },
};
</script>

<style>
/* Add your custom styles here */
</style>

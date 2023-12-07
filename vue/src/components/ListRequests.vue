<template>
  <v-card flat>
    <v-card-title class="d-flex align-center pe-2">
      <v-icon icon="mdi-video-input-component"></v-icon> &nbsp;

      <v-spacer></v-spacer>List of Requests (TODO: Headers issue, specific
      column display, font and style change of data)

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
    <!-- TODO: Add custom headers using the headers prop -->
    <!-- TODO: Customize the items-per-page -->
    <v-data-table
      v-model:search="search"
      :items="reversedRequests"
      :items-per-page="1000"
    >
      <template v-slot:item.requestId="{ item }">
        <div class="text-end">
          <v-chip
            variant="text"
            :text="item.requestId"
            class="text-lowercase"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeId="{ item }">
        <div class="text-end">
          <v-chip
            variant="text"
            :text="item.employeeId"
            class="text-lowercase"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.employeeName="{ item }">
        <div class="text-end">
          <v-chip
            variant="text"
            :text="item.employeeName"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.date="{ item }">
        <div class="text-end">
          <v-chip
            variant="text"
            :text="item.date"
            class="text-lowercase"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.message="{ item }">
        <div class="text-end">
          <v-chip :text="item.message" label size="large"></v-chip>
        </div>
      </template>

      <template v-slot:item.emergency="{ item }">
        <div class="text-end">
          <v-chip
            :color="item.emergency ? 'red' : 'blue'"
            :text="item.emergency ? 'Emergency' : 'Vacation'"
            class="text-uppercase"
            label
            size="large"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.approved="{ item }">
        <v-dialog width="500">
          <template v-slot:activator="{ props }">
            <v-btn color="orange" v-bind="props" text="Accept / Decline">
            </v-btn>
          </template>

          <template v-slot:default="{ isActive }">
            <v-card title="Request off">
              <v-chip size="compact" id="vchipDate">
                Rachelle R. 12-02-2023 (Monday) (Vacation)</v-chip
              >
              <v-card-text> "Camping with my family" </v-card-text>

              <v-card-actions>
                <div class="text-end">
                  <v-btn
                    variant="tonal"
                    color="green"
                    rounded="xl"
                    text="Accept"
                    class="text-uppercase"
                    label
                    size="x-large"
                    id="acceptButton"
                  ></v-btn>
                  <v-dialog width="500">
                    <template v-slot:activator="{ props }">
                      <v-btn
                        v-bind="props"
                        variant="tonal"
                        color="red"
                        rounded="xl"
                        text="Decline"
                        class="text-uppercase"
                        label
                        size="x-large"
                        id="declineButton"
                      ></v-btn>
                    </template>

                    <template v-slot:default="{ isActive }">
                      <v-card title="Reasoning for Decline">
                        <v-card-text>
                          <v-text-field
                            label="Message (optional)"
                          ></v-text-field>
                        </v-card-text>

                        <v-card-actions>
                          <v-btn
                            variant="tonal"
                            color="red"
                            rounded="xl"
                            text="Send & Decline"
                            class="text-uppercase"
                            label
                            size="x-large"
                            id="finalDecline"
                          ></v-btn>
                          <v-spacer></v-spacer>

                          <v-btn
                            text="Cancel"
                            @click="isActive.value = false"
                            id="declineCancel"
                          >
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </template>
                  </v-dialog>
                </div>
                <v-spacer></v-spacer>

                <v-btn
                  text="Cancel"
                  @click="isActive.value = false"
                  id="adCancel"
                ></v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
      </template>

      <template v-slot:item.covered="{ item }">
        <div class="text-end">
          <v-chip
            :color="item.covered ? 'green' : 'red'"
            :text="item.covered ? 'Covered' : 'Uncovered'"
            class="text-uppercase"
            label
            size="large"
            variant="outlined"
          ></v-chip>
        </div>
      </template>

      <template v-slot:item.pending="{ item }">
        <div class="text-end">
          <v-chip
            :color="item.pending ? 'red' : 'green'"
            :text="item.pending ? 'Pending' : 'Finalized'"
            class="text-uppercase"
            label
            size="large"
          ></v-chip>
        </div>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      headers: [
        { text: "Request ID", value: "requestId", align: "start" },
        { text: "Employee ID", value: "employeeId", align: "start" },
        { text: "Employee Name", value: "employeeName", align: "start" },
        { text: "Date", value: "date", align: "start" },
        { text: "Message", value: "message", align: "start" },
        { text: "Emergency", value: "emergency", align: "center" },
        { text: "Covered", value: "covered", align: "center" },
        { text: "Approved", value: "approved", align: "center" },
        { text: "Pending", value: "pending", align: "center" },
      ],
      search: "",

      requests: [
        {
          requestId: 1,
          employeeId: 1,
          employeeName: "Steve C.",
          date: "2023-12-01",
          message: "hello",
          emergency: false,
          covered: false,
          approved: false,
          pending: true,
        },
      ],
    };
  },
  computed: {
    reversedRequests() {
      console.log(...this.$store.state.listReqArr);
      return [...this.$store.state.listReqArr].reverse();
    },
  },
};
</script>
<style>
</style>
<template>
  <div>
    <div id="graphTitle"> TOTAL REQUESTS OFF </div>
    
    <Bar
      id="my-chart-id"
      :options="chartOptions"
      :data="chartData"
    />
  </div>
</template>

<script>
import { Bar } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js';

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: 'BarChart',
  components: { Bar },
  data() {
    return {
      chartData: {
        labels: ['RACHELLE', 'JOHN', 'LAURA', 'BRIAN', 'STEVE'],
        datasets: [
          {
            data: [4, 3, 8, 5, 7],
            backgroundColor: [
            '#FFF1D7', // Light Green
              '#C4D7F2', // Sky Blue
              '#AFDEDC', // Mint Green
              '#91A8A4', // Slate Gray
              '#776871',      
            ],
          },
        ],
      },
      chartOptions: {
        responsive: true,
        scales: {
          y: {
            title: {
              display: true,
              text: 'NUMBER OF REQUESTS OFF MADE',
            },
            ticks: {
              font: {
                family: 'League Spartan', // Use League Spartan font
              },
              fontSize: 100, // Set the font size for axis labels
            },
            
          },
        },   plugins: {
          legend: {
            display: true,
            position: 'top',
            labels: {
              filter: function (legendItem, chartData) {
                // Remove the legend item corresponding to the undefined label
                return chartData.labels[legendItem.index] !== undefined;
              },
            },
          },
        },
      },
    };
  },
};

</script>

<style scoped>
#my-chart-id canvas {
  font-family: 'League Spartan', sans-serif; /* Use League Spartan font */
}

#my-chart-id .chartjs-text {
  font-size: 100px; /* Set the font size for the names */
}
#graphTitle{
  text-align: center;
  font-family: 'League Spartan', sans-serif;
  font-size: larger;
}
</style>

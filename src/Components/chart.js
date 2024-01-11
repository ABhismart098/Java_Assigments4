// import React, { useEffect, useState } from "react";
// import { Bar, Line } from "react-chartjs-2";
// import io from "socket.io-client";
// import {
//   Chart as ChartJS,
//   Title,
//   Tooltip,
//   Legend,
//   BarElement,
//   CategoryScale,
//   LinearScale,
// } from "chart.js";
// ChartJS.register(
//   Title,
//   Tooltip,
//   Legend,
//   BarElement,
//   CategoryScale,
//   LinearScale
// );

// const socket = io("http://localhost:3002"); // Connect to the backend server

// const ChartComponent = ({ chartType, customizationOptions }) => {
//   const [chartData, setChartData] = useState({
//     labels: [],
//     datasets: [
//       {
//         label: "Data",
//         data: [],
//         backgroundColor: "rgba(75,192,192,0.2)",
//         borderColor: "rgba(75,192,192,1)",
//         borderWidth: 1,
//       },
//     ],
//   });

//   const createUpdatedData = (newData) => {
//     let receivedData = newData
//       .filter((data) => data?.end_year !== "" && data?.intensity !== "")
//       .sort((a, b) => a.end_year - b.end_year);
//     if (receivedData?.length > 0) {
//       let updatedData = { ...chartData };
//       updatedData.labels = receivedData.map((row) => row?.end_year);
//       updatedData.datasets[0].data = receivedData.map((row) => row?.intensity);
//       setChartData(updatedData);
//     }
//   };

//   useEffect(() => {
//     socket.on("updateChartData", (newData) => {
//       createUpdatedData(newData);
//     });
//     return () => {
//       socket.off("updateChartData");
//     };
//   }, );

//   const options = {
//     scales: {
//       x: { ...customizationOptions.xAxis },
//       y: { ...customizationOptions.yAxis },
//     },
//   };

//   const Chart = chartType === "bar" ? Bar : Line;

//   if (chartData?.labels?.length === 0) return <></>;

//   return <Chart data={chartData} options={options} />;
// };

// export default ChartComponent;
// import React, { useEffect, useState } from "react";
// import { Bar, Line } from "react-chartjs-2";
// import io from "socket.io-client";
// import {
//   Chart as ChartJS,
//   Title,
//   Tooltip,
//   Legend,
//   BarElement,
//   CategoryScale,
//   LinearScale,
// } from "chart.js";
// ChartJS.register(
//   Title,
//   Tooltip,
//   Legend,
//   BarElement,
//   CategoryScale,
//   LinearScale
// );

// const socket = io("http://localhost:3002"); // Connect to the backend server

// const ChartComponent = ({ chartType, customizationOptions }) => {
//   const [chartData, setChartData] = useState({
//     labels: [],
//     datasets: [
//       {
//         label: "Data",
//         data: [],
//         backgroundColor: "rgba(75,192,192,0.2)",
//         borderColor: "rgba(75,192,192,1)",
//         borderWidth: 1,
//       },
//     ],
//   });

//   const createUpdatedData = (newData) => {
//     let receivedData = newData
//       .filter((data) => data?.end_year !== "" && data?.intensity !== "")
//       .sort((a, b) => a.end_year - b.end_year);
//     if (receivedData?.length > 0) {
//       let updatedData = { ...chartData };
//       updatedData.labels = receivedData.map((row) => row?.end_year);
//       updatedData.datasets[0].data = receivedData.map((row) => row?.intensity);
//       setChartData(updatedData);
//     }
//   };

//   useEffect(() => {
//     // Listen for initial data from the server
//     socket.on("updateChartData", (initialData) => {
//       createUpdatedData(initialData);
//     });

//     // Listen for updates from the server
//     socket.on("updateChartData", (newData) => {
//       createUpdatedData(newData);
//     });

//     // Clean up event listeners when the component unmounts
//     return () => {
//       socket.off("updateChartData");
//     };
//   }, ); // Add chartData as a dependency to useEffect

//   const options = {
//     scales: {
//       x: { ...customizationOptions.xAxis },
//       y: { ...customizationOptions.yAxis },
//     },
//   };

//   const Chart = chartType === "bar" ? Bar : Line;

//   if (chartData?.labels?.length === 0) return <></>;

//   return <Chart data={chartData} options={options} />;
// };

// export default ChartComponent;
// import React, { useEffect, useState } from "react";
// import { Bar, Line } from "react-chartjs-2";
// import io from "socket.io-client";
// import {
//   Chart as ChartJS,
//   Title,
//   Tooltip,
//   Legend,
//   BarElement,
//   CategoryScale,
//   LinearScale,
// } from "chart.js";

// // Register Chart.js components
// ChartJS.register(
//   Title,
//   Tooltip,
//   Legend,
//   BarElement,
//   CategoryScale,
//   LinearScale
// );

// const socket = io("http://localhost:3003"); // Connect to the backend server

// const ChartComponent = ({ chartType, customizationOptions }) => {
//   const [chartData, setChartData] = useState({
//     labels: [],
//     datasets: [
//       {
//         label: "Data",
//         data: [],
//         backgroundColor: "rgba(75,192,192,0.2)",
//         borderColor: "rgba(75,192,192,1)",
//         borderWidth: 1,
//       },
//     ],
//   });

//   // Function to update chart data
//   const updateChartData = (newData) => {
//     const filteredData = newData
//       .filter((data) => data?.end_year !== "" && data?.intensity !== "")
//       .sort((a, b) => a.end_year - b.end_year);

//     if (filteredData.length > 0) {
//       setChartData((prevData) => ({
//         ...prevData,
//         labels: filteredData.map((row) => row?.end_year),
//         datasets: [
//           {
//             ...prevData.datasets[0],
//             data: filteredData.map((row) => row?.intensity),
//           },
//         ],
//       }));
//     }
//   };

//   useEffect(() => {
//     // Listen for initial data and updates from the server
//     const handleChartDataUpdate = (newData) => {
//       updateChartData(newData);
//     };

//     socket.on("updateChartData", handleChartDataUpdate);

//     // Clean up event listeners when the component unmounts
//     return () => {
//       socket.off("updateChartData", handleChartDataUpdate);
//     };
//   }, []); // Empty dependency array ensures this effect runs once on mount

//   const options = {
//     scales: {
//       x: { ...customizationOptions.xAxis },
//       y: { ...customizationOptions.yAxis },
//     },
//   };

//   const Chart = chartType === "bar" ? Bar : Line;

//   if (chartData?.labels?.length === 0) return <></>;

//   return <Chart data={chartData} options={options} />;
// };

// export default ChartComponent;

    
import React, { useState, useEffect } from 'react';

const App = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    // Fetch data when the component mounts
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/data');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.json();
      setData(result);
    } catch (error) {
      console.error('Error fetching data:', error.message);
    }
  };

  return (
    <div>
      <h1>Data Visualization Dashboard</h1>
      {data ? (
        <div>
          <p>{data}</p>
          {/* Render your charts or visualizations here */}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default App;

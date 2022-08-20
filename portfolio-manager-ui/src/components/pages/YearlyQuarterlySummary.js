import axios from "axios";
import { useState, useEffect } from 'react';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
  } from 'chart.js';
  import { Line } from 'react-chartjs-2';

function YearlyQuarterlySummary(){
    
    const [data, setData] = useState();
    const [chartData, setChartData] = useState();
    const [hasChartData, setHasChartData] = useState(false); 
    const [chartContent, setChartContent] = useState(); //here

    useEffect(() => {
        setChartContentIfThereIsData();
        getYearlyQuarterlyData();
    }, [hasChartData]);


    function setChartContentIfThereIsData(){
        if(hasChartData){
           setChartContent( <Line options={options} data={chartData}/>)
        }else{
            setChartContent(<div>Loading...</div>)
        }
    }


    function populateChartData(list) {
        let labels = [];
        let totalDividends = [];
        let averageDividends = [];
        let maxDividends = [];
        list.reverse().map(item => {
            labels.push(item.dividendYear+"-"+item.quarter);
            totalDividends.push(item.amount);
            averageDividends.push(item.avgAmount);
            maxDividends.push(item.maxAmount);
        });

        let tempData = {
            labels,
            datasets: [
                {
                    label: 'Total Dividend',
                    data: totalDividends,
                    backgroundColor: 'rgba(255, 99, 132, 0.5)',
                }
                ,
                {
                    label: 'Average Dividend',
                    data: averageDividends,
                    backgroundColor: 'rgba(53, 162, 235, 0.5)',
                },
                {
                    label: 'Max Dividend',
                    data: maxDividends,
                    backgroundColor: 'rgba(100, 100, 235, 0.5)',
                }
            ],
        };
        setChartData(tempData);
        setHasChartData(true);
    }


    ChartJS.register(
        CategoryScale,
        LinearScale,
        PointElement,
        LineElement,
        Title,
        Tooltip,
        Legend
      );
      
       const options = {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: 'Line Chart',
          },
        },
      };
      
    //   const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
      
    //   const data = {
    //     labels,
    //     datasets: [
    //       {
    //         label: 'Dataset 1',
    //         data: labels.map(() => faker.datatype.number({ min: -1000, max: 1000 })),
    //         borderColor: 'rgb(255, 99, 132)',
    //         backgroundColor: 'rgba(255, 99, 132, 0.5)',
    //       },
    //       {
    //         label: 'Dataset 2',
    //         data: labels.map(() => faker.datatype.number({ min: -1000, max: 1000 })),
    //         borderColor: 'rgb(53, 162, 235)',
    //         backgroundColor: 'rgba(53, 162, 235, 0.5)',
    //       },
    //     ],
    //   };
      


    function getYearlyQuarterlyData() {
        // Simple GET request using axios
        axios.get('http://localhost:8081/dividendSummaryByYearAndQuarter')
            .then(response => {
                setData(response.data.list.map((item) => (
                    <tr key={item.dividendYear+'-'+item.quarter}>
                        <td>{item.dividendYear}</td>
                        <td>{item.quarter}</td>
                        <td>{item.amount}</td>
                        <td>{item.maxAmount}</td>
                        <td>{item.avgAmount}</td>
                    </tr>
                ))
                );
                populateChartData(response.data.list);
            });
    }


  //   function getYearlyData() {
  //     // Simple GET request using axios
  //     axios.get('http://localhost:8081/dividendSummaryByYear')
  //         .then(response => {
  //             setYearlyData(response.data);
  //         });
  // }

    return (
        <div className="container rounded">
            <div ><b>Dividends  &gt; Yearly-Quarterly-Summary</b></div>
            <br />
            <div className="container">
                <div className="row">
                    <div className="col-lg-6">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">Year</th>
                                    <th scope="col">Quarter</th>
                                    <th scope="col">Total. Div.</th>
                                    <th scope="col">Max Div.</th>
                                    <th scope="col">Avg Div.</th>
                                </tr>
                            </thead>
                            <tbody>
                                {

                              //    yearlyData.list.map((item) => (
                              //     <tr>
                              //         <td>{item.dividendYear}</td>
                              //         <td>{item.amount}</td>
                              //         <td>{item.maxAmount}</td>
                              //         <td>{item.avgAmount}</td>
                              //     </tr>
                              // ))



                                  data
                                }
                            </tbody>
                        </table>

                    </div>
                    <div className="col-lg-6">
                    {chartContent}
                    </div>
                </div>
            </div>
        </div>
    )
}


export default YearlyQuarterlySummary;
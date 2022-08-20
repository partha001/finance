import "antd/dist/antd.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from "axios";
import React, { useState, useEffect } from 'react';
import './YearlySummary.css';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';


function YearlySummary() {


    const [yearlyData, setYearlyData] = useState();
    const [chartData, setChartData] = useState();
    const [hasChartData, setHasChartData] = useState(false); 
    const [chartContent, setChartContent] = useState(); //here


    useEffect(() => {
        getYearlyData();
        setChartContentIfThereIsData();

    }, [hasChartData,]);

    function setChartContentIfThereIsData(){
        if(hasChartData){
           setChartContent( <Bar options={options} data={chartData} />)
        }else{
            setChartContent(<div>Loading...</div>)
        }
    }


    ChartJS.register(
        CategoryScale,
        LinearScale,
        BarElement,
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
                text: 'Chart.js Bar Chart',
            },
        },
    };


    function getYearlyData() {
        axios.get('http://localhost:8081/dividendSummaryByYear')
            .then(response => {
                setYearlyData(response.data.list.map((item) => (
                    <tr key={item.dividendYear}>
                        <td>{item.dividendYear}</td>
                        <td>{item.amount}</td>
                        <td>{item.maxAmount}</td>
                        <td>{item.avgAmount}</td>
                    </tr>
                )));
                populateChartData(response.data.list);
            });
    }


    function populateChartData(list) {
        let labels = [];
        let totalDividends = [];
        let averageDividends = [];
        let maxDividends = [];
        list.reverse().map(item => {
            labels.push(item.dividendYear);
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


    //   function getYearlyData() {
    //     // Simple GET request using axios
    //     axios.get('http://localhost:8081/dividendSummaryByYear')
    //         .then(response => {
    //             setYearlyData(response.data);
    //         });
    // }

    return (
        <div className="container rounded">
            <div ><b>Dividends  &gt; YearlySummary</b></div>
            <br />
            <div className="container">
                <div className="row">
                    <div className="col-lg-6">


                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">Year</th>
                                    <th scope="col">Total Div.</th>
                                    <th scope="col">Max Div.</th>
                                    <th scope="col">Avg Div.</th>
                                </tr>
                            </thead>
                            <tbody>
                                {yearlyData}
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

export default YearlySummary;
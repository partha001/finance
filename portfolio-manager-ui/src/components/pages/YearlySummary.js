import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from "axios";
import { useState, useEffect } from 'react';
import './YearlySummary.css';

function YearlySummary() {


    const [yearlyData, setYearlyData] = useState();

    useEffect(() => {
        getYearlyData();
    }, []);

    useEffect(() => {
    }, [yearlyData]);


    function getYearlyData() {
        // Simple GET request using axios
        axios.get('http://localhost:8081/dividendSummaryByYear')
            .then(response => {
                setYearlyData(response.data.list.map((item) => (
                    <tr>
                        <td>{item.dividendYear}</td>
                        <td>{item.amount}</td>
                        <td>{item.maxAmount}</td>
                        <td>{item.avgAmount}</td>
                    </tr>
                ))
                )
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
            <div ><b>Dividends  &gt; YearlySummary</b></div>
            <br />
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Year</th>
                                    <th scope="col">Dividend</th>
                                    <th scope="col">Max</th>
                                    <th scope="col">avg</th>
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



                                  yearlyData
                                }
                            </tbody>
                        </table>

                    </div>
                    <div class="col-lg-6">
                        section2
                    </div>
                </div>
            </div>
        </div>
    )
}

export default YearlySummary;
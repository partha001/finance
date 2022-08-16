import axios from "axios";
import { useState, useEffect } from 'react';

function QuarterlySummary(){


    const [quarterlyData, setQuarterlyData] = useState();

    useEffect(() => {
        getQuarterlyData();
    }, []);

    useEffect(() => {
    }, [quarterlyData]);


    function getQuarterlyData() {
        // Simple GET request using axios
        axios.get('http://localhost:8081/dividendSummaryByQuarter')
            .then(response => {
                setQuarterlyData(response.data.list.map((item) => (
                    <tr>
                        <td>{item.quarter}</td>
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
            <div ><b>Dividends  &gt; QuarterlySummary</b></div>
            <br />
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Quarter</th>
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



                              quarterlyData
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

export default QuarterlySummary;
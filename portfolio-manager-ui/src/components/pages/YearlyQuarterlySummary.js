import axios from "axios";
import { useState, useEffect } from 'react';

function YearlyQuarterlySummary(){
    
    const [data, setData] = useState();

    useEffect(() => {
        getYearlyQuarterlyData();
    }, []);

    useEffect(() => {
    }, [data]);


    function getYearlyQuarterlyData() {
        // Simple GET request using axios
        axios.get('http://localhost:8081/dividendSummaryByYearAndQuarter')
            .then(response => {
                setData(response.data.list.map((item) => (
                    <tr>
                        <td>{item.dividendYear}</td>
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
            <div ><b>Dividends  &gt; YearlyQuarterlySummary</b></div>
            <br />
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Year</th>
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



                                  data
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


export default YearlyQuarterlySummary;
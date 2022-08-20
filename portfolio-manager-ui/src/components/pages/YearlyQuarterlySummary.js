import axios from "axios";
import { useState, useEffect } from 'react';

function YearlyQuarterlySummary(){
    
    const [data, setData] = useState();

    useEffect(() => {
        getYearlyQuarterlyData();
    }, []);


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
                        section2
                    </div>
                </div>
            </div>
        </div>
    )
}


export default YearlyQuarterlySummary;
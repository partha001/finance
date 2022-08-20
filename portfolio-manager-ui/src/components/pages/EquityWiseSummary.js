import axios from "axios";
import { useState, useEffect } from 'react';


function EquityWiseSummary(){
   
    const [data, setData] = useState();

    useEffect(() => {
        getYearlyQuarterlyData();
    }, []);

    useEffect(() => {
    }, [data]);


    function getYearlyQuarterlyData() {
        // Simple GET request using axios
        axios.get('http://localhost:8081/dividendSummaryByEquity') 
            .then(response => {
                setData(response.data.list.map((item) => (
                    <tr key={item.symbol+'-'+item.name}>
                        <td>{item.symbol}</td>
                        <td>{item.name}</td>
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
            <div ><b>Dividends  &gt; EquitywiseSummary</b></div>
            <br />
            <div className="container">
                <div className="row">
                    <div className="col-lg-6">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">Symbol</th>
                                    <th scope="col">Name</th>
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
                    <div className="col-lg-6">
                        section2
                    </div>
                </div>
            </div>
        </div>
    )
}

export default EquityWiseSummary;

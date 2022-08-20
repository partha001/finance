import { Space, Table, Tag } from 'antd';
import "antd/dist/antd.css";
import { Icon, Switch, Radio, Form, Divider } from "antd";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from "axios";
import React, { useState, useEffect } from 'react';
import './YearlySummary.css';


function YearlySummary() {


    const [yearlyData, setYearlyData] = useState();
    const [loadinng, setLoading] = useState(false);


    useEffect(() => {
        getYearlyData();
    }, []);


    const columns = [
        {
            title: "Year",
            dataIndex: "dividendYear",
            key: "dividendYear"
        },
        {
            title: "Dividend Amount",
            dataIndex: "amount",
            key: "amount"
        }
    ]


    function getYearlyData() {
        // Simple GET request using axios
        axios.get('http://localhost:8081/dividendSummaryByYear')
            .then(response => {
                setYearlyData(response.data.list.map((item) => (
                    <tr key={item.dividendYear}>
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
                    <div className="col-lg-6">
                        section2
                    </div>
                </div>
            </div>
        </div>
    )
}

export default YearlySummary;
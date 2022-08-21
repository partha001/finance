import axios from "axios";
import { useState, useEffect } from 'react';
import { Space, Table, Tag , Icon, Switch, Radio, Form, Divider} from 'antd';


function EquityWiseSummary(){
   
    const [data, setData] = useState();
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        getYearlyQuarterlyData();
    }, []);

    useEffect(() => {
    }, [data]);


    function getYearlyQuarterlyData() {
        setLoading(true);
        axios.get('http://localhost:8081/dividendSummaryByEquity') 
            .then(response => {
                setLoading(false);
                populateDate(response.data.list);
                // setData(response.data.list.map((item) => (
                //     <tr key={item.symbol+'-'+item.name}>
                //         <td>{item.symbol}</td>
                //         <td>{item.name}</td>
                //         <td>{item.amount}</td>
                //         <td>{item.maxAmount}</td>
                //         <td>{item.avgAmount}</td>
                //     </tr>
                // ))
                // )
            });
    }


    function populateDate(list){
        let arr = [];
        list.map( item =>{
            arr.push(
                {
                    'key': item.symbol,
                    'symbol': item.symbol,
                    'name': item.name,
                    'amount': item.amount,
                    'maxAmount': item.maxAmount,
                    'avgAmount': item.avgAmount,
                    'frequency': item.frequency
                }
            )
        });
       setData(arr);
    }



    const columns = [
        // {
        //     title: "Symbol",
        //     dataIndex: "symbol",
        //     key: "symbol"
        //     // filters: years,
        //     // onFilter: (value, record) => { 
        //     //     // console.log(value);
        //     //     // console.log(record.dividendYear);
        //     //     //return record.dividendYear.indexOf(value) === 0;
        //     //     return record['dividendYear'].toString().toLowerCase().includes(value.toLowerCase())
        //     //}

        // },
        {
            title: "Name",
            dataIndex: "name"
            // filters: quartersFilter,
            // key: "quarter",
            // onFilter: (value, record) => { 
            //     // console.log(value);
            //     // console.log(record.dividendYear);
            //     //return record.dividendYear.indexOf(value) === 0;
            //     return record['quarter'].toString().toLowerCase().includes(value.toLowerCase())
            // }
        },
        // {
        //     title: "Symbol",
        //     dataIndex: "symbol",
        //     key: "quarter"
        // },
        {
            title: "Div. Amt.",
            dataIndex: "amount",
            key: "amount",
            sorter: (a, b) => {
                return a.amount - b.amount          
            }
        }
        ,{
            title: "Max. Amt.",
            dataIndex: "maxAmount",
            key: "maxAmount",
            sorter: (a, b) => {
                return a.maxAmount - b.maxAmount          
            }
        }
        ,{
            title: "Avg. Amt.",
            dataIndex: "avgAmount",
            key: "avgAmount",
            sorter: (a, b) => {
                return a.avgAmount - b.avgAmount          
            }
        },
        {
            title: "Count",
            dataIndex: "frequency",
            key: "frequency",
            sorter: (a, b) => {
                return a.avgAmount - b.avgAmount          
            }
        }
    ]



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
                    <Table loading={loading} dataSource={data} columns={columns}></Table>
                        {/* <table className="table">
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
                        </table> */}

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

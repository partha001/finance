import axios from "axios";
import { useState, useEffect } from 'react';
import { Space, Table, Tag , Icon, Switch, Radio, Form, Divider} from 'antd';


function DividendDetails(){
   
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);
    const [years, setYears] = useState([]);
    const [quartersFilter, setQuartersFilter] = useState([]);




    useEffect(() => {
        console.log("hello");
        populateQuartersFilter();
        getDividendDetails();           
    }, []);


    function populateQuartersFilter(){
        let arr = [{
            
                'text':'1',
                'value':'1'
            }
            ,{
                'text':'2',
                'value':'2'
            }
            ,{
                'text':'3',
                'value':'3'
            }
            ,{
                'text':'4',
                'value':'4'
            }]
            setQuartersFilter(arr);
    }

    function getDividendDetails() {
        setLoading(true);
        axios.get('http://localhost:8081/dividendDetails')
            .then(response => {
                setLoading(false);
                populateDate(response.data.list);
                populateYears(response.data.list)
            });
    }

    function populateDate(list){
        let arr = [];
        list.map( item =>{
            arr.push(
                {
                    'key': item.id,
                    'dividendYear' : item.dividendYear,
                    'quarter': item.quarter,
                    'name': item.name,
                    'amount': item.amount
                }
            )
        });
       setData(arr);
    }


    function populateYears(list){
        //console.log('inputList',list);
        let temp = {}, tempArr = [];


       list.map(item =>{
        //console.log(item.name);
        //temp.push(item.dividendYear);
        temp[item.dividendYear] = item.dividendYear;
       })
       console.log(temp);

       Object.keys(temp).forEach(function(key) {
        console.log(key, temp[key]);
        tempArr.push({
            'text': key,
            'value': key
        })
    })

       setYears(tempArr);


    //setYears(new Set(years));
    }


    const columns = [
        {
            title: "Year",
            dataIndex: "dividendYear",
            key: "dividendYear",
            filters: years,
            onFilter: (value, record) => { 
                // console.log(value);
                // console.log(record.dividendYear);
                //return record.dividendYear.indexOf(value) === 0;
                return record['dividendYear'].toString().toLowerCase().includes(value.toLowerCase())
            }

        },
        {
            title: "Quarter",
            dataIndex: "quarter",
            filters: quartersFilter,
            key: "quarter",
            onFilter: (value, record) => { 
                // console.log(value);
                // console.log(record.dividendYear);
                //return record.dividendYear.indexOf(value) === 0;
                return record['quarter'].toString().toLowerCase().includes(value.toLowerCase())
            }
        },
        // {
        //     title: "Symbol",
        //     dataIndex: "symbol",
        //     key: "quarter"
        // },
        {
            title: "Name",
            dataIndex: "name",
            key: "name"
        },
        {
            title: "Div. Amt.",
            dataIndex: "amount",
            key: "amount",
            sorter: (a, b) => {
                return a.amount - b.amount          
            }
        }
    ]


    // function getDividendDetails() {
    //     // Simple GET request using axios
    //     axios.get('http://localhost:8081/dividendDetails')
    //         .then(response => {
    //             setData(response.data.list.map((item) => (
    //                 <tr>
    //                     <td>{item.dividendYear}</td>
    //                     <td>{item.quarter}</td>
    //                     <td>{item.symbol}</td>
    //                     <td>{item.name}</td>
    //                     <td>{item.amount}</td>
    //                 </tr>
    //             ))
    //             )
    //         });
    // }



    return (
       
        <div className="container rounded">
             {console.log("inside return block",years)}
            <div ><b>Dividends  &gt; Dividend details</b></div>
            <br />
            <div className="container">
                <div className="row">
                    <div className="col-lg-6">

                        <Table loading={loading} dataSource={data} columns={columns}></Table>

                    </div>
                    <div className="col-lg-6">
                        section2
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DividendDetails;

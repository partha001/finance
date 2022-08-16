import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import React, { useState } from 'react';
import '../components/Menu1';
import Menu1 from './Menu1';

function Navigation(props) {

    const [activeMenu,setActiveMenu] = useState(props.activeMenu);

    

    const menuClickHandler = (event)=>{
        console.log(event.target.id);
        setActiveMenu(event.target.id);   
    }

    let myvar1 = (activeMenu==='' && <p>menu1</p>);
    myvar1 = (activeMenu==='menu2' && <p>menu2</p>);
    myvar1 = (activeMenu==='menu2' && <p>yearlySummary</p>);

    // function getDividend() {
    //     fetch("http://localhost:8081/dividendSummaryByYear")
    //         .then((response) => {
    //             return response.json();
    //         })
    //         .then((data) => {
    //             console.log(data);
    //         })
    // }

    return (<div>
        <div className="container rounded">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">

                    {/* <a class="navbar-brand" href="#">Home</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button> */}

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#" id="menu1" onClick={menuClickHandler}>Menu1</a>
                            </li>
                            <li className="nav-item" >
                                <a className="nav-link" href="#"  id="menu2" onClick={menuClickHandler}>Menu2</a>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Dividends
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a className="dropdown-item" href="#"  id="menuYearlySummar" onClick={menuClickHandler}>Yearly Summary</a></li>
                                    <li><a className="dropdown-item" href="#"  id="menuQuarterlySummary" onClick={menuClickHandler}>Quarterly Summary </a></li>
                                    <li><a className="dropdown-item" href="#"  id="menuYearlyQuarterlySummary" onClick={menuClickHandler}>Year and Quarterly Summary</a></li>
                                    <li><a className="dropdown-item" href="#"  id="menuEquitySummary" onClick={menuClickHandler}>Equitywise Summary</a></li>
                                </ul>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link disabled" href="#" tabIndex="-1" aria-disabled="true">Disabled</a>
                            </li>
                        </ul>
                        <form className="d-flex">
                            <button className="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>


        <div className="container rounded">
         {myvar1}
        </div>
    </div>)
}

export default Navigation;
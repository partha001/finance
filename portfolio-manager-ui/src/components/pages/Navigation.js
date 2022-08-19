import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import React, { useEffect, useState } from 'react';
import YearlySummary from './YearlySummary';
import QuarterlySummary from './QuarterlySummary';
import YearlyQuarterlySummary from './YearlyQuarterlySummary';
import EquityWiseSummary from './EquityWiseSummary';
import DividendDetails from './DividendDetails';
import './Navigation.css';

function Navigation(props) {

    const [activeMenu, setActiveMenu] = useState(props.activeMenu);
    const [content, setContent] = useState(props.activeMenu);


    useEffect(() => {
        setContentFunc(activeMenu);
    }, [activeMenu])

    const menuClickHandler = (event) => {
        setActiveMenu(event.target.id);
    }


    const setContentFunc = () => {
        if (activeMenu === 'menu1') {
            setContent('menu1');
        }
        else if (activeMenu === 'menu2') {
            setContent('menu2')
        } else if (activeMenu === 'menuYearlySummar') {
            setContent(<YearlySummary />)
        } else if (activeMenu === 'menuQuarterlySummary') {
            setContent(<QuarterlySummary />)
        }
        else if (activeMenu === 'menuYearlyQuarterlySummary') {
            setContent(<YearlyQuarterlySummary />)
        }
        else if (activeMenu === 'menuEquitySummary') {
            setContent(<EquityWiseSummary />)
        }
        else if (activeMenu === 'menuDividendDetails') {
            setContent(<DividendDetails />)
        }


        
    }



    return (<div>
        <div className="container rounded" style={{ margin: "10px" }}>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0" onClick={(e) => menuClickHandler(e)} >
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#" id="menu1">Menu1</a>
                            </li>
                            <li className="nav-item" >
                                <a className="nav-link" href="#" id="menu2">Menu2</a>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Dividends
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown" >
                                    <li><a className="dropdown-item" href="#" id="menuYearlySummar" >Yearly Summary</a></li>
                                    <li><a className="dropdown-item" href="#" id="menuQuarterlySummary" >Quarterly Summary </a></li>
                                    <li><a className="dropdown-item" href="#" id="menuYearlyQuarterlySummary" >Yearly and Quarterly Summary</a></li>
                                    <li><a className="dropdown-item" href="#" id="menuEquitySummary" >Equitywise Summary</a></li>
                                    <li><a className="dropdown-item" href="#" id="menuDividendDetails" >Dividend Details</a></li>
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
            <div className='tab-body'>
                <p>{content}</p>
            </div>
        </div>
    </div>)
}

export default Navigation;
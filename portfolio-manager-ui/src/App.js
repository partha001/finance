import logo from './logo.svg';
import './App.css';
import "./components/pages/Navigation"
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Routes, Route } from "react-router-dom";
import Welcome from './components/pages/Welcome';
import Dashboard from './components/pages/Dashboard';

function App() {

  const activeMenu = "menu1";

  return (

    <div>
      <div className="container jumbotron rounded header-style">
        <h1>Portfolio Manager</h1>
      </div>

      <div className='container'>
        <Routes>
          <Route path='' element={<Welcome/>}> </Route>
          <Route path='/dashboard' element={<Dashboard  activeMenu={activeMenu}/>}></Route>
          <Route path='*' element={<div><h4>404 no such page found</h4></div>}></Route>
        </Routes>
      </div>

      <div className="container footerStyle rounded footer-style">
        <div className="footerStyleContent">
          <p className="text-white credit">Developed by:
            Partha Biswas
            <a href="#">Partha Biswas</a>
            . &copy; Copyright 2018</p>
        </div>
      </div>
    </div>
  );
}

export default App;

import logo from './logo.svg';
import './App.css';
import "./components/Navigation"
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import Navigation from './components/Navigation';

function App() {
  return (
    <div>
      <div className="container jumbotron bg-secondary rounded">      
          <h1>Portfolio Manager</h1>
      </div>

      <Navigation activeMenu="menu1"></Navigation>


      <div className="container footerStyle bg-secondary rounded">
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

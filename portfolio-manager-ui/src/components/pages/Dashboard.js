import Navigation from "./Navigation";

function Dashboard(props){
    return (
        <div>
            <Navigation activeMenu={props.activeMenu}/>
        </div>

        
    )
}


export default Dashboard;
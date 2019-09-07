import React from 'react';
import logo from './logo.svg';
import './App.css';

class App extends React.Component {


    state = {
        userData: []
    };

    componentDidMount() {
        const error_type =  new URLSearchParams(window.location.search).get("error_type");
        if (error_type) {

            const error_description = new URLSearchParams(window.location.search).get("error_description");


            return;
        } else {
            const access_token = new URLSearchParams(window.location.search).get("token");


            if(access_token){
                const refresh_token = new URLSearchParams(window.location.search).get("refresh_token");
                const jSessionId = new URLSearchParams(window.location.search).get("session_id");
                document.cookie = `JSESSIONID=${jSessionId}`;

                let self = this;

                fetch('/users/me', {
                    method: 'get',
                    headers: new Headers({
                        'Authorization': `Bearer ${access_token}`
                    })
                })
                    .then((response)=>{

                        response.json().then(function(data) {

                            document.getElementById("json").innerHTML = JSON.stringify(data, undefined, 2);
                            window.history.replaceState({}, document.title, "/");
                        });
                    })

            } else {
                window.location.href = 'http://localhost:8080/login/active-directory';
            }
        }

    }

    render() {

       return (

           <div>
           {/*<div className="App">
               <header className="App-header">
                   <a
                       className="App-link"
                       //href="http://localhost:8080/login/active-directory"
                       rel="noopener noreferrer"
                   >
                       Welcome
                   </a>

               </header>

           </div>*/}

               <pre id="json" style={{color: 'black', width: '800px'}}>{JSON.stringify(this.state.userData)}</pre>
           </div>

       )

   }
}

export default App;

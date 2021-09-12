import React, { Component } from 'react'
import './App.css'
import { useState, useEffect } from 'react'
import { render } from 'react-dom';
import { useHistory } from 'react-router-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom'
import LoginComponent from './login/LoginComponent.js'
import MainPage from './mainpage/MainPage.js'
import Footer from './Footer'


function App() {

const [message, setMessage] = useState("")
const [successMessage, setSuccessMessage] = useState("")
const[loginUser, setLoginUser] = useState("")
const [demands, setDemands] = useState([]);

const login = async (email, password, history) => {

    const res = await fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify({email, password}),
    })

    const data = await res.json();

    if (res.status !== 200) {
        setMessage(data.errorMessage)
    } else {
        setLoginUser(email);
        localStorage.setItem("loginUser", JSON.stringify(email));
        setDemands([]);
        history.push('/main-page');
    }

}

const addDemand = async (demandRequest) => {

console.log(demandRequest);

    const res = await fetch('http://localhost:8080/demand', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(demandRequest),
    })

    const data = await res.json();

    if (res.status !== 200) {
        console.log(data.errorMessage)
    } else {
        setDemands(data);
    }
}

const deleteDemand = async (demandId) => {
     const res = await fetch(`http://localhost:8080/demand/${demandId}`, {
          method: 'DELETE',
        })
}

const getDemands = async(loginUser) => {
    const res = await fetch(`http://localhost:8080/demands-by/user/${loginUser}`)
    const data = await res.json()

    return data;
}

const updateDemand = async(updateDemandRequest) => {

    const res = await fetch(`http://localhost:8080/demand/${updateDemandRequest.demandId}`, {
      method: 'PUT',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(updateDemandRequest),
    })

    const data = await res.json();

    if (res.status !== 200) {
        console.log(data.errorMessage)
    } else {
        setDemands(data);
    }


}


const register = async (email, password, repeatedPassword) => {

   const registerData = {
       "email" : email,
       "password" : password
   }
   const res = await fetch('http://localhost:8080/register', {
     method: 'POST',
     headers: {
       'Content-type': 'application/json',
     },
     body: JSON.stringify(registerData),
   })

   const data = await res.json();
   res.status !== 200 ?
       setMessage(data.errorMessage) : setSuccessMessage("You have successfully created an account on Renting Buddy");

}

  return (

        <>
        <Router>
        <Route path = "/" exact render={(props) => (
            <LoginComponent login={login} register={register} message={message} setMessage={setMessage} successMessage={successMessage} setSuccessMessage={setSuccessMessage}/>
          )
        } />
        <Route path = "/main-page" exact render={(props) => (
            <MainPage loginUser={loginUser} setLoginUser={setLoginUser} addDemand={addDemand} getDemands={getDemands} deleteDemand={deleteDemand}
            editDemand={updateDemand} demands={demands} setDemands={setDemands} />
          )
        } />
        </Router>
        <Footer />
        </>
  );
}

export default App;

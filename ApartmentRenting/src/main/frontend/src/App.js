import React, { Component } from 'react'
import './App.css'
import { useState, useEffect } from 'react'
import { render } from 'react-dom';
import { useHistory } from 'react-router-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom'
import LoginComponent from './login/LoginComponent.js'
import MainPage from './mainpage/MainPage.js'


function App() {

const [message, setMessage] = useState("")
const [successMessage, setSuccessMessage] = useState("")

const login = async (email, password, history) => {

    const res = await fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify({email, password}),
    })

    const data = await res.json();
    res.status !== 200 ? setMessage(data.errorMessage) : history.push('/main-page');

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

        <Router>
        <Route path = "/" exact render={(props) => (
            <LoginComponent login={login} register={register} message={message} setMessage={setMessage} successMessage={successMessage} setSuccessMessage={setSuccessMessage}/>
          )
        } />
        <Route path = "/main-page" exact render={(props) => (
            <MainPage />
          )
        } />
        </Router>
  );
}

export default App;

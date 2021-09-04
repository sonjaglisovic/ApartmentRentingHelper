import React, { Component } from 'react'
import './App.css'
import loginimage from './assets/login2.jpg'
import { useState, useEffect } from 'react'

function App() {

const [signIn, setSignIn] = useState(true)
const [email, setEmail] = useState("")
const [password, setPassword] = useState("")
const [repeatedPassword, setRepeatedPassword] = useState("")
const [message, setMessage] = useState("")
const [reTypePasswordMissing, setReTypePasswordMissing] = useState("")
const [passwordMissing, setPasswordMissing] = useState("")
const [emailMissing, setEmailMissing] = useState("")

const toggleSgnIn = () => {
    setSignIn(signIn ? false : true)
}

const loginOrSignUp = () => {
console.log("PROSAO");
}

  return (

   <div className="container h-100 w-100">
    <div className = "row">
    {signIn === true ?
        <div className="col-md-12">
            <h1 className="mt-2 mainLetterColor">Welcome back</h1>
            <p className="mt-2 mainLetterColor">If you already have an account, just sign in. We've missed you!</p>
        </div>
        :
         <div className="col-md-12">
            <h1 className="mt-2 mainLetterColor">Welcome to Renting Buddy</h1>
            <p className="mt-2 mainLetterColor">Create account to find perfect apartment for you!</p>
        </div>
      }

    </div>
    <div className = "row">
        <div className = "col-md-5 mt-3">
            <img src={loginimage}></img>
        </div>
        <div className="col-md-2"></div>
        <div className = "col-md-5 formPosition bg-gradient-light mt-5">
        <form onSubmit = {loginOrSignUp}>
            <label className="mt-2">
             <span>Email</span>
             {emailMissing === "" ?
             <input type="email"  required="required" onChange={(e) => setEmail(e.target.value)}/>
             : <><input className="errorBorder" type="email"  required="required" onChange={(e) => setEmail(e.target.value)}/>
             <p className = "requiredMessage"> Email is required! </p>
             </>}
            </label>
            <label>
              <span>Password</span>
              {passwordMissing === "" ?
              <input type="password"  required="required" onChange={(e) => setPassword(e.target.value)} /> :
              <>
              <input type="password" className="errorBorder" required="required" onChange={(e) => setPassword(e.target.value)} />
               <p className = "requiredMessage"> Password is required! </p>
               </>}
            </label>
            {signIn === true ?
            <>
            <button type="submit" className="button mt-5">Sign In</button>
            <div className="col-md-4">
                <p className="mt-4">New here?</p>
            </div>
            <button type="button" className="button" onClick={toggleSgnIn}>Create account</button>
            </> : <>
            <label>
              <span>Re-type password</span>
              {reTypePasswordMissing === "" ?
              <input type="password" onChange={(e) => setRepeatedPassword(e.target.value)} /> : <>
              <input type="password" className="errorBorder" onChange={(e) => setRepeatedPassword(e.target.value)} />
               <p className = "requiredMessage">  Re-type password field is required is required! </p>
              </>}
            </label>
             <button type="submit" className="button mt-5">Sign Up</button>
             <button type="button" className="button mt-5" onClick={toggleSgnIn}>Back to login page</button>
            </>}
            </form>
            {
             message !== "" && <p className = "errorMessage mt-4"> {message}! </p>
            }
        </div>
    </div>
   </div>
  );
}

export default App;

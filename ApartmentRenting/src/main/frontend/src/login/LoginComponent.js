import React, { Component } from 'react'
import './LoginComponent.css'
import { useState, useEffect } from 'react'
import Slideshow from '../Slideshow.js'
import ShowPassword from './ShowPassword.js'
import { useHistory } from 'react-router-dom'

function LoginComponent({login, register, message, setMessage, successMessage, setSuccessMessage}) {

const [signIn, setSignIn] = useState(true)
const [email, setEmail] = useState("")
const [password, setPassword] = useState("")
const [repeatedPassword, setRepeatedPassword] = useState("")
const [reTypePasswordMissing, setReTypePasswordMissing] = useState("")
const [passwordMissing, setPasswordMissing] = useState("")
const [emailMissing, setEmailMissing] = useState("")
const history = useHistory()
const [showPasswordChecked, setShowPasswordChecked] = useState(false)

const toggleSgnIn = () => {
    setEmail("");
    setPassword("");
    setRepeatedPassword("");
    setReTypePasswordMissing("");
    setPasswordMissing("");
    setEmailMissing("");
    setMessage("");
    setShowPasswordChecked(false);
    setSignIn(signIn ? false : true)
}

const signInOrUp = (event) => {

    event.preventDefault();
    setEmailMissing("");
    setPasswordMissing("");
    setReTypePasswordMissing("");
    setMessage("");
    setSuccessMessage("");

    if(email === "") {
        setEmailMissing("required");
        return;
    } else if(password === "") {
        setPasswordMissing("required");
        return;
    }

    if(signIn) {
        login(email, password, history);
    } else {

        if(repeatedPassword === "") {
            setReTypePasswordMissing("required");
            return;
        }
        if(password !== repeatedPassword) {
            setMessage("Re-typed password and password didn't match");
            return;
        }
        register(email, password, repeatedPassword);
    }
    if(message === "") {

        setEmail("");
        setPassword("");
        setRepeatedPassword("");
    }
}

  return (

   <div className="container h-100 w-100">
    <div className = "row">
    {signIn === true ?
        <div className="col-md-12">
            <h1 className="mt-2 h1login mainLetterColor">Welcome back</h1>
            <p className="mt-2 mainLetterColor">If you already have an account, just sign in. We've missed you!</p>
        </div>
        :
         <div className="col-md-12">
            <h1 className="mt-2 h1login mainLetterColor">Welcome to Renting Buddy</h1>
            <p className="mt-2 mainLetterColor">Create account to find perfect apartment for you!</p>
        </div>
      }

    </div>
    <div className = "row">
        <div className = "col-md-6 mt-3">
            <Slideshow />
        </div>
        <div className="col-md-1"></div>
        <div className = "col-md-5 formPosition bg-gradient-light mt-5">
        <form onSubmit = {signInOrUp}>
            <label className="mt-2">
             <span>Email</span>
             {emailMissing === "" ?
             <input type="email"  value={email} onChange={(e) => setEmail(e.target.value)}/>
             : <><input className="errorBorder" type="email" required="required" onChange={(e) => setEmail(e.target.value)}/>
             <p className = "requiredMessage"> Email is required! </p>
             </>}
            </label>
            <label>
              <span>Password</span>
              {passwordMissing === "" ?
              <input type={showPasswordChecked === false ? "password" : "text"} value={password} onChange={(e) => setPassword(e.target.value)} /> :
              <>
              <input type={showPasswordChecked === false ? "password" : "text"} className="errorBorder" required="required" onChange={(e) => setPassword(e.target.value)} />
               <p className = "requiredMessage"> Password is required! </p>
               </>}
            </label>
            {signIn === true ?
            <>
            <ShowPassword showPassword={showPasswordChecked} setShowPassword={setShowPasswordChecked}/>
            <button type="submit" className="button mt-3">Sign In</button>
            <div className="col-md-4">
                <p className="mt-4">New here?</p>
            </div>
            <button type="button" className="button" onClick={toggleSgnIn}>Create account</button>
            </> : <>
            <label>
              <span>Re-type password</span>
              {reTypePasswordMissing === "" ?
              <input type={showPasswordChecked === false ? "password" : "text"} value={repeatedPassword} onChange={(e) => setRepeatedPassword(e.target.value)} /> : <>
              <input type={showPasswordChecked === false ? "password" : "text"} className="errorBorder" onChange={(e) => setRepeatedPassword(e.target.value)} />
               <p className = "requiredMessage">  Re-type password field is required! </p>
              </>}
            </label>
            <ShowPassword showPassword={showPasswordChecked} setShowPassword={setShowPasswordChecked} />
             <button type="submit" className="button mt-3">Sign Up</button>
             <button type="button" className="button mt-5" onClick={toggleSgnIn}>Back to login page</button>
            </>}
            </form>
            {
             message !== "" && <p className = "errorMessage mt-4"> {message}! </p>
            }
            {
             successMessage !== "" && <p className = "text-success mt-4"> {successMessage}! </p>
            }
        </div>
    </div>
   </div>
  );
}

export default LoginComponent;
import React, { Component } from 'react'
import mainpage from '../assets/main-page.jpg'
import './MainPage.css'

const MainPage = () => {
  return (
  <div className="container-fluid">
  <div className="row">
  <div className="col-md-12" style={{backgroundImage:`url(${mainpage})`, padding:0, width:`100%`, height:`100vh`}}>
    <h1 className="mt-5 ml-5">Don't wait </h1>
    <h1 className="ml-5">Get your dream apartment</h1>
    <button className="ml-5 mt-5 bg-dark mainbutton">Find Apartment</button>
  </div>
  </div>
  <div className="row">
    <div className="col-md-12">
        <p> BLAAA </p>
    </div>
  </div>
  </div>
  )
}

export default MainPage
import React, { Component } from 'react'
import mainpage from '../assets/main-page.jpg'

const MainPage = () => {
  return (
  <div className="container-fluid">
  <div className="row">
  <div className="col-md-12" style={{backgroundImage:`url(${mainpage})`, padding:0, width:`100%`, height:`100vh`}}>
    <p className="mt-10 ml-5">REDIRECTED</p>
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
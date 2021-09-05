import React from 'react';
import { Fade } from 'react-slideshow-image';
import 'react-slideshow-image/dist/styles.css'
import loginimage from './assets/login2.jpg'
import loginimage1 from './assets/login3.jpg'
import loginimage2 from './assets/login4.jpg'



const Slideshow = () => {
  return (
    <div className="slide-container">
      <Fade>
        <div className="each-fade">
          <div className="image-container">
            <img src={loginimage} style={{width: "100%"}} />
          </div>
        </div>
        <div className="each-fade">
          <div className="image-container">
            <img src={loginimage1} style={{width: "100%"}} />
          </div>
        </div>
        <div className="each-fade">
          <div className="image-container">
            <img src={loginimage2} style={{width: "100%"}} />
          </div>
        </div>
      </Fade>
    </div>
  )
}

export default Slideshow
import React from 'react';
import { useState, useEffect, useRef } from 'react'
import { GoogleMap, LoadScript, Marker, Circle } from '@react-google-maps/api';

const MapContainer = ({position, setPosition, diameter}) => {

const API_KEY = 'AIzaSyDCjUdKEcmM7E1CopQLP4q0l8SasbRTFPM';

 const mapStyles = {
    height: "40vh",
    width: "100%",
    left:"9%",
    top: "9%"
 };

const options = {
        fillColor: "#ff6961",
        strokeColor: "#ff6961",
        strokeOpacity: 0.8,
        strokeWeight: 0.1,
        fillOpacity: 0.35,
        clickable: false,
        draggable: false,
        editable: false,
        visible: true,
        zIndex: 1
}

 const changeCoordinates = (e) => {
        setPosition({lat:e.latLng.lat(), lng:e.latLng.lng()})
 }

  return (
     <LoadScript className="ml-2"
       googleMapsApiKey={API_KEY}>
        <GoogleMap onClick={changeCoordinates}
          mapContainerStyle={mapStyles}
          zoom={13}
          center={position}>
         <Marker position={position}/>
         <Circle center={position}
          radius={diameter*1000}
          options={options} />
         </GoogleMap>
     </LoadScript>
  )
}

export default MapContainer;
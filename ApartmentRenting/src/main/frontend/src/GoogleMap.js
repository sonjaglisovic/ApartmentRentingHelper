import React from 'react';
import { useState, useEffect, useRef } from 'react'
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';

const MapContainer = ({position, setPosition}) => {

  const mapStyles = {
    height: "40vh",
    width: "100%",
    left:"9%",
    top: "9%"
 };

const clickMap = (e) => {
    setPosition({lat:e.latLng.lat(), lng:e.latLng.lng()})
}

  return (
     <LoadScript className="ml-2"
       googleMapsApiKey='AIzaSyDCjUdKEcmM7E1CopQLP4q0l8SasbRTFPM'>
        <GoogleMap onClick={clickMap}
          mapContainerStyle={mapStyles}
          zoom={13}
          center={position}
        > <Marker position={position}/> </GoogleMap>
     </LoadScript>
  )
}

export default MapContainer;
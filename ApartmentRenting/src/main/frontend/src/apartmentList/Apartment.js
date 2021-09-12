import React, { Component } from 'react'
import { useState, useEffect, useRef } from 'react'
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import './ApartmentList.css'
import { BiMap, BiMoney, BiSun, BiArea, BiCar } from "react-icons/bi";
import { BsHouseDoorFill, BsBuilding } from "react-icons/bs";

const Apartment = ({apartment}) => {

   return(
    <>
    <Row>
    <Image className="mt-3 imm" src={apartment.image}  responsive thumbnail />
    </Row>
    <Row >
        <span className="ml-2 mt-3"><BiMap /> Location: {apartment.location} </span>
    </Row>
     <Row className="every-first">
        <span className="ml-2 mt-3"><BiMoney /> Price: {apartment.price} &euro; </span>
    </Row>
    <Row >
        <span className="ml-2 mt-3"><BsHouseDoorFill /> Number of rooms: {apartment.numOfRooms} </span>
    </Row>
     <Row className="every-first">
        <span className="ml-2 mt-3"><BsBuilding /> Floor: {apartment.floor} </span>
    </Row>
    <Row >
        <span className="ml-2 mt-3 "><BiSun /> Heating type: {apartment.heatingType} </span>
    </Row>
      <Row className="every-first">
            <span className="ml-2 mt-3"><BiArea /> Area: {apartment.area} m<sup>2</sup> </span>
        </Row>
      <Row >
          <span className="ml-2 mt-3"><BiCar /> Parking place: {apartment.parking ? " yes" : " no"}  </span>
      </Row>
      <Row>
            <a role="button" class="btn text-light moreInfo" href={apartment.url}>More info</a>
        </Row>
    </>
   )
}

export default Apartment
import React, { Component } from 'react'
import { useState, useEffect, useRef } from 'react'
import Apartment from './Apartment'
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Container from 'react-bootstrap/Container'
import Col from 'react-bootstrap/Col'

const ApartmentList = ({apartmentList}) => {

return(

    <>
    {apartmentList.length > 0 ? apartmentList.map((apartment, index) => (
        <>
        <Col xs={6} md={4}>
            <Apartment apartment={apartment} />
         </Col>
         {(index + 1) % 4 === 0 && <div className="clearfix"></div>}
        </>
    ))  : <div className="row mt-3"> <div className="col-md-12 loading"> <span> No apartments to show </span> </div> </div>}
    </>
)
}

export default ApartmentList
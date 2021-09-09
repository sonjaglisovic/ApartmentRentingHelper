import React, { Component } from 'react'
import mainpage from '../assets/main-page.jpg'
import './MainPage.css'
import Navbar from '../navbar/Navbar'
import { useState, useEffect, useRef } from 'react'
import tt from '@tomtom-international/web-sdk-maps'
import MapContainer from '../GoogleMap'
import $ from 'jquery';

const MainPage = ({loginUser, setLoginUser, addDemand}) => {

const [priceMin, setPriceMin] = useState(null)
const [priceMax, setPriceMax] = useState(null)
const [demandName, setDemandName] = useState(null)
const [numberOfRoomsMin, setNumberOfRoomsMin] = useState(null)
const [numberOfRoomsMax, setNumberOfRoomsMax] = useState(null)
const [heatType, setHeatType] = useState(null)
const [parkingPlaceRequired, setParkingRequired] = useState(false)
const [minArea, setMinArea] = useState(null)
const [maxArea, setMaxArea] = useState(null)
const mapElement = useRef()
const [floorMin, setFloorMin] = useState(null)
const [floorMax, setFloorMax] = useState(null)
const [diameter, setDiameter] = useState(null)
const [updateDemand, setUpdateDemand] = useState(true)
const [position, setPosition] = useState({lat:44.787197, lng:20.457273} )
const [mapZoom, setMapZoom] = useState(13)
const [map, setMap] = useState({})


 useEffect(() => {
    setLoginUser(JSON.parse(localStorage.getItem("loginUser")));

  }, [])

 const changeHandler = (e) => {
     var options = e.target.options;
      var value = [];
      for (var i = 0, l = options.length; i < l; i++) {
        if (options[i].selected) {
          value.push(options[i].value);
        }
      }
      setHeatType(value);
  }

const processDemand = (event) => {
    event.preventDefault();
    setPriceMin(priceMin === "" ? null : parseInt(priceMin, 10));
    setPriceMax(priceMax === "" ? null : parseInt(priceMax, 10));
    setNumberOfRoomsMin(numberOfRoomsMin === "" ? null : parseFloat(numberOfRoomsMin));
    setNumberOfRoomsMax(numberOfRoomsMax === "" ? null : parseFloat(numberOfRoomsMax));
    if(heatType != null) {
    var heatTypes = ""
        heatType.map((heatingType) => {heatTypes = heatTypes + (heatTypes === "" ?  "" : ",") + heatingType})
    }
    setMinArea(minArea === "" ? null : parseInt(minArea, 10));
    setMaxArea(maxArea === "" ? null : parseInt(maxArea, 10));
    setFloorMin(floorMin === "" ? null : parseInt(floorMin, 10));
    setFloorMax(floorMax === "" ? null : parseInt(floorMax, 10));
    const demand = {id:null, lat:position.lat, lng:position.lng, diameter:parseFloat(diameter), priceMin:priceMin, priceMax:priceMax,
        numberOfRoomsMin:numberOfRoomsMin, numberOfRoomsMax:numberOfRoomsMax, heatType:heatTypes, parkingPlaceRequired:parkingPlaceRequired,
        minArea:minArea, maxArea:maxArea, floorMin:floorMin, floorMax:floorMax, userEmail:loginUser, demandName:demandName}
    addDemand(demand);
    $("#addApartmentModal .close").click();


}

  return (

<>
  <Navbar setLoginUser={setLoginUser} loginUser={loginUser}/>

  <div className="container-fluid">
  <div className="row">
  <div className="col-md-12" style={{backgroundImage:`url(${mainpage})`, padding:0, width:`100%`, height:`100vh`}}>
  {loginUser != "" ?
  <>
      <h1 className="mt-5 ml-5">Don't wait </h1>
      <h1 className="ml-5">Get your dream apartment</h1>
      <button className="ml-5 mt-5 bg-dark mainbutton " data-toggle="modal" data-target="#addApartmentModal">Find Apartment</button>

      <div class="modal fade" id="addApartmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
        <div class="modal-dialog bg-dark" role="document">
          <div class="modal-content bg-light">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLongTitle">Add filters</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
             <form onSubmit={processDemand}>
            <div class="modal-body">

                <table>
                    <tr>
                        <MapContainer position={position} setPosition={setPosition} />
                    </tr>
                    <div class="form-group required">
                    <tr className="ml-5">
                     <td>
                        <label class="control-label">Diameter: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required step=".01" onChange={(e) => setDiameter(e.target.value)} />
                        </td>
                        </tr>
                        <tr>
                        <td>
                        <label class="control-label">Filter Name: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="text" required step=".01" onChange={(e) => setDemandName(e.target.value)} />
                        </td>
                        </tr>
                        <tr>
                        <td>
                             <label>Minimal price: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setPriceMin(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Maximal price: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setPriceMax(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Minimal number of rooms: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setNumberOfRoomsMin(e.target.value)} />
                        </td>
                    </tr>
                     <tr>
                        <td>
                             <label>Maximal number of rooms: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setNumberOfRoomsMax(e.target.value)} />
                        </td>
                    </tr>
                     <tr>
                        <td>
                             <label>Heating type: </label>
                        </td>
                        <td>
                            <select className="mt-3" id="heating" name="heatType" multiple onChange={changeHandler}>
                              <option value="centralno">Centralno</option>
                              <option value="etažno">Etažno</option>
                              <option value="nastruju">Na struju</option>
                              <option value="klima">Klima</option>
                            </select>
                             <span class="focus"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Parking required: </label>
                        </td>
                        <td>
                           <input type="checkbox" onChange={(e) => setParkingRequired(!parkingPlaceRequired)} />
                        </td>
                    </tr>

                     <tr>
                        <td>
                             <label>Minimal area: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setMinArea(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Maximal area: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setMaxArea(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Minimal floor: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setFloorMin(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Maximal floor: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" onChange={(e) => setFloorMax(e.target.value)} />
                        </td>
                    </tr>

                    </div>
                </table>

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn text-light modal-close">Save filters</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

            </div>
            </form>
          </div>
        </div>
      </div>

  </>  :
  <>
      <h1 className="mt-5 ml-5">You have to login first </h1>
  </>
  }
   </div>
  </div>
  {loginUser!== "" && <div className="row">
    <div className="col-md-12">
        <p> BLAAA </p>
    </div>
  </div>}
  </div>
  </>
  )
}

export default MainPage
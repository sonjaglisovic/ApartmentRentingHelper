import React, { Component } from 'react'
import mainpage from '../assets/main-page.jpg'
import './MainPage.css'
import Navbar from '../navbar/Navbar'
import { useState, useEffect, useRef } from 'react'
import tt from '@tomtom-international/web-sdk-maps'

const MainPage = ({loginUser, setLoginUser}) => {

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
const [lat, setLat] = useState(null)
const [lon, setLon] = useState(null)
const [diameter, setDiameter] = useState(null)
const [updateDemand, setUpdateDemand] = useState(true)
const [mapLatitude, setMapLatitude] = useState(44.787197)
const [mapLongitude, setMapLongitude] = useState(20.457273)
const [mapZoom, setMapZoom] = useState(13)
const [map, setMap] = useState({})

 useEffect(() => {
    setLoginUser(JSON.parse(localStorage.getItem("loginUser")));

    var map = tt.map({
        key: "B6hkAefPnSwcihAngy9SSffKppzyW5kw",
        container: mapElement.current,
        center: [mapLongitude, mapLatitude],
        zoom: mapZoom
      });
      setMap(map);

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
      console.log(heatType);
  }

const getUserCoordinates = (e) => {
    var position = e.latLng;
    console.log(position);
}

const updateMap = () => {
  map.setCenter([parseFloat(mapLongitude), parseFloat(mapLatitude)]);
  map.setZoom(mapZoom);
};

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
            <div class="modal-body">
            <form>
                <table>
                    <tr>
                    <div ref={mapElement} className="mapDiv" onClick={getUserCoordinates}></div>
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
                        <label class="control-label">Demand Name: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required step=".01" onChange={(e) => setDemandName(e.target.value)} />
                        </td>
                        </tr>
                        <tr>
                        <td>
                             <label>Minimal price: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setPriceMin(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Maximal price: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setPriceMax(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Minimal number of rooms: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setNumberOfRoomsMin(e.target.value)} />
                        </td>
                    </tr>
                     <tr>
                        <td>
                             <label>Maximal number of rooms: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setNumberOfRoomsMax(e.target.value)} />
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
                            <input className="input_fields" type="number" required onChange={(e) => setMinArea(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Maximal area: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setMaxArea(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Minimal floor: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setFloorMin(e.target.value)} />
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label>Maximal floor: </label>
                        </td>
                        <td>
                            <input className="input_fields" type="number" required onChange={(e) => setFloorMax(e.target.value)} />
                        </td>
                    </tr>

                    </div>
                </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn text-light modal-close">Save filters</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

            </div>
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
import React, { Component } from 'react'
import { useState, useEffect, useRef } from 'react'
import './DemandList.css'
import { FiMinimize2, FiMaximize2, FiEdit3, FiTrash2 } from "react-icons/fi";
import $ from 'jquery'
import ApartmentList from '../apartmentList/ApartmentList'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'

const Demand = ({demand, showDetails, index, setShowDetails, deleteDemand, onEdit}) => {

const [modalId, setModalId] = useState("")

const toggleExpand = () => {
    var details = [...showDetails];
    details[index] = !details[index];
    setShowDetails(details);
}

useEffect(() => {
 var modalDialogId = "#" + demand.demandId;
 setModalId(modalDialogId);
}, [])

const deleteFilter = async () => {
    deleteDemand(demand.demandId);
    $(`${modalId} .close`).click();

}

const onUpdate = () => {
    onEdit(demand);
}

return(
   <>
   <div className="row filter mt-3">
    <div className="col-md-10">
     <div className = "row dataAlign">
       <h3> {demand.demandName} </h3>
       </div>
       <div className="row dataAlign">
        <span> Price:  {demand.priceMin !== null ? demand.priceMin : "/"  }-{demand.priceMax !== null ? demand.priceMax : "/"} &euro; </span>
        </div>
        <div className="row dataAlign">
        <span> Rooms:  {demand.numberOfRoomsMin !== null ? demand.numberOfRoomsMin : "/"  }-{demand.numberOfRoomsMax !== null ? demand.numberOfRoomsMax : "/"} </span>
        </div>
        <div className="row dataAlign">
        <span> Heating type:  {demand.heatType !== null && demand.heatType !== "" ? demand.heatType : "/"} </span>
        </div>
        <div className="row dataAlign">
        <span> Parking place required:  {demand.parkingPlaceRequired === true ? "Yes" : "No"} </span>
        </div>
         <div className="row dataAlign">
        <span> Area:  {demand.minArea !== null ? demand.minArea : "/"  }-{demand.maxArea !== null ? demand.maxArea : "/"} m<sup>2</sup></span>
        </div>
        <div className="row dataAlign">
        <span> Floor:  {demand.floorMin !== null ? demand.floorMin : "/"  }-{demand.floorMax !== null ? demand.floorMax : "/"} </span>
        </div>
        <div className="row dataAlign">
        <span> Coordinates: {demand.lat} : {demand.lng} </span>
        </div>
        <div className="row dataAlign">
        <span> Radius: {demand.diameter} km </span>
        </div>
    </div>
    <div className="col-md-2 d-flex flex-row-reverse">
        <FiTrash2 className="icon-size"
            data-toggle="modal" data-target={modalId}
          />
           <FiEdit3 className="icon-size"
              onClick = { onUpdate }
            />
           {showDetails[index] === false ? <FiMaximize2 onClick={toggleExpand} className="icon-size" /> : <FiMinimize2 onClick={toggleExpand} className="icon-size" />}
    </div>
    </div>
    {showDetails[index] === true && <Container>
    <Row className="mt-3">
        <ApartmentList apartmentList={demand.apartmentList} />
     </Row>
    </Container>
    }
     <div class="modal fade" id={demand.demandId} tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog bg-dark" role="document">
              <div class="modal-content bg-light">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">Delete item</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>

                <div class="modal-body">
                    Are you sure you want to delete this filter?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn text-light modal-close" onClick={deleteFilter}>Delete</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

                </div>

              </div>
            </div>
          </div>

    </>
)
}

export default Demand
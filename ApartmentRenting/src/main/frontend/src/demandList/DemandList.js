import React, { Component } from 'react'
import { useState, useEffect, useRef } from 'react'
import './DemandList.css'
import Demand from './Demand'

const DemandList = ({getDemands, loginUser, deleteDemand, demands, setDemands, loading, setLoading, onEdit}) => {

const [showDetails, setShowDetails] = useState([]);

 useEffect(() => {
   const fetchDemands = async () => {
        const demandsFromServer = await getDemands(loginUser);
        setDemands(demandsFromServer)
        setLoading(false);
    }
    setLoading(true);
    fetchDemands();
  }, [])

  useEffect(() => {
   var showDetails = [];
   demands.map((singleDemand) => showDetails.push(false))
   setShowDetails(showDetails)
   demands.length !== 0 && setLoading(false)
  }, [demands])

const removeDemandFromListAndDelete = (demandId) => {
    var newDemands = demands.filter((demand) => {
        return demand.demandId !== demandId;
    })
    setDemands(newDemands);
    deleteDemand(demandId);
}

  return(
    <>
    <h1 className="mt-5 ml-2 filterh">My filters</h1>
    {loading === false ?
    <>
        {demands.length !== 0 ?
        <div className="conatiner">
            {demands.map((demand, index) => (
                <Demand key={index} demand={demand} showDetails={showDetails} index={index} setShowDetails={setShowDetails} loading={loading} deleteDemand={removeDemandFromListAndDelete} onEdit={onEdit}/>
            ))}

        </div> :  <div className="row mt-3"> <div className="col-md-12 loading"> <span> No filters to show</span> </div> </div>}
        </> : <div className="row mt-3"> <div className="col-md-12 loading"> <span> Loading... </span> </div> </div> }
    </>
  )
}

export default DemandList
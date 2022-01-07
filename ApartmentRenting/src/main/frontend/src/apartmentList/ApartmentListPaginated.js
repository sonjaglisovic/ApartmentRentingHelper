import React, { useState, useEffect } from 'react';
import ReactPaginate from 'react-paginate';
import './ApartmentList'
import Apartment from './Apartment'
import './ApartmentList.css'
import Image from 'react-bootstrap/Image'
import Row from 'react-bootstrap/Row'
import Container from 'react-bootstrap/Container'
import Col from 'react-bootstrap/Col'


const ApartmentListPaginated = ({apartmentList}) => {
  const [pagination, setPagination] = useState({
    data: apartmentList,
    offset: 0,
    numberPerPage: 6,
    pageCount: 0,
    currentData: []
  });
  useEffect(() => {
    setPagination((prevState) => ({
      ...prevState,
      pageCount: prevState.data.length / prevState.numberPerPage,
      currentData: prevState.data.slice(pagination.offset, pagination.offset + pagination.numberPerPage)
    }))
  }, [pagination.numberPerPage, pagination.offset])

  const handlePageClick = (event) => {
    const selected = event.selected;
    const offset = selected * pagination.numberPerPage
    setPagination({ ...pagination, offset })
  }

  return (
    <>
    <div className = "conatiner ml-4 mt-4">
    <div className="row">
      {pagination.currentData && pagination.currentData.map((apartment, index) => (
           <>
               <div className="col-md-4">
                  <Apartment apartment={apartment} />
               </div>
               {(index + 1) % 4 === 0 && <div className="clearfix"></div>}
          </>
      ))}
        </div>
      <div className="row">
       <div className="col-md-12">
       <ReactPaginate className="pagination"
                    previousLabel={'previous'}
                    nextLabel={'next'}
                    breakLabel={'...'}
                    pageCount={pagination.pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={4}
                    onPageChange={handlePageClick}
                    containerClassName={'pagination'}
                    activeClassName={'active'}
                    />
               </div>
          </div>
         </div>

    </>
  );
}

export default ApartmentListPaginated
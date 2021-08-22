package rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception;

import org.springframework.http.HttpStatus;

public class DemandNotFoundException extends ApartmentRentingException {

    public DemandNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

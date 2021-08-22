package rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception;

import org.springframework.http.HttpStatus;

public class ApartmentRentingException extends RuntimeException{

    private HttpStatus httpStatus;
    private String errorMessage;

    ApartmentRentingException(String message, HttpStatus httpStatus) {
        super(message);
        errorMessage = message;
        this.httpStatus = httpStatus;
    }
}

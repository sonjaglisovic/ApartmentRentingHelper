package rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordFormatException extends ApartmentRentingException{

    public IncorrectPasswordFormatException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

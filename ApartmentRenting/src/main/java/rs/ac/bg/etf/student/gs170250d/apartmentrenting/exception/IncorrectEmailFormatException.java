package rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception;

import org.springframework.http.HttpStatus;

public class IncorrectEmailFormatException extends ApartmentRentingException{

    public IncorrectEmailFormatException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

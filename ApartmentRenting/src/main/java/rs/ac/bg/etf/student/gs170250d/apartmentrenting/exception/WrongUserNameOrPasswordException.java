package rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception;

import org.springframework.http.HttpStatus;

public class WrongUserNameOrPasswordException extends ApartmentRentingException{

    public WrongUserNameOrPasswordException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}

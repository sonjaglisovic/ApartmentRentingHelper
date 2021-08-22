package rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsUserWithEmail extends ApartmentRentingException{

    public AlreadyExistsUserWithEmail(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

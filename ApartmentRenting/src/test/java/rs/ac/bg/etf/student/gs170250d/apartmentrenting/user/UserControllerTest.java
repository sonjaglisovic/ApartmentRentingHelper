package rs.ac.bg.etf.student.gs170250d.apartmentrenting.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.controller.UserController;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.AlreadyExistsUserWithEmail;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.IncorrectEmailFormatException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.IncorrectPasswordFormatException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.WrongUserNameOrPasswordException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.UserRequest;

import javax.transaction.Transactional;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void registerWithAllCorrectRequestParameters() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "sonja1234@");
        ResponseEntity responseEntity = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void tryToRegisterWithIncorrectEmailFormat() {
        UserRequest userRequest = new UserRequest("sonja3gmail.com", "sonja1234@");
        ResponseEntity res = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    @Test
    public void tryToRegisterWithIncorrectPasswordFormatLengthLessThanRequired() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "sja1@");
        ResponseEntity res = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    @Test
    public void tryToRegisterWithIncorrectPasswordFormatNoLetter() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "123451234@");
        ResponseEntity res = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    @Test
    public void tryToRegisterWithIncorrectPasswordFormatNoNumber() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "sonjaaaaa@");
        ResponseEntity res = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    @Test
    public void tryToRegisterWithIncorrectPasswordFormatNoSpecialCharacter() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "sonja123433");
        ResponseEntity res = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    @Test
    public void tryToRegisterWithAlreadyExistingEmail() {
        UserRequest userRequest = new UserRequest("sonja1@gmail.com", "sonja1234@");
        userController.register(userRequest);
        ResponseEntity res = userController.register(userRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    @Test
    public void loginWithRightCredentials() {
        UserRequest userRequest = new UserRequest("sonja1@gmail.com", "sonja1234@");
        userController.register(userRequest);
        ResponseEntity response = userController.login(userRequest);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void tryToLoginWithWrongEmail() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "sonja1234@");
        ResponseEntity res = userController.login(userRequest);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    public void tryToLoginWithWrongPassword() {
        UserRequest userRequest = new UserRequest("sonja3@gmail.com", "sonja1234@");
        userController.register(userRequest);
        UserRequest loginRequest = new UserRequest("sonja3@gmail.com", "sonja123");
        ResponseEntity res = userController.login(loginRequest);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }
}

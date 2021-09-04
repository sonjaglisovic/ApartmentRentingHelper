package rs.ac.bg.etf.student.gs170250d.apartmentrenting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.AlreadyExistsUserWithEmail;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.IncorrectEmailFormatException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.IncorrectPasswordFormatException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.WrongUserNameOrPasswordException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.UserRequest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.UserRepository;

import java.util.Optional;
import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestParam UserRequest user) {

        Optional<UserEntity> fetchedUser = userRepository.findById(user.getEmail());
        if(fetchedUser.isEmpty()) {
            throw new WrongUserNameOrPasswordException("User name is not correct", HttpStatus.NOT_FOUND);
        }
        if(!user.getPassword().equals(fetchedUser.get().getPassword())) {
            throw new WrongUserNameOrPasswordException("User name or password is not correct", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping(path = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestParam UserRequest user) {

        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(user.getEmail()).matches()) {
            throw new IncorrectEmailFormatException("Incorrect email format", HttpStatus.BAD_REQUEST);
        }
        String passwordRegex = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W_]).{8,}$";
        pattern = Pattern.compile(passwordRegex);
        if(!pattern.matcher(user.getPassword()).matches()) {
            throw new IncorrectPasswordFormatException("Incorrect password format exception", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.findById(user.getEmail()).isPresent()) {
            throw new AlreadyExistsUserWithEmail("Already exists user with this email", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity(user);
        userRepository.save(userEntity);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }
}

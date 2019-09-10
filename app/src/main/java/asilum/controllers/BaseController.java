package asilum.controllers;

import asilum.exceptions.IncorrectUsernameOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class BaseController {

    @ResponseBody
    @ExceptionHandler(IncorrectUsernameOrPasswordException.class)
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("Login or password incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

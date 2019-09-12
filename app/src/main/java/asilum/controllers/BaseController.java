package asilum.controllers;

import asilum.exceptions.ExistingUserException;
import asilum.exceptions.InvalidParameterException;
import asilum.exceptions.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.RollbackException;
import java.util.List;

@Controller
public abstract class BaseController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidArgumentException(MethodArgumentNotValidException e) {

        final List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : errors ) {
            builder.append("Error on field " + error.getField() + " : " + error.getDefaultMessage() + "\n");
        }
        return new ResponseEntity<>(builder.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserException() {
        return new ResponseEntity<>("Login or password incorrect", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleExistingUserException(Exception ex, ServletWebRequest request) {
        if (request.getRequest().getMethod().equals("POST") && request.getRequest().getRequestURI().equals("/users")){
            return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
        } else {
            System.out.println(ex);
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

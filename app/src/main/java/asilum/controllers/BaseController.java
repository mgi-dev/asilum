package asilum.controllers;

import asilum.exceptions.UserNotFoundException;
import asilum.locales.ErrorMessages;
import asilum.models.database.ConstraintNames;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public abstract class BaseController{

    private Map<String, ErrorMessages> linkedErrorMessages = new HashMap<String, ErrorMessages>(){{
        put(ConstraintNames.UNIQUE_USERNAME_CONSTRAINT, ErrorMessages.USER_ALREADY_EXISTING);
    }};


    private ResponseEntity<String> handleArgumentError(List<FieldError> errors){
        StringBuilder builder = new StringBuilder();
        for (FieldError error : errors ) {
            builder.append("Error on field " + error.getField() + " : " + error.getDefaultMessage() + "\n");
        }
        return new ResponseEntity<>(builder.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

//    @ExceptionHandler(Exception.class)
//    public @ResponseBody
//    void handleEverything(Exception e) {
//        System.out.println("===================================================================");
//        System.out.println("===================================================================");
//        System.out.println("===================================================================");
//        System.out.println("===================================================================");
//        System.out.println("===================================================================");
//        System.out.println(e);
//        System.out.println(e.getMessage());
//    }

    @ExceptionHandler(BindException.class)
    public @ResponseBody
    ResponseEntity<String> handleEverything(BindException e) {
        return this.handleArgumentError(e.getBindingResult().getFieldErrors());
    }

        @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseEntity<String> handleInvalidArgumentException(MethodArgumentNotValidException e) {
        return this.handleArgumentError(e.getBindingResult().getFieldErrors());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserException() {
        return new ResponseEntity<>("Login or password incorrect", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleExistingUserException(DataIntegrityViolationException ex) {
        for(Map.Entry<String,ErrorMessages> error: this.linkedErrorMessages.entrySet()) {
            if (ex.getMostSpecificCause().getMessage().contains(error.getKey())) {
                return new ResponseEntity<>(error.getValue().label, HttpStatus.CONFLICT);
            }
        }

        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println("UNKNOWN ERROR: What the fuck happened ?");
        System.out.println(ex.toString());
        System.out.println(ex.getMessage());
        System.out.println(ex.getMostSpecificCause().toString());
        System.out.println(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);


    }
}

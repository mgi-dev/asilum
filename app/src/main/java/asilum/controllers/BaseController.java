package asilum.controllers;

import asilum.exceptions.InvalidParameterException;
import asilum.exceptions.UserNotFoundException;
import asilum.locales.ErrorMessages;
import asilum.models.database.ConstraintNames;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
//        System.out.println(e.toString());
//        System.out.println(e.getMessage());
//    }

    @ExceptionHandler(BindException.class)
    public @ResponseBody
    ResponseEntity<String> handleBindingException(BindException e) {
        return this.handleArgumentError(e.getBindingResult().getFieldErrors());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody
    ResponseEntity<String> handleJsonParseError(HttpMessageNotReadableException e) {
        String rawErrorMessage = e.getCause().toString();
        String field = rawErrorMessage.substring(rawErrorMessage.indexOf("[\"") + 1, rawErrorMessage.indexOf("\"]") + 1);
        if (field.length() == 0) {
            return new ResponseEntity<>("You fucked up your JSON pretty badly, check your parameters.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Something wrong with input value. The field " + field + " is not correctly formatted.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
        @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ResponseEntity<String> handleInvalidArgumentException(MethodArgumentNotValidException e) {
        return this.handleArgumentError(e.getBindingResult().getFieldErrors());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserException() {
        return new ResponseEntity<>("User cannot be found for given values.", HttpStatus.NOT_FOUND);
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
        System.out.println("You probably forgot to link your error in linkedErrorsMessages.");
        System.out.println(ex.toString());
        System.out.println(ex.getMessage());
        System.out.println(ex.getMostSpecificCause().toString());
        System.out.println(ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);


    }
}

package ee.mark.webshiospring.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleException(NoSuchElementException e) {
        ErrorResponse response = new ErrorResponse(new Date(), "This resource does not exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleException(EmptyResultDataAccessException e) {
        ErrorResponse response = new ErrorResponse(new Date(), "This resource does not exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleException(RegistrationException e) {
        ErrorResponse response = new ErrorResponse(new Date(), "Mandatory fields are not filled", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleException(UserExistsException e) {
        ErrorResponse response = new ErrorResponse(new Date(), "User with this person code already exists", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleException(EmailExistsException e) {
        ErrorResponse response = new ErrorResponse(new Date(), "User with this email already exists", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

package ee.mark.webshiospring.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(NoSuchElementException e) {
        return new ErrorResponse(new Date(), "This resource does not exist");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(EmptyResultDataAccessException e) {
        return new ErrorResponse(new Date(), "This resource does not exist");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(RegistrationException e) {
        return new ErrorResponse(new Date(), "Mandatory fields are not filled");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(UserExistsException e) {
        return new ErrorResponse(new Date(), "User with this person code already exists");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(EmailExistsException e) {
        return new ErrorResponse(new Date(), "User with this email already exists");
    }
}

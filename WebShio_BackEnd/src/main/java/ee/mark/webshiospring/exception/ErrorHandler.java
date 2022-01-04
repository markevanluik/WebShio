package ee.mark.webshiospring.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(EntityNotFoundException e) {
        return new ErrorResponse(new Date(), "This resource does not exist");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(ConstraintViolationException e) {
        return new ErrorResponse(new Date(), "Database constraints have been violated");
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(DataIntegrityViolationException e) {
        return new ErrorResponse(new Date(), "Database constraints have been violated");
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

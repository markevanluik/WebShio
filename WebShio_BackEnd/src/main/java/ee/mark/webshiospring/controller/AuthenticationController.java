package ee.mark.webshiospring.controller;

import ee.mark.webshiospring.exception.EmailExistsException;
import ee.mark.webshiospring.exception.RegistrationException;
import ee.mark.webshiospring.exception.UserExistsException;
import ee.mark.webshiospring.model.Person;
import ee.mark.webshiospring.model.input.LoginData;
import ee.mark.webshiospring.model.output.AuthData;
import ee.mark.webshiospring.repository.PersonRepository;
import ee.mark.webshiospring.service.JwtBuilder;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.util.Calendar.HOUR;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final PersonRepository personRepository;

    private final PasswordEncoder encoder;

    private final JwtBuilder jwtBuilder;

    @Operation(summary = "Log in user")
    @PostMapping("login")
    public ResponseEntity<AuthData> login(@RequestBody LoginData loginData) {
        if(loginData.getEmail() != null && loginData.getPassword() != null) {
            Person person = personRepository.findByEmail(loginData.getEmail());
            if(person != null) {
                if (encoder.matches(loginData.getPassword(), person.getPassword()) ||
                        loginData.getPassword().equals(person.getPassword())) {
                    AuthData data = jwtBuilder.createJwtAuthToken(person);
                    log.info("Login successful: {}", data);
                    return new ResponseEntity<>(data, HttpStatus.OK);
                }
            }
        }
        log.warn("Login failed");
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Sign up/Register user")
    @PostMapping("signup")
    public ResponseEntity<Person> signup(@RequestBody Person person) throws RegistrationException, UserExistsException, EmailExistsException {
        try {
            if (personRepository.findById(person.getPersonCode()).isPresent()) {
                throw new UserExistsException();
            }
            if (personRepository.findByEmail(person.getEmail()) != null) {
                throw new EmailExistsException();
            }
            String hashedPassword = encoder.encode((person.getPassword()));
            person.setPassword(hashedPassword);
            personRepository.save(person);
            log.info("User registered {}", person.getPersonCode());
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (UserExistsException exception) {
            log.error("This user already exists {}",
                    person.getPersonCode());
            throw new UserExistsException();
        } catch (EmailExistsException exception) {
            log.error("This email({}) already exists {}", person.getEmail(),
                    person.getPersonCode());
            throw new EmailExistsException();
        } catch (Exception exception) {
            log.error("User registration failed {} {}",
                    person.getPersonCode(), exception.getMessage());
            throw new RegistrationException();
        }

    }
}

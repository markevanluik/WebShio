package ee.mark.webshiospring.repository;

import ee.mark.webshiospring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByEmail(String email);
}

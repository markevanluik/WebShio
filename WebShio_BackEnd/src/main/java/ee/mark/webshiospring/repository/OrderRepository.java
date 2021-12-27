package ee.mark.webshiospring.repository;

import ee.mark.webshiospring.model.Order;
import ee.mark.webshiospring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrderByPersonEquals(Person person);
}

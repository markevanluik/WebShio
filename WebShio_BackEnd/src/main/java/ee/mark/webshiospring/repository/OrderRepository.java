package ee.mark.webshiospring.repository;

import ee.mark.webshiospring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

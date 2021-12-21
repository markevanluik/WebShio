package ee.mark.webshiospring.repository;

import ee.mark.webshiospring.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

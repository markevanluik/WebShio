package ee.mark.webshiospring.repository;

import ee.mark.webshiospring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

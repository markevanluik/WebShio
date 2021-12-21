package ee.mark.webshiospring.service;

import ee.mark.webshiospring.model.Category;
import ee.mark.webshiospring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        //itemCache.updateCache(item);
        return categoryRepository.save(category);
    }

    public Category editCategory(Category category) {
        //itemCache.updateCache(item);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        //itemCache.updateCache(item);
        categoryRepository.deleteById(id);
    }

    public Category getCategory(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id).get();
        } else {
            throw new NoSuchElementException();
        }
    }
}

package ee.mark.webshiospring.controller;

import ee.mark.webshiospring.model.Category;
import ee.mark.webshiospring.model.output.CategoryResponse;
import ee.mark.webshiospring.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @Operation(summary = "Get one category")
    @GetMapping("categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @Operation(summary = "Add one category")
    @PostMapping("categories")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody Category category) {
        Category categorySaved = categoryService.addCategory(category);
        return new ResponseEntity<>(new CategoryResponse("Category '" + categorySaved.getCategoryName()
                + "' with ID-" + categorySaved.getId() + " was added"), HttpStatus.CREATED);
    }

    @Operation(summary = "Edit one Category")
    @PutMapping("categories")
    public ResponseEntity<CategoryResponse> editCategory(@Valid @RequestBody Category category) {
        Category categoryEdited = categoryService.editCategory(category);
        return new ResponseEntity<>(new CategoryResponse("Category with ID-" + categoryEdited.getId()
                + " was changed to " + categoryEdited.getCategoryName()), HttpStatus.OK);
    }

    @Operation(summary = "Delete one category")
    @DeleteMapping("categories/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new CategoryResponse("Category with ID-" + categoryId
                + " has been deleted"), HttpStatus.OK);
    }
}

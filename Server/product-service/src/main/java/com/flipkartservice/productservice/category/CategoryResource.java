package com.flipkartservice.productservice.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    private CategoryService categoryService;
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/allPanelsWithProducts")
    public ResponseEntity<List<CategoryPanelWithProduct>> getAllCategoryPanelProducts() {
        return new ResponseEntity<>(categoryService.getAllPanelWithProducts(), HttpStatus.OK);
    }
}

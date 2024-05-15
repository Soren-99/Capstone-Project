package soren.rahimi.Capstone.Project.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soren.rahimi.Capstone.Project.dto.CategoryDTO;
import soren.rahimi.Capstone.Project.entities.Category;
import soren.rahimi.Capstone.Project.service.admin.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;
    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.createcategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}

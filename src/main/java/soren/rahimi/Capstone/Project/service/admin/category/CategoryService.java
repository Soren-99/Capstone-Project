package soren.rahimi.Capstone.Project.service.admin.category;

import soren.rahimi.Capstone.Project.dto.CategoryDTO;
import soren.rahimi.Capstone.Project.entities.Category;

import java.util.List;

public interface CategoryService {
    Category createcategory(CategoryDTO categoryDTO);

    List<Category> getAllCategories();
}

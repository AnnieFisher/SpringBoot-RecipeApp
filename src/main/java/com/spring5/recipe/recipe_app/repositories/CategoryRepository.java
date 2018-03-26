package com.spring5.recipe.recipe_app.repositories;

import com.spring5.recipe.recipe_app.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}

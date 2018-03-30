package com.spring5.recipe.recipe_app.repositories;

import com.spring5.recipe.recipe_app.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}

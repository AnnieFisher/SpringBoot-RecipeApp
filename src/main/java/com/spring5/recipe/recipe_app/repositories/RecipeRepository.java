package com.spring5.recipe.recipe_app.repositories;

import com.spring5.recipe.recipe_app.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
}

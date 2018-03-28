package com.spring5.recipe.recipe_app.services;

import com.spring5.recipe.recipe_app.commands.RecipeCommand;
import com.spring5.recipe.recipe_app.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}

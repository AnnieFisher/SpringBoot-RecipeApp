package com.spring5.recipe.recipe_app.services;


import com.spring5.recipe.recipe_app.commands.RecipeCommand;
import com.spring5.recipe.recipe_app.converters.RecipeCommandToRecipe;
import com.spring5.recipe.recipe_app.converters.RecipeToRecipeCommand;
import com.spring5.recipe.recipe_app.model.Recipe;
import com.spring5.recipe.recipe_app.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("LOGGING IN GET RECIPE SERVICE*************");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}

package com.spring5.recipe.recipe_app.converters;

import com.spring5.recipe.recipe_app.commands.RecipeCommand;
import com.spring5.recipe.recipe_app.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
                                 NotesToNotesCommand notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setCategoryList(source.getCategoryList());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));


        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients().forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}

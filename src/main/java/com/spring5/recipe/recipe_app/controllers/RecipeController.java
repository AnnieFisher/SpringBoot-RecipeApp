package com.spring5.recipe.recipe_app.controllers;

import com.spring5.recipe.recipe_app.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Long.valueOf;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/recipeDetails/{id}")
    public String getRecipe(@PathVariable String id,  Model model){
        model.addAttribute("recipe", recipeService.findById(valueOf(id)));
        return "recipe/recipeDetails";
    }
}

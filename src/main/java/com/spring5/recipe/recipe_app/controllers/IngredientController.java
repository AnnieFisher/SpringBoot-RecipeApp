package com.spring5.recipe.recipe_app.controllers;

import com.spring5.recipe.recipe_app.commands.IngredientCommand;
import com.spring5.recipe.recipe_app.services.IngredientService;
import com.spring5.recipe.recipe_app.services.RecipeService;
import com.spring5.recipe.recipe_app.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.valueOf;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String viewSingleIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(valueOf(recipeId), valueOf(id)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(valueOf(recipeId), valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientForm";
    }


    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}

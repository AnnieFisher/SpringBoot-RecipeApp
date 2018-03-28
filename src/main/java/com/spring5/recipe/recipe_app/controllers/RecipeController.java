package com.spring5.recipe.recipe_app.controllers;

import com.spring5.recipe.recipe_app.commands.RecipeCommand;
import com.spring5.recipe.recipe_app.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.valueOf;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String getRecipe(@PathVariable String id,  Model model){
        model.addAttribute("recipe", recipeService.findById(valueOf(id)));
        return "recipe/recipeDetails";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @RequestMapping("/recipe/{id}/update")
    public String update(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(valueOf(id)));
        return "recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" +savedCommand.getId() +"/show" ;
    }


}

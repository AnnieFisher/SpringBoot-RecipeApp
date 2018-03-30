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

    @GetMapping("/recipe/{id}/show")
    public String getRecipe(@PathVariable String id,  Model model){
        model.addAttribute("recipe", recipeService.findById(valueOf(id)));
        return "recipe/recipeDetails";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/update")
    public String update(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(valueOf(id)));
        return "recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" +savedCommand.getId() +"/show" ;
    }

    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable String id){
        recipeService.deleteById(valueOf(id));
        return "redirect:/";
    }


}

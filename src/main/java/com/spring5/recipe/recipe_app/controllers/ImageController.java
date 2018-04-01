package com.spring5.recipe.recipe_app.controllers;

import com.spring5.recipe.recipe_app.commands.RecipeCommand;
import com.spring5.recipe.recipe_app.services.ImageService;
import com.spring5.recipe.recipe_app.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Long.valueOf;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(valueOf(id)));
        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile")MultipartFile file) {
        imageService.saveImageFile(valueOf(id), file);
        return "redirect:/recipe/" +id +"/show";
    }

    @GetMapping("recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(valueOf(id));

        if(recipeCommand.getImage() != null){
            byte[] byteArray = new byte[recipeCommand.getImage().length];

            int i = 0;
            for(Byte wrappedByte : recipeCommand.getImage()){
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
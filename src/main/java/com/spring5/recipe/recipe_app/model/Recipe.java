package com.spring5.recipe.recipe_app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

@Data
@EqualsAndHashCode(exclude = "notes")
@ToString(exclude = "notes")
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @ElementCollection
    private List<String> categoryList = new ArrayList<>();


    @Lob
    private String directions;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
    public void addEnumCategoryToList(Categories cat){
        List<String> enumNames = Stream.of(Categories.values()).map(Categories::name)
                .collect(Collectors.toList());

        for(String category: enumNames){
            if(category.equalsIgnoreCase(valueOf(cat))){
                this.categoryList.add(category);
            }
        }
    }
}

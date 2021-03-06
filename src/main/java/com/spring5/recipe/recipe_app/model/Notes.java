package com.spring5.recipe.recipe_app.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "recipes")
@ToString(exclude = "recipes")

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}

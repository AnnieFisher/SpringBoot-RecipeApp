package com.spring5.recipe.recipe_app.services;

import com.spring5.recipe.recipe_app.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}

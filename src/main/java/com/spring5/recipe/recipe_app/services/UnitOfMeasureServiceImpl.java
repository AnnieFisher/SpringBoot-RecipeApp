package com.spring5.recipe.recipe_app.services;

import com.spring5.recipe.recipe_app.commands.UnitOfMeasureCommand;
import com.spring5.recipe.recipe_app.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.spring5.recipe.recipe_app.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureCommand = unitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(), false)
                .map(unitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}

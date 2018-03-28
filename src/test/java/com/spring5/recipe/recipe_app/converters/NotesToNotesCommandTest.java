package com.spring5.recipe.recipe_app.converters;

import com.spring5.recipe.recipe_app.commands.NotesCommand;
import com.spring5.recipe.recipe_app.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static java.lang.Long.valueOf;

public class NotesToNotesCommandTest {

    public static final Long ID_VALUE = valueOf(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }


    @Test
    public void convert() throws Exception {
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = converter.convert(notes);

        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }
}
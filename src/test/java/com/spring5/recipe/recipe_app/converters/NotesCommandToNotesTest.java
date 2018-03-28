package com.spring5.recipe.recipe_app.converters;

import com.spring5.recipe.recipe_app.commands.NotesCommand;
import com.spring5.recipe.recipe_app.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Long.valueOf;
import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Long ID_VALUE = valueOf(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes = converter.convert(notesCommand);

        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}
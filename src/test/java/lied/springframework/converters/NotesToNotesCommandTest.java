package lied.springframework.converters;

import lied.springframework.commands.NotesCommand;
import lied.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long NOTES_ID = 1L;
    private static final String RECIPE_NOTES = "some notes";

    NotesToNotesCommand notesCommandConverter;

    @Before
    public void setUp() throws Exception {
        notesCommandConverter = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(notesCommandConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(notesCommandConverter.convert(new Notes()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command = notesCommandConverter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(NOTES_ID, command.getId());
        assertEquals(RECIPE_NOTES, command.getNotes());
    }

}
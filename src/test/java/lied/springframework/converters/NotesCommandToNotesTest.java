package lied.springframework.converters;

import lied.springframework.commands.NotesCommand;
import lied.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long NOTES_ID = 1L;
    private static final String RECIPE_NOTES = "some other notes";

    NotesCommandToNotes notesConverter;

    @Before
    public void setUp() throws Exception {
        notesConverter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(notesConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(notesConverter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(NOTES_ID);
        command.setNotes(RECIPE_NOTES);

        //when
        Notes notes = notesConverter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(NOTES_ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}
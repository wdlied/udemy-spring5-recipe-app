package lied.springframework.converters;

import lied.springframework.commands.UnitOfMeasureCommand;
import lied.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long UOM_ID = 1L;
    private static final String DESCRIPTION = "description";

    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(uomConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(uomConverter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand command = uomConverter.convert(uom);

        //then
        assertNotNull(command);
        assertEquals(UOM_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }

}
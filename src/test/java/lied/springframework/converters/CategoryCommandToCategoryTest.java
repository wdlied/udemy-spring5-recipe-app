package lied.springframework.converters;

import lied.springframework.commands.CategoryCommand;
import lied.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final Long CATEGORY_ID = 1L;
    private static final String DESCRIPTION = "description";

    CategoryCommandToCategory categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = categoryConverter.convert(categoryCommand);

        //then
        assertNotNull(category);
        assertEquals(CATEGORY_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}
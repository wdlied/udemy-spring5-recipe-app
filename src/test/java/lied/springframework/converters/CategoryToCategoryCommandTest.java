package lied.springframework.converters;

import lied.springframework.commands.CategoryCommand;
import lied.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long CATEGORY_ID = 1L;
    private static final String DESCRIPTION = "description";

    CategoryToCategoryCommand categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        categoryConverter.convert(null);
    }

    @Test
    public void testEmptyObject() throws Exception {
        categoryConverter.convert(new Category());
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand command = categoryConverter.convert(category);

        //then
        assertNotNull(command);
        assertEquals(CATEGORY_ID, command.getId());
        assertEquals(DESCRIPTION, command.getDescription());
    }

}
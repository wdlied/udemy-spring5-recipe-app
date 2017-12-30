package lied.springframework.converters;

import lied.springframework.commands.IngredientCommand;
import lied.springframework.domain.Ingredient;
import lied.springframework.domain.Recipe;
import lied.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long INGREDIENT_ID = new Long(1L);
    private static final Long UOM_ID = new Long(2L);

    IngredientToIngredientCommand ingredientCommandConverter;

    @Before
    public void setUp() throws Exception {
        ingredientCommandConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(ingredientCommandConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(ingredientCommandConverter.convert(new Ingredient()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUnitOfMeasure(uom);

        //when
        IngredientCommand command = ingredientCommandConverter.convert(ingredient);

        //then
        assertNotNull(command);
        assertNotNull(command.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(UOM_ID, command.getUnitOfMeasure().getId());
    }

}
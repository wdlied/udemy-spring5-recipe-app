package lied.springframework.converters;

import lied.springframework.commands.CategoryCommand;
import lied.springframework.commands.IngredientCommand;
import lied.springframework.commands.NotesCommand;
import lied.springframework.commands.RecipeCommand;
import lied.springframework.domain.Difficulty;
import lied.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.xmlunit.diff.Diff;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final Long RECIPE_ID = new Long(1);
    private static final Integer COOK_TIME = new Integer(5);
    private static final Integer PREP_TIME = Integer.valueOf("7");
    private static final String DESCRIPTION = "My Recipe";
    private static final String DIRECTIONS = "My Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = Integer.valueOf("9");
    private static final String SOURCE = "My Source";
    private static final String URL = "Some URL";
    private static final Long CATEGORY_ID1 = 1L;
    private static final Long CATEGORY_ID2 = 2L;
    private static final Long INGREDIENT_ID1 = 3L;
    private static final Long INGREDIENT_ID2 = 4L;
    private static final Long NOTES_ID = 5L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        converter.convert(null);
    }

    @Test
    public void testEmptyObject() throws Exception {
        converter.convert(new RecipeCommand());
    }

    @Test
    public void testConvert() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setDifficulty(DIFFICULTY);
        command.setDirections(DIRECTIONS);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        command.setNotes(notes);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CATEGORY_ID1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CATEGORY_ID2);

        command.getCategories().add(category1);
        command.getCategories().add(category2);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(INGREDIENT_ID1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGREDIENT_ID2);

        command.getIngredients().add(ingredient1);
        command.getIngredients().add(ingredient2);

        //when
        Recipe recipe = converter.convert(command);

        //then
        assertNotNull(recipe);
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SOURCE, recipe.getSource());

        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

    }
}
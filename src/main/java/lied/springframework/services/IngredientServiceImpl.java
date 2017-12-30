package lied.springframework.services;

import lied.springframework.commands.IngredientCommand;
import lied.springframework.converters.IngredientCommandToIngredient;
import lied.springframework.converters.IngredientToIngredientCommand;
import lied.springframework.domain.Ingredient;
import lied.springframework.domain.Recipe;
import lied.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (!optionalRecipe.isPresent()) {
            //error handling
            log.error("recipe id not found: " + recipeId);

        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

//        Optional<IngredientCommand> optionalIngredientCommand = Optional.empty();
//
//        for (Ingredient ingredient : recipe.getIngredients()) {
//            if (ingredient.getId() == ingredientId) {
//                IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);
//                optionalIngredientCommand = Optional.of(command);
//                break;
//            }
//        }



        if(!optionalIngredientCommand.isPresent()) {
            //error handling
            log.error("ingredient id not found: " + ingredientId);
        }

        return optionalIngredientCommand.get();
    }
}

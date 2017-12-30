package lied.springframework.services;

import lied.springframework.commands.RecipeCommand;
import lied.springframework.domain.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(long recipeId);

    RecipeCommand findCommandById(long recipeId);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long idToDelete);
}

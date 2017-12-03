package lied.springframework.repositories;

import lied.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository  extends CrudRepository<Recipe, Long> {
}

package lied.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private BigDecimal amount;
    private String description;
    private UnitOfMeasureCommand unitOfMeasure;
    private Long recipeId;
}

package coffeemachine.models.ingredients;

import coffeemachine.enums.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FillIngredient {

    private IngredientType ingredientType;
    private Integer quantity;
}

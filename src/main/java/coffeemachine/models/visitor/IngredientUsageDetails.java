package coffeemachine.models.visitor;

import coffeemachine.enums.IngredientUsageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientUsageDetails {
    private int requiredQuantity;
    private IngredientUsageType ingredientUsageType;
}

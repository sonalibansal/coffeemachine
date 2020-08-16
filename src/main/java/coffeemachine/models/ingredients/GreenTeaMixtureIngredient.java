package coffeemachine.models.ingredients;

import coffeemachine.enums.CapacityUnit;
import coffeemachine.enums.IngredientType;
import coffeemachine.models.Quantity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GreenTeaMixtureIngredient extends Ingredient {

    public GreenTeaMixtureIngredient() {
        super(IngredientType.GREEN_TEA_MIXTURE, CapacityUnit.WEIGHT_IN_GRAM);
    }

    public GreenTeaMixtureIngredient(final String name,
                                     final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.GREEN_TEA_MIXTURE, name, quantity, maxCapacity,
              CapacityUnit.WEIGHT_IN_GRAM);
    }
}

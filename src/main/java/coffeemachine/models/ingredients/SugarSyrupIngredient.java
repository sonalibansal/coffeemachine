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
public class SugarSyrupIngredient extends Ingredient {

    public SugarSyrupIngredient() {
        super(IngredientType.SUGAR_SYRUP, CapacityUnit.MILLILITER);
    }

    public SugarSyrupIngredient(final String name,
                                final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.SUGAR_SYRUP, name, quantity, maxCapacity, CapacityUnit.MILLILITER);
    }
}

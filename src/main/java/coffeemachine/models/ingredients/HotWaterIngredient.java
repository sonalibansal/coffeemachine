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
public class HotWaterIngredient extends Ingredient {

    public HotWaterIngredient() {
        super(IngredientType.HOT_WATER, CapacityUnit.MILLILITER);
    }

    public HotWaterIngredient(final String name,
                              final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.HOT_WATER, name, quantity, maxCapacity, CapacityUnit.MILLILITER);
    }

}

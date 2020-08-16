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
public class HotMilkIngredient extends Ingredient {

    public HotMilkIngredient() {
        super(IngredientType.HOT_MILK, CapacityUnit.MILLILITER);
    }

    public HotMilkIngredient(final String name,
                             final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.HOT_MILK, name, quantity, maxCapacity, CapacityUnit.MILLILITER);
    }
}

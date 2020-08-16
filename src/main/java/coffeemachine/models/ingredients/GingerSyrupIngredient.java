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
public class GingerSyrupIngredient extends Ingredient {

    public GingerSyrupIngredient() {
        super(IngredientType.GINGER_SYRUP, CapacityUnit.MILLILITER);
    }

    public GingerSyrupIngredient(final String name,
                                 final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.GINGER_SYRUP, name, quantity, maxCapacity, CapacityUnit.MILLILITER);
    }
}

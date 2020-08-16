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
public class TeaLeavesPowderIngredient extends Ingredient {

    public TeaLeavesPowderIngredient() {
        super(IngredientType.TEA_LEAVES_POWDER, CapacityUnit.WEIGHT_IN_GRAM);
    }

    public TeaLeavesPowderIngredient(final String name,
                                     final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.TEA_LEAVES_POWDER, name, quantity,
              maxCapacity, CapacityUnit.WEIGHT_IN_GRAM);
    }
}

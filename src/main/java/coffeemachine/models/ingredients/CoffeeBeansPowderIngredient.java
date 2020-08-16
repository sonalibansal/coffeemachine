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
public class CoffeeBeansPowderIngredient extends Ingredient {

    public CoffeeBeansPowderIngredient() {
        super(IngredientType.COFFEE_BEANS_POWDER, CapacityUnit.WEIGHT_IN_GRAM);
    }

    public CoffeeBeansPowderIngredient(final String name,
                                       final Quantity quantity, final Integer maxCapacity) {
        super(IngredientType.COFFEE_BEANS_POWDER, name, quantity, maxCapacity,
              CapacityUnit.WEIGHT_IN_GRAM);
    }
}

package coffeemachine.models.beverages;

import coffeemachine.enums.BeverageType;
import coffeemachine.enums.IngredientType;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HotCoffeeBeverage extends Beverage {

    public HotCoffeeBeverage() {
        super(BeverageType.HOT_COFFEE);
    }

    public HotCoffeeBeverage(final String name,
                             final Map<IngredientType, Integer> ingredientsMap) {
        super(BeverageType.HOT_COFFEE, name, ingredientsMap);
    }
}

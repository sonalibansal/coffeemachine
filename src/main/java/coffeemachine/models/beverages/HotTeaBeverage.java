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
public class HotTeaBeverage extends Beverage {

    public HotTeaBeverage() {
        super(BeverageType.HOT_TEA);
    }

    public HotTeaBeverage(final String name,
                          final Map<IngredientType, Integer> ingredientsMap) {
        super(BeverageType.HOT_TEA, name, ingredientsMap);
    }
}

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
public class GingerTeaBeverage extends Beverage {

    public GingerTeaBeverage() {
        super(BeverageType.GINGER_TEA);
    }

    public GingerTeaBeverage(final String name,
                             final Map<IngredientType, Integer> ingredientsMap) {
        super(BeverageType.GINGER_TEA, name, ingredientsMap);
    }
}

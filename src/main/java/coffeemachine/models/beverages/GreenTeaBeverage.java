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
public class GreenTeaBeverage extends Beverage {

    public GreenTeaBeverage() {
        super(BeverageType.GREEN_TEA);
    }

    public GreenTeaBeverage(final String name,
                            final Map<IngredientType, Integer> ingredientsMap) {
        super(BeverageType.GREEN_TEA, name, ingredientsMap);
    }
}

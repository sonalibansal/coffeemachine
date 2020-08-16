package coffeemachine.models.beverages;

import coffeemachine.enums.BeverageType;
import coffeemachine.enums.IngredientType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HotTeaBeverage.class, name = BeverageType.HOT_TEA_TEXT),
        @JsonSubTypes.Type(value = HotCoffeeBeverage.class, name = BeverageType.HOT_COFFEE_TEXT),
        @JsonSubTypes.Type(value = GingerTeaBeverage.class, name = BeverageType.GINGER_TEA_TEXT),
        @JsonSubTypes.Type(value = BlackTeaBeverage.class, name = BeverageType.BLACK_TEA_TEXT),
        @JsonSubTypes.Type(value = GreenTeaBeverage.class, name = BeverageType.GREEN_TEA_TEXT)
})
public abstract class Beverage {

    private BeverageType type;
    private String name;
    private Map<IngredientType, Integer> ingredients;

    Beverage(BeverageType type) {
        this.type = type;
    }
}

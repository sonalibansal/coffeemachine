package coffeemachine.models.ingredients;

import static coffeemachine.enums.IngredientType.GREEN_TEA_MIXTURE_TEXT;

import coffeemachine.enums.CapacityUnit;
import coffeemachine.enums.IngredientType;
import coffeemachine.models.Quantity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HotWaterIngredient.class, name = IngredientType.HOT_WATER_TEXT),
        @JsonSubTypes.Type(value = HotMilkIngredient.class, name = IngredientType.HOT_MILK_TEXT),
        @JsonSubTypes.Type(value = GingerSyrupIngredient.class, name = IngredientType.GINGER_SYRUP_TEXT),
        @JsonSubTypes.Type(value = SugarSyrupIngredient.class, name = IngredientType.SUGAR_SYRUP_TEXT),
        @JsonSubTypes.Type(value = TeaLeavesPowderIngredient.class, name = IngredientType.TEA_LEAVES_POWDER_TEXT),
        @JsonSubTypes.Type(value = CoffeeBeansPowderIngredient.class, name = IngredientType.COFFEE_BEANS_POWDER_TEXT),
        @JsonSubTypes.Type(value = GreenTeaMixtureIngredient.class, name = GREEN_TEA_MIXTURE_TEXT)
})
public abstract class Ingredient {

    private IngredientType type;
    private String name;
    private Quantity quantity;
    private Integer maxCapacity;
    private CapacityUnit capacityUnit;

    Ingredient(IngredientType type, CapacityUnit capacityUnit) {
        this.type = type;
        this.capacityUnit = capacityUnit;
    }
}

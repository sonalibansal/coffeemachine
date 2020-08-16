package coffeemachine.models;

import coffeemachine.models.beverages.Beverage;
import coffeemachine.models.ingredients.Ingredient;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This entity denotes the coffee configuration that will be read while configuring the coffee machine
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeMachineConfiguration {
    private Map<String, Integer> outlets;
    private List<Ingredient> total_items_quantity;
    private List<Beverage> beverages;
}

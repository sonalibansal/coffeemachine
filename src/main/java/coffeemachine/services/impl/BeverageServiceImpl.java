package coffeemachine.services.impl;

import coffeemachine.models.beverages.Beverage;
import coffeemachine.models.ingredients.FillIngredient;
import coffeemachine.models.ingredients.Ingredient;
import coffeemachine.services.BeverageService;
import coffeemachine.visitors.BeverageCreationVisitor;
import coffeemachine.visitors.IngredientUsageVisitor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeverageServiceImpl implements BeverageService {

    private final BeverageCreationVisitor beverageCreationVisitor;
    private final IngredientUsageVisitor ingredientUsageVisitor;

    public BeverageServiceImpl(List<Beverage> beverages, List<Ingredient> ingredients) {
        ingredientUsageVisitor = new IngredientUsageVisitor(ingredients);
        beverageCreationVisitor = new BeverageCreationVisitor(ingredientUsageVisitor, beverages);
    }

    @Override
    public boolean makeBeverage(Beverage beverage) {
        if (beverageCreationVisitor.canCreateBeverage(beverage.getType())) {
            log.info("Beverage is made successfully");
            return true;
        }
        return false;
    }

    @Override
    public boolean fillIngredient(FillIngredient ingredient) {
        return ingredientUsageVisitor
                .addIngredient(ingredient.getIngredientType(), ingredient.getQuantity());
    }
}

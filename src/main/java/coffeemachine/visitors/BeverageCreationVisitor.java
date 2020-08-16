package coffeemachine.visitors;

import coffeemachine.enums.BeverageType;
import coffeemachine.enums.IngredientType;
import coffeemachine.enums.IngredientUsageType;
import coffeemachine.exceptions.CoffeeMachineException;
import coffeemachine.exceptions.ResponseCode;
import coffeemachine.models.beverages.Beverage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeverageCreationVisitor {

    private final Map<BeverageType, Beverage> beverageMap;
    private final IngredientUsageVisitor ingredientUsageVisitor;

    public BeverageCreationVisitor(IngredientUsageVisitor ingredientUsageVisitor,
                                   List<Beverage> beverageList) {
        this.ingredientUsageVisitor = ingredientUsageVisitor;
        beverageMap = new HashMap<>();
        for (Beverage beverage : beverageList) {
            beverageMap.put(beverage.getType(), beverage);
        }
    }

    public boolean canCreateBeverage(BeverageType type) {
        return createBeverage(beverageMap.get(type));
    }

    /**
     * This method checks whether a particular beverage can be made or not.
     * It checks the ingredients availability by taking a synchronised lock on the ingredient
     * to handle concurrent request.
     * If all the ingredients are available, then it returns true.
     * Otherwise it roll backs the operations on ingredients and throws an exception
     *
     * @param beverage
     * @return true or false
     */
    private boolean createBeverage(Beverage beverage) {
        Map<IngredientType, Integer> usedIngredient = new HashMap<>();
        for (Map.Entry<IngredientType, Integer> ingredientTypeEntry : beverage.getIngredients()
                .entrySet()) {
            int requiredQuantity = ingredientTypeEntry.getValue();
            boolean isIngredientAvailable = ingredientUsageVisitor
                    .performOperation(ingredientTypeEntry.getKey(),
                                      requiredQuantity, IngredientUsageType.LOCK);
            if (isIngredientAvailable) {
                usedIngredient.put(ingredientTypeEntry.getKey(), ingredientTypeEntry.getValue());
                continue;
            }
            rollBackUsedIngredient(usedIngredient);
            throw CoffeeMachineException
                    .create("Insufficient ingredients", ResponseCode.LOW_INGREDIENTS);
        }
        return true;
    }

    private void rollBackUsedIngredient(Map<IngredientType, Integer> usedIngredient) {
        for (Map.Entry<IngredientType, Integer> ingredientTypeEntry : usedIngredient.entrySet()) {
            ingredientUsageVisitor
                    .performOperation(ingredientTypeEntry.getKey(), ingredientTypeEntry.getValue(),
                                      IngredientUsageType.RELEASE);
        }
    }
}


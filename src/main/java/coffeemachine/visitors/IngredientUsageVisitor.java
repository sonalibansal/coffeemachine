package coffeemachine.visitors;

import coffeemachine.enums.IngredientType;
import coffeemachine.enums.IngredientUsageType;
import coffeemachine.models.ingredients.Ingredient;
import coffeemachine.models.visitor.IngredientUsageDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IngredientUsageVisitor implements
        IngredientType.IngredientVisitor<Boolean, IngredientUsageDetails> {

    private final Map<IngredientType, Ingredient> ingredientMap;

    public IngredientUsageVisitor(List<Ingredient> ingredientList) {
        ingredientMap = new HashMap<>();
        for (Ingredient ingredient : ingredientList) {
            ingredientMap.put(ingredient.getType(), ingredient);
        }
    }

    /**
     * This method is used to refill the ingredient with some quantity.
     * There can be 2 cases here:
     * 1. If the quantity given by the user is less than the maxCapacity of the container,
     * it will increase the current quantity by the given amount
     * 2. Otherwise, it will fill the ingredient according to the maxCapacity of the container
     *
     * @param ingredientType
     * @param quantityRequired
     * @return
     */
    public boolean addIngredient(IngredientType ingredientType, int quantityRequired) {
        return performOperation(ingredientType, quantityRequired, IngredientUsageType.RELEASE);
    }

    boolean performOperation(IngredientType type, int quantityRequired,
                             IngredientUsageType ingredientUsageType) {
        return type.accept(this, IngredientUsageDetails.builder()
                .ingredientUsageType(ingredientUsageType)
                .requiredQuantity(quantityRequired)
                .build()
        );
    }

    @Override
    public synchronized Boolean visitHotWater(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.HOT_WATER, data);
    }

    @Override
    public synchronized Boolean visitHotMilk(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.HOT_MILK, data);
    }

    @Override
    public synchronized Boolean visitGingerSyrup(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.GINGER_SYRUP, data);
    }

    @Override
    public synchronized Boolean visitSugarSyrup(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.SUGAR_SYRUP, data);
    }

    @Override
    public synchronized Boolean visitTeaLeavesPowder(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.TEA_LEAVES_POWDER, data);
    }

    @Override
    public synchronized Boolean visitCoffeeBeansPowder(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.COFFEE_BEANS_POWDER, data);
    }

    @Override
    public synchronized Boolean visitGreenTeaMixture(IngredientUsageDetails data) {
        return performIngredientOperation(IngredientType.GREEN_TEA_MIXTURE, data);
    }

    private Boolean performIngredientOperation(IngredientType ingredientType,
                                               IngredientUsageDetails data) {
        return data.getIngredientUsageType()
                .accept(new IngredientUsageType.IngredientUsageTypeVisitor<Boolean, Void>() {
                    @Override
                    public Boolean visitLock(Void voidData) {
                        Ingredient ingredient = ingredientMap.get(ingredientType);
                        if (ingredient.getQuantity().getCurrentQuantity() >= data
                                .getRequiredQuantity()) {
                            ingredient.getQuantity().setCurrentQuantity(
                                    ingredient.getQuantity().getCurrentQuantity() - data
                                            .getRequiredQuantity());
                            log.info("Quantity : {} deducted successfully for ingredient type : {}",
                                     data.getRequiredQuantity(), ingredientType);
                            return true;
                        }
                        log.info("Quantity deduction not done for ingredient type : {}",
                                 ingredientType);
                        return false;
                    }

                    @Override
                    public Boolean visitRelease(Void voidData) {
                        log.info(
                                "Quantity before addition : {} added successfully for ingredient type : {}",
                                data.getRequiredQuantity(), ingredientType);
                        addIngredientQuantity(ingredientMap.get(ingredientType),
                                              data.getRequiredQuantity());
                        log.info("Quantity : {} added successfully for ingredient type : {}",
                                 ingredientMap.get(ingredientType).getQuantity()
                                         .getCurrentQuantity(), ingredientType);
                        return true;
                    }
                }, null);
    }

    private void addIngredientQuantity(Ingredient ingredient, int quantityToAdd) {
        int currentQuantity = ingredient.getQuantity().getCurrentQuantity();
        int now = currentQuantity + quantityToAdd;
        ingredient.getQuantity().setCurrentQuantity(Math.min(now, ingredient.getMaxCapacity()));
    }
}

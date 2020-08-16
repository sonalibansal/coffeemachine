package coffeemachine.services;


import coffeemachine.enums.IngredientType;
import coffeemachine.exceptions.CoffeeMachineException;
import coffeemachine.exceptions.ResponseCode;
import coffeemachine.models.Quantity;
import coffeemachine.models.beverages.Beverage;
import coffeemachine.models.beverages.HotCoffeeBeverage;
import coffeemachine.models.beverages.HotTeaBeverage;
import coffeemachine.models.ingredients.CoffeeBeansPowderIngredient;
import coffeemachine.models.ingredients.FillIngredient;
import coffeemachine.models.ingredients.GingerSyrupIngredient;
import coffeemachine.models.ingredients.HotMilkIngredient;
import coffeemachine.models.ingredients.HotWaterIngredient;
import coffeemachine.models.ingredients.Ingredient;
import coffeemachine.models.ingredients.SugarSyrupIngredient;
import coffeemachine.models.ingredients.TeaLeavesPowderIngredient;
import coffeemachine.services.impl.BeverageServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeverageServiceImplTest {

    private BeverageService beverageService;
    private List<Beverage> beverageList = new ArrayList<>();
    private List<Ingredient> ingredients = new ArrayList<>();

    @Before
    public void setUp() {
        Map<IngredientType, Integer> coffeeIngredientsMap = new HashMap<>();
        coffeeIngredientsMap.put(IngredientType.HOT_WATER, 300);
        coffeeIngredientsMap.put(IngredientType.HOT_MILK, 100);
        coffeeIngredientsMap.put(IngredientType.SUGAR_SYRUP, 50);
        coffeeIngredientsMap.put(IngredientType.COFFEE_BEANS_POWDER, 2);
        beverageList.add(new HotCoffeeBeverage("hot_coffee", coffeeIngredientsMap));


        Map<IngredientType, Integer> teaIngredientsMap = new HashMap<>();
        teaIngredientsMap.put(IngredientType.HOT_WATER, 200);
        teaIngredientsMap.put(IngredientType.HOT_MILK, 100);
        teaIngredientsMap.put(IngredientType.SUGAR_SYRUP, 10);
        teaIngredientsMap.put(IngredientType.TEA_LEAVES_POWDER, 2);
        beverageList.add(new HotTeaBeverage("hot_tea", teaIngredientsMap));

        ingredients.add(new HotWaterIngredient("hot_water", Quantity.builder()
                .currentQuantity(1000)
                .minQuantity(10)
                .build(), 2000));
        ingredients.add(new HotMilkIngredient("hot_milk", Quantity.builder()
                .currentQuantity(1000)
                .minQuantity(10)
                .build(), 2000));
        ingredients.add(new GingerSyrupIngredient("ginger_syrup", Quantity.builder()
                .currentQuantity(100)
                .minQuantity(10)
                .build(), 500));
        ingredients.add(new SugarSyrupIngredient("sugar_syrup", Quantity.builder()
                .currentQuantity(100)
                .minQuantity(10)
                .build(), 500));
        ingredients.add(new TeaLeavesPowderIngredient("tea_leaves_powder", Quantity.builder()
                .currentQuantity(100)
                .minQuantity(5)
                .build(), 250));
        ingredients.add(new CoffeeBeansPowderIngredient("coffee_beans_powder", Quantity.builder()
                .currentQuantity(50)
                .minQuantity(5)
                .build(), 250));

        beverageService = new BeverageServiceImpl(beverageList, ingredients);

    }

    @Test
    public void testMakeBeverageSuccessfully() {
        List<Ingredient> modifiedIngredients = new ArrayList<>();
        modifiedIngredients.add(new HotWaterIngredient("hot_water", Quantity.builder()
                .currentQuantity(700)
                .minQuantity(10)
                .build(), 2000));
        modifiedIngredients.add(new HotMilkIngredient("hot_milk", Quantity.builder()
                .currentQuantity(900)
                .minQuantity(10)
                .build(), 2000));
        modifiedIngredients.add(new GingerSyrupIngredient("ginger_syrup", Quantity.builder()
                .currentQuantity(100)
                .minQuantity(10)
                .build(), 500));
        modifiedIngredients.add(new SugarSyrupIngredient("sugar_syrup", Quantity.builder()
                .currentQuantity(50)
                .minQuantity(10)
                .build(), 500));
        modifiedIngredients
                .add(new TeaLeavesPowderIngredient("tea_leaves_powder", Quantity.builder()
                        .currentQuantity(100)
                        .minQuantity(5)
                        .build(), 250));
        modifiedIngredients
                .add(new CoffeeBeansPowderIngredient("coffee_beans_powder", Quantity.builder()
                        .currentQuantity(48)
                        .minQuantity(5)
                        .build(), 250));
        Assert.assertTrue(beverageService.makeBeverage(new HotCoffeeBeverage()));
        Assert.assertEquals(modifiedIngredients, ingredients);
    }

    @Test
    public void testMakeBeverageNotSuccessful() {
        ingredients.get(5).setQuantity(Quantity.builder()
                                               .currentQuantity(1)
                                               .minQuantity(5)
                                               .build());
        List<Ingredient> modifiedIngredients = new ArrayList<>();
        modifiedIngredients.add(new HotWaterIngredient("hot_water", Quantity.builder()
                .currentQuantity(1000)
                .minQuantity(10)
                .build(), 2000));
        modifiedIngredients.add(new HotMilkIngredient("hot_milk", Quantity.builder()
                .currentQuantity(1000)
                .minQuantity(10)
                .build(), 2000));
        modifiedIngredients.add(new GingerSyrupIngredient("ginger_syrup", Quantity.builder()
                .currentQuantity(100)
                .minQuantity(10)
                .build(), 500));
        modifiedIngredients.add(new SugarSyrupIngredient("sugar_syrup", Quantity.builder()
                .currentQuantity(100)
                .minQuantity(10)
                .build(), 500));
        modifiedIngredients
                .add(new TeaLeavesPowderIngredient("tea_leaves_powder", Quantity.builder()
                        .currentQuantity(100)
                        .minQuantity(5)
                        .build(), 250));
        modifiedIngredients
                .add(new CoffeeBeansPowderIngredient("coffee_beans_powder", Quantity.builder()
                        .currentQuantity(1)
                        .minQuantity(5)
                        .build(), 250));

        try {
            beverageService.makeBeverage(new HotCoffeeBeverage());
            TestCase.fail("Should have thrown an exception");
        } catch (CoffeeMachineException ce) {
            Assert.assertEquals(ResponseCode.LOW_INGREDIENTS, ce.getResponseCode());
            Assert.assertEquals("Insufficient ingredients", ce.getMessage());
            Assert.assertEquals(modifiedIngredients, ingredients);
        }
    }

    @Test
    public void testFillIngredientForQuantityGreaterThanMaximumCapacity() {
        Assert.assertTrue(beverageService.fillIngredient(FillIngredient.builder()
                                                                 .ingredientType(
                                                                         IngredientType.HOT_MILK)
                                                                 .quantity(1100)
                                                                 .build()));
        Ingredient ingredient = ingredients.get(1);
        Assert.assertEquals(ingredient.getMaxCapacity(),
                            ingredient.getQuantity().getCurrentQuantity());
    }

    @Test
    public void testFillIngredientForQuantityEqualToMaximumCapacity() {
        Assert.assertTrue(beverageService.fillIngredient(FillIngredient.builder()
                                                                 .ingredientType(
                                                                         IngredientType.HOT_MILK)
                                                                 .quantity(1000)
                                                                 .build()));
        Ingredient ingredient = ingredients.get(1);
        Assert.assertEquals(ingredient.getMaxCapacity(),
                            ingredient.getQuantity().getCurrentQuantity());
    }

    @Test
    public void testFillIngredientForQuantityLessThanMaximumCapacity() {
        Assert.assertTrue(beverageService.fillIngredient(FillIngredient.builder()
                                                                 .ingredientType(
                                                                         IngredientType.HOT_MILK)
                                                                 .quantity(500)
                                                                 .build()));
        Ingredient ingredient = ingredients.get(1);
        Assert.assertEquals(new Integer(1500), ingredient.getQuantity().getCurrentQuantity());
    }
}

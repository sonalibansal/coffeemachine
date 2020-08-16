package coffeemachine.services;

import coffeemachine.models.beverages.Beverage;
import coffeemachine.models.ingredients.FillIngredient;

public interface BeverageService {

    boolean makeBeverage(Beverage beverage);

    boolean fillIngredient(FillIngredient ingredient);


}

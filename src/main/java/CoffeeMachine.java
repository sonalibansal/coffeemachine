import static java.lang.System.out;

import coffeemachine.enums.BeverageType;
import coffeemachine.enums.IngredientType;
import coffeemachine.enums.InstructionType;
import coffeemachine.exceptions.CoffeeMachineException;
import coffeemachine.exceptions.ResponseCode;
import coffeemachine.models.CoffeeMachineConfiguration;
import coffeemachine.models.beverages.Beverage;
import coffeemachine.models.ingredients.FillIngredient;
import coffeemachine.models.ingredients.Ingredient;
import coffeemachine.services.BeverageService;
import coffeemachine.services.impl.BeverageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoffeeMachine {
    private CoffeeMachineConfiguration configuration;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Scanner scan = new Scanner(System.in);

    private BeverageService beverageService;

    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<Beverage> beverageOptions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        CoffeeMachine coffeeMachineMain = new CoffeeMachine();
        coffeeMachineMain.startMain();
    }

    private void startMain() throws IOException {
        configureCoffeeMachine();
        start();
    }

    /**
     * This method configures the coffee machine by reading the config from a json file
     *
     * @throws IOException
     */
    private void configureCoffeeMachine() throws IOException {
        InputStream inputStream = ClassLoader
                .getSystemResourceAsStream("coffee_machine_config.json");
        configuration = objectMapper.readValue(inputStream, CoffeeMachineConfiguration.class);

        validateConfigJson();

        //Add into the in memory list from config file
        ingredientList.addAll(configuration.getTotal_items_quantity());
        beverageOptions.addAll(configuration.getBeverages());
        log.info("Coffee machine configuration is ready with the following : {}", configuration);

        beverageService = new BeverageServiceImpl(beverageOptions, ingredientList);
    }

    /**
     * This method is used to validate the json config.
     * Right now it is used to check the duplicate json objects for any beverage and ingredients
     */
    private void validateConfigJson() {
        Set<IngredientType> ingredientTypeSet = new HashSet<>();
        configuration.getTotal_items_quantity().forEach(ingredient -> {
            ingredientTypeSet.add(ingredient.getType());
        });

        if (ingredientTypeSet.size() != configuration.getTotal_items_quantity().size()) {
            throw CoffeeMachineException
                    .create("Duplicate ingredients ", ResponseCode.DUPLICATE_ENTITIES);
        }

        Set<BeverageType> beveragesSet = new HashSet<>();
        configuration.getBeverages().forEach(beverage -> {
            beveragesSet.add(beverage.getType());
        });
        if (beveragesSet.size() != configuration.getBeverages().size()) {
            throw CoffeeMachineException
                    .create("Duplicate beverages ", ResponseCode.DUPLICATE_ENTITIES);
        }
    }

    /**
     * This is used to show the menu to the user when he clicks on the start button
     * It supports 2 instructions right now
     * 1. Make Beverage
     * 2. Refill Ingredients
     */
    private void start() {
        out.println("\n ------------------ ");
        out.println("|   Select Type:   |\n ------------------ \n|");
        out.println("| 1. Make Beverage |");
        out.println("| 2. Refill Ingredients |");

        int itemIndex = scan.nextInt();
        switch (itemIndex) {
            case 1:
                executionInstructions(InstructionType.MAKE_BEVERAGE);
                break;
            case 2:
                executionInstructions(InstructionType.REFILL_INGREDIENT);
                break;
            default:
                throw CoffeeMachineException.create("Instruction not supported",
                                                    ResponseCode.INSTRUCTIONS_NOT_SUPPORTED);
        }
    }

    private void executionInstructions(final InstructionType instructionType) {
        switch (instructionType) {
            case MAKE_BEVERAGE:
                makeBeverage(beverageOptions);
                break;
            case REFILL_INGREDIENT:
                fillIngredient(ingredientList);
                break;
            default:
                throw CoffeeMachineException.create("Instruction not supported",
                                                    ResponseCode.INSTRUCTIONS_NOT_SUPPORTED);
        }
    }

    /**
     * Menu driven function to show the beverage menu to the user.
     *
     * @param beverageOptions
     */
    private void makeBeverage(List<Beverage> beverageOptions) {
        out.println("\n ------------------ ");
        out.println("|   Select Beverage Type:   |\n ------------------ \n|");

        int itemsSize = beverageOptions.size();
        for (int i = 0; i < itemsSize; i++) {
            out.println("|" + i + ". " + beverageOptions.get(i).getName() + "|");
        }
        int itemIndex = scan.nextInt();
        Beverage item;
        try {
            item = beverageOptions.get(itemIndex);
        } catch (Exception e) {
            log.error("Item not present");
            throw CoffeeMachineException
                    .create("Item not present", ResponseCode.BEVERAGE_NOT_SUPPORTED);
        }
        beverageService.makeBeverage(item);
    }

    /**
     * Menu driven function to show the ingredients options to the user to refill the user
     *
     * @param ingredients
     */
    private void fillIngredient(List<Ingredient> ingredients) {
        out.println("\n ------------------ ");
        out.println("|   Select Ingredient Type:   |\n ------------------ \n|");

        int itemsSize = ingredients.size();
        for (int i = 0; i < itemsSize; i++) {
            out.println("|" + i + ". " + ingredients.get(i).getType() + "|");
        }

        // It takes 2 inputs one is the item number and the quantity to refill the ingredient slot
        int itemIndex = scan.nextInt();
        int quantity = scan.nextInt();
        Ingredient item;
        try {
            item = ingredients.get(itemIndex);
            beverageService.fillIngredient(FillIngredient.builder()
                                                   .ingredientType(item.getType())
                                                   .quantity(quantity)
                                                   .build());
        } catch (Exception e) {
            log.error("Item not present");
            throw CoffeeMachineException
                    .create("Item not present", ResponseCode.BEVERAGE_NOT_SUPPORTED);
        }
    }
}

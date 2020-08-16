package coffeemachine.enums;

public enum IngredientType {
    HOT_WATER {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitHotWater(data);
        }
    },
    HOT_MILK {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitHotMilk(data);
        }
    },
    GINGER_SYRUP {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitGingerSyrup(data);
        }
    },
    SUGAR_SYRUP {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitSugarSyrup(data);
        }
    },
    TEA_LEAVES_POWDER {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitTeaLeavesPowder(data);
        }
    },
    COFFEE_BEANS_POWDER {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitCoffeeBeansPowder(data);
        }
    },
    GREEN_TEA_MIXTURE {
        @Override
        public <T, J> T accept(IngredientVisitor<T, J> visitor, J data) {
            return visitor.visitGreenTeaMixture(data);
        }
    };

    public static final String HOT_WATER_TEXT = "HOT_WATER";
    public static final String HOT_MILK_TEXT = "HOT_MILK";
    public static final String GINGER_SYRUP_TEXT = "GINGER_SYRUP";
    public static final String SUGAR_SYRUP_TEXT = "SUGAR_SYRUP";
    public static final String TEA_LEAVES_POWDER_TEXT = "TEA_LEAVES_POWDER";
    public static final String COFFEE_BEANS_POWDER_TEXT = "COFFEE_BEANS_POWDER";
    public static final String GREEN_TEA_MIXTURE_TEXT = "GREEN_TEA_MIXTURE";

    public abstract <T, J> T accept(IngredientVisitor<T, J> visitor, J data);

    public interface IngredientVisitor<T, J> {

        T visitHotWater(J data);

        T visitHotMilk(J data);

        T visitGingerSyrup(J data);

        T visitSugarSyrup(J data);

        T visitTeaLeavesPowder(J data);

        T visitCoffeeBeansPowder(J data);

        T visitGreenTeaMixture(J data);
    }
}

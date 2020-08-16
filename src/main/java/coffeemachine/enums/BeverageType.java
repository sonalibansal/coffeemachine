package coffeemachine.enums;

public enum BeverageType {
    HOT_TEA {
        @Override
        public <T, J> T accept(BeverageType.BeverageTypeVisitor<T, J> visitor, J data) {
            return visitor.visitHotTea(data);
        }
    },
    HOT_COFFEE {
        @Override
        public <T, J> T accept(BeverageType.BeverageTypeVisitor<T, J> visitor, J data) {
            return visitor.visitHotCoffee(data);
        }
    },
    BLACK_TEA {
        @Override
        public <T, J> T accept(BeverageType.BeverageTypeVisitor<T, J> visitor, J data) {
            return visitor.visitBlackTea(data);
        }
    },
    GREEN_TEA {
        @Override
        public <T, J> T accept(BeverageType.BeverageTypeVisitor<T, J> visitor, J data) {
            return visitor.visitGreenTea(data);
        }
    },
    GINGER_TEA {
        @Override
        public <T, J> T accept(BeverageType.BeverageTypeVisitor<T, J> visitor, J data) {
            return visitor.visitGingerTea(data);
        }
    };

    public static final String HOT_TEA_TEXT = "HOT_TEA";
    public static final String HOT_COFFEE_TEXT = "HOT_COFFEE";
    public static final String BLACK_TEA_TEXT = "BLACK_TEA";
    public static final String GREEN_TEA_TEXT = "GREEN_TEA";
    public static final String GINGER_TEA_TEXT = "GINGER_TEA";

    public abstract <T, J> T accept(BeverageType.BeverageTypeVisitor<T, J> visitor, J data);

    public interface BeverageTypeVisitor<T, J> {

        T visitHotTea(J data);

        T visitHotCoffee(J data);

        T visitBlackTea(J data);

        T visitGreenTea(J data);

        T visitGingerTea(J data);
    }
}

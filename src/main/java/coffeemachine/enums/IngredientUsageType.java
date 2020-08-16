package coffeemachine.enums;

/**
 * This enum defines whether the ingredients needs to be added or deducted.
 * LOCK means the ingredient quantity will be deducted and release means ingredient quantity needs to added.
 */
public enum IngredientUsageType {
    LOCK {
        @Override
        public <T, J> T accept(IngredientUsageType.IngredientUsageTypeVisitor<T, J> visitor,
                               J data) {
            return visitor.visitLock(data);
        }
    },
    RELEASE {
        @Override
        public <T, J> T accept(IngredientUsageType.IngredientUsageTypeVisitor<T, J> visitor,
                               J data) {
            return visitor.visitRelease(data);
        }
    };

    public static final String LOCK_TEXT = "LOCK";
    public static final String RELEASE_TEXT = "RELEASE";

    public abstract <T, J> T accept(IngredientUsageType.IngredientUsageTypeVisitor<T, J> visitor,
                                    J data);

    public interface IngredientUsageTypeVisitor<T, J> {

        T visitLock(J data);

        T visitRelease(J data);
    }
}

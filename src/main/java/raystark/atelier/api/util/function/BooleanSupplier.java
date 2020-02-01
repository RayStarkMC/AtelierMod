package raystark.atelier.api.util.function;

@FunctionalInterface
public interface BooleanSupplier extends java.util.function.BooleanSupplier {
    default BooleanSupplier negate() {
        return () -> !getAsBoolean();
    }
}

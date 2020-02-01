package raystark.atelier.api.util.function;

@FunctionalInterface
public interface DoublePredicate extends java.util.function.DoublePredicate {
    default BooleanSupplier supply(double t) {
        return () -> test(t);
    }
}

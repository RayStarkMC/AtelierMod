package raystark.atelier.api.util.function;

@FunctionalInterface
public interface IntPredicate extends java.util.function.IntPredicate {
    default BooleanSupplier supply(int t) {
        return () -> test(t);
    }
}

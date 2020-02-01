package raystark.atelier.api.util.function;

@FunctionalInterface
public interface LongPredicate extends java.util.function.LongPredicate {
    default BooleanSupplier supply(long t) {
        return () -> test(t);
    }
}

package raystark.atelier.api.util.function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface IntPredicate extends java.util.function.IntPredicate {
    default BooleanSupplier supply(int t) {
        return () -> test(t);
    }

    default IntUnaryOperator when(java.util.function.IntUnaryOperator function) {
        requireNonNull(function);
        return t -> test(t) ? function.applyAsInt(t) : t;
    }
}

package raystark.atelier.api.util.function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface LongPredicate extends java.util.function.LongPredicate {
    default BooleanSupplier supply(long t) {
        return () -> test(t);
    }

    default LongUnaryOperator when(java.util.function.LongUnaryOperator function) {
        requireNonNull(function);
        return t -> test(t) ? function.applyAsLong(t) : t;
    }
}

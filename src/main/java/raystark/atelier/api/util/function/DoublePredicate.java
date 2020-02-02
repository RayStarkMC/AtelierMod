package raystark.atelier.api.util.function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface DoublePredicate extends java.util.function.DoublePredicate {
    default BooleanSupplier supply(double t) {
        return () -> test(t);
    }

    default DoubleUnaryOperator when(java.util.function.DoubleUnaryOperator function) {
        requireNonNull(function);
        return t -> test(t) ? function.applyAsDouble(t) : t;
    }
}

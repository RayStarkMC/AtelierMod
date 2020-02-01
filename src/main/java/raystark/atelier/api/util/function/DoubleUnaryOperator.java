package raystark.atelier.api.util.function;

import java.util.function.DoubleSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface DoubleUnaryOperator extends java.util.function.DoubleUnaryOperator {
    default <V> DoubleFunction then(java.util.function.DoubleFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsDouble(t));
    }

    default DoubleToIntFunction thenI(java.util.function.DoubleToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsDouble(t));
    }

    default DoubleUnaryOperator thenD(java.util.function.DoubleUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsDouble(t));
    }

    default DoubleToLongFunction thenL(java.util.function.DoubleToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsDouble(t));
    }

    default DoublePredicate thenB(java.util.function.DoublePredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsDouble(t));
    }

    default DoubleSupplier supply(double t) {
        return () -> applyAsDouble(t);
    }
}

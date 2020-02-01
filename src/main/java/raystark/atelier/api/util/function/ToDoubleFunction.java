package raystark.atelier.api.util.function;

import java.util.function.DoubleSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface ToDoubleFunction<T> extends java.util.function.ToDoubleFunction<T> {
    default <V> Function<T, V> then(java.util.function.DoubleFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsDouble(t));
    }

    default ToIntFunction<T> thenI(java.util.function.DoubleToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsDouble(t));
    }

    default ToDoubleFunction<T> thenD(java.util.function.DoubleUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsDouble(t));
    }

    default ToLongFunction<T> thenL(java.util.function.DoubleToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsDouble(t));
    }

    default Predicate<T> thenB(java.util.function.DoublePredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsDouble(t));
    }

    default DoubleSupplier supply(T t) {
        requireNonNull(t);
        return () -> applyAsDouble(t);
    }
}

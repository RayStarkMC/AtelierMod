package raystark.atelier.api.util.function;

import java.util.function.LongSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface ToLongFunction<T> extends java.util.function.ToLongFunction<T> {
    default <V> Function<T, V> then(java.util.function.LongFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsLong(t));
    }

    default ToIntFunction<T> thenI(java.util.function.LongToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsLong(t));
    }

    default ToDoubleFunction<T> thenD(java.util.function.LongToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsLong(t));
    }

    default ToLongFunction<T> thenL(java.util.function.LongUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsLong(t));
    }

    default Predicate<T> thenB(java.util.function.LongPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsLong(t));
    }

    default LongSupplier supply(T t) {
        requireNonNull(t);
        return () -> applyAsLong(t);
    }
}

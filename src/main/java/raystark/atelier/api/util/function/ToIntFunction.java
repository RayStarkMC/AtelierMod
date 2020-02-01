package raystark.atelier.api.util.function;

import java.util.function.IntSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface ToIntFunction<T> extends java.util.function.ToIntFunction<T> {
    default <V> Function<T, V> then(java.util.function.IntFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsInt(t));
    }

    default ToIntFunction<T> thenI(java.util.function.IntUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsInt(t));
    }

    default ToDoubleFunction<T> thenD(java.util.function.IntToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsInt(t));
    }

    default ToLongFunction<T> thenL(java.util.function.IntToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsInt(t));
    }

    default Predicate<T> thenB(java.util.function.IntPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsInt(t));
    }

    default IntSupplier supply(T t) {
        requireNonNull(t);
        return () -> applyAsInt(t);
    }
}

package raystark.atelier.api.util.function;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Function<T, R> extends java.util.function.Function<T, R> {
    default <V> Function<T, V> then(java.util.function.Function<? super R, ? extends V> after) {
        requireNonNull(after);
        return t -> andThen(after).apply(t);
    }

    default ToIntFunction<T> thenI(java.util.function.ToIntFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsInt(apply(t));
    }

    default ToDoubleFunction<T> thenD(java.util.function.ToDoubleFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(apply(t));
    }

    default ToLongFunction<T> thenL(java.util.function.ToLongFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsLong(apply(t));
    }

    default Predicate<T> thenB(java.util.function.Predicate<? super R> after) {
        requireNonNull(after);
        return t -> after.test(apply(t));
    }

    default Supplier suplly(T t) {
        requireNonNull(t);
        return () -> apply(t);
    }
}

package raystark.atelier.api.util.function;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface LongFunction<R> extends java.util.function.LongFunction<R> {
    default <V> LongFunction then(java.util.function.Function<? super R, ? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(apply(t));
    }

    default LongToIntFunction thenI(java.util.function.ToIntFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsInt(apply(t));
    }

    default LongToDoubleFunction thenD(java.util.function.ToDoubleFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(apply(t));
    }

    default LongUnaryOperator thenL(java.util.function.ToLongFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsLong(apply(t));
    }

    default LongPredicate thenB(java.util.function.Predicate<? super R> after) {
        requireNonNull(after);
        return t -> after.test(apply(t));
    }

    default Supplier<R> supply(long t) {
        return () -> apply(t);
    }
}

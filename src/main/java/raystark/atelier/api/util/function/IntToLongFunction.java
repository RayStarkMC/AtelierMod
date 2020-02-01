package raystark.atelier.api.util.function;

import java.util.function.LongSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface IntToLongFunction extends java.util.function.IntToLongFunction {
    default <V> IntFunction then(java.util.function.LongFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsLong(t));
    }

    default IntUnaryOperator thenI(java.util.function.LongToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsLong(t));
    }

    default IntToDoubleFunction thenD(java.util.function.LongToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsLong(t));
    }

    default IntToLongFunction thenL(java.util.function.LongUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsLong(t));
    }

    default IntPredicate thenB(java.util.function.LongPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsLong(t));
    }

    default LongSupplier supply(int t) {
        return () -> applyAsLong(t);
    }
}

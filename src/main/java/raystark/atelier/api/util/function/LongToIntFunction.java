package raystark.atelier.api.util.function;

import java.util.function.IntSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface LongToIntFunction extends java.util.function.LongToIntFunction {
    default <V> LongFunction then(java.util.function.IntFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsInt(t));
    }

    default LongToIntFunction thenI(java.util.function.IntUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsInt(t));
    }

    default LongToDoubleFunction thenD(java.util.function.IntToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsInt(t));
    }

    default LongUnaryOperator thenL(java.util.function.IntToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsInt(t));
    }

    default LongPredicate thenB(java.util.function.IntPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsInt(t));
    }

    default IntSupplier supply(long t) {
        return () -> applyAsInt(t);
    }
}

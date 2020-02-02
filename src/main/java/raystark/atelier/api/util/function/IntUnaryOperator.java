package raystark.atelier.api.util.function;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface IntUnaryOperator extends java.util.function.IntUnaryOperator {
    default <V> IntFunction then(java.util.function.IntFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsInt(t));
    }

    default IntUnaryOperator thenI(java.util.function.IntUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsInt(t));
    }

    default IntToDoubleFunction thenD(java.util.function.IntToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsInt(t));
    }

    default IntToLongFunction thenL(java.util.function.IntToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsInt(t));
    }

    default IntPredicate thenB(java.util.function.IntPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsInt(t));
    }

    default IntSupplier supply(int t) {
        return () -> applyAsInt(t);
    }

    default IntConsumer consume(IntConsumer consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(applyAsInt(t));
    }
}

package raystark.atelier.api.util.function;

import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface LongUnaryOperator extends java.util.function.LongUnaryOperator {
    default <V> LongFunction then(java.util.function.LongFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsLong(t));
    }

    default LongToIntFunction thenI(java.util.function.LongToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsLong(t));
    }

    default LongToDoubleFunction thenD(java.util.function.LongToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsLong(t));
    }

    default LongUnaryOperator thenL(java.util.function.LongUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsLong(t));
    }

    default LongPredicate thenB(java.util.function.LongPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsLong(t));
    }

    default LongSupplier supply(long t) {
        return () -> applyAsLong(t);
    }

    default LongConsumer consume(LongConsumer consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(applyAsLong(t));
    }
}

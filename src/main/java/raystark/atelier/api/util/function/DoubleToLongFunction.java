package raystark.atelier.api.util.function;

import java.util.function.DoubleConsumer;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface DoubleToLongFunction extends java.util.function.DoubleToLongFunction {
    default <V> DoubleFunction then(java.util.function.LongFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsLong(t));
    }

    default DoubleToIntFunction thenI(java.util.function.LongToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsLong(t));
    }

    default DoubleUnaryOperator thenD(java.util.function.LongToDoubleFunction after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsLong(t));
    }

    default DoubleToLongFunction thenL(java.util.function.LongUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsLong(t));
    }

    default DoublePredicate thenB(java.util.function.LongPredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsLong(t));
    }

    default LongSupplier supply(double t) {
        return () -> applyAsLong(t);
    }

    default DoubleConsumer consume(LongConsumer consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(applyAsLong(t));
    }
}

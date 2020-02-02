package raystark.atelier.api.util.function;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.LongConsumer;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface LongToDoubleFunction extends java.util.function.LongToDoubleFunction {
    default <V> LongFunction then(java.util.function.DoubleFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsDouble(t));
    }

    default LongToIntFunction thenI(java.util.function.DoubleToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsDouble(t));
    }

    default LongToDoubleFunction thenD(java.util.function.DoubleUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsDouble(t));
    }

    default LongUnaryOperator thenL(java.util.function.DoubleToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsDouble(t));
    }

    default LongPredicate thenB(java.util.function.DoublePredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsDouble(t));
    }

    default DoubleSupplier supply(long t) {
        return () -> applyAsDouble(t);
    }

    default LongConsumer consume(DoubleConsumer consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(applyAsDouble(t));
    }
}

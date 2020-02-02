package raystark.atelier.api.util.function;

import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface DoubleToIntFunction extends java.util.function.DoubleToIntFunction {
    default <V> LongFunction then(java.util.function.IntFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsInt(t));
    }

    default DoubleToIntFunction thenI(java.util.function.DoubleToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsInt(t));
    }

    default DoubleUnaryOperator thenD(java.util.function.DoubleUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsInt(t));
    }

    default DoubleToLongFunction thenL(java.util.function.DoubleToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsInt(t));
    }

    default DoublePredicate thenB(java.util.function.DoublePredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsInt(t));
    }

    default IntSupplier supply(double t) {
        return () -> applyAsInt(t);
    }

    default DoubleConsumer consume(IntConsumer consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(applyAsInt(t));
    }
}

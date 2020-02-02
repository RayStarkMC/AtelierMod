package raystark.atelier.api.util.function;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface IntToDoubleFunction extends java.util.function.IntToDoubleFunction {
    default <V> IntFunction then(java.util.function.DoubleFunction<? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(applyAsDouble(t));
    }

    default IntUnaryOperator thenI(java.util.function.DoubleToIntFunction after) {
        requireNonNull(after);
        return t -> after.applyAsInt(applyAsDouble(t));
    }

    default IntToDoubleFunction thenD(java.util.function.DoubleUnaryOperator after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(applyAsDouble(t));
    }

    default IntToLongFunction thenL(java.util.function.DoubleToLongFunction after) {
        requireNonNull(after);
        return t -> after.applyAsLong(applyAsDouble(t));
    }

    default IntPredicate thenB(java.util.function.DoublePredicate after) {
        requireNonNull(after);
        return t -> after.test(applyAsDouble(t));
    }

    default DoubleSupplier supply(int t) {
        return () -> applyAsDouble(t);
    }

    default IntConsumer consume(DoubleConsumer consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(applyAsDouble(t));
    }
}

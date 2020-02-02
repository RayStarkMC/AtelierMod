package raystark.atelier.api.util.function;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface DoubleFunction<R> extends java.util.function.DoubleFunction<R> {
    default <V> DoubleFunction then(java.util.function.Function<? super R, ? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(apply(t));
    }

    default DoubleToIntFunction thenI(java.util.function.ToIntFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsInt(apply(t));
    }

    default DoubleUnaryOperator thenD(java.util.function.ToDoubleFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(apply(t));
    }

    default DoubleToLongFunction thenL(java.util.function.ToLongFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsLong(apply(t));
    }

    default DoublePredicate thenB(java.util.function.Predicate<? super R> after) {
        requireNonNull(after);
        return t -> after.test(apply(t));
    }

    default Supplier<R> supply(double t) {
        return () -> apply(t);
    }

    default DoubleConsumer consume(Consumer<? super R> consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(apply(t));
    }
}

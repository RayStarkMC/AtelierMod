package raystark.atelier.api.util.function;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface IntFunction<R> extends java.util.function.IntFunction<R> {
    default <V> IntFunction then(java.util.function.Function<? super R, ? extends V> after) {
        requireNonNull(after);
        return t -> after.apply(apply(t));
    }

    default IntUnaryOperator thenI(java.util.function.ToIntFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsInt(apply(t));
    }

    default IntToDoubleFunction thenD(java.util.function.ToDoubleFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsDouble(apply(t));
    }

    default IntToLongFunction thenL(java.util.function.ToLongFunction<? super R> after) {
        requireNonNull(after);
        return t -> after.applyAsLong(apply(t));
    }

    default IntPredicate thenB(java.util.function.Predicate<? super R> after) {
        requireNonNull(after);
        return t -> after.test(apply(t));
    }

    default Supplier<R> supply(int t) {
        return () -> apply(t);
    }

    default IntConsumer consume(Consumer<? super R> consumer) {
        requireNonNull(consumer);
        return t -> consumer.accept(apply(t));
    }
}

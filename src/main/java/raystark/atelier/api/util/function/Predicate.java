package raystark.atelier.api.util.function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Predicate<T> extends java.util.function.Predicate<T> {
    default BooleanSupplier supply(T t) {
        requireNonNull(t);
        return () -> test(t);
    }

    default UnaryOperator<T> when(java.util.function.UnaryOperator<T> function) {
        requireNonNull(function);
        return t -> test(t) ? function.apply(t) : t;
    }
}

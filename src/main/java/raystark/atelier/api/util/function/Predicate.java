package raystark.atelier.api.util.function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Predicate<T> extends java.util.function.Predicate<T> {
    default BooleanSupplier supply(T t) {
        requireNonNull(t);
        return () -> test(t);
    }
}

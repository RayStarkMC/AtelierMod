package raystark.atelier.api.util.function;

import static java.util.Objects.requireNonNull;

public final class Functions {
    private Functions() {}

    public static <T, R> Function<T, R> f(java.util.function.Function<T, R> function) {
        requireNonNull(function);
        return function::apply;
    }

    public static <T> ToIntFunction<T> ftI(java.util.function.ToIntFunction<T> toIntFunction) {
        requireNonNull(toIntFunction);
        return toIntFunction::applyAsInt;
    }

    public static <T> ToDoubleFunction<T> ftD(java.util.function.ToDoubleFunction<T> toDoubleFunction) {
        requireNonNull(toDoubleFunction);
        return toDoubleFunction::applyAsDouble;
    }

    public static <T> ToLongFunction<T> ftL(java.util.function.ToLongFunction<T> toLongFunction) {
        requireNonNull(toLongFunction);
        return toLongFunction::applyAsLong;
    }

    public static <R> IntFunction<R> fI(java.util.function.IntFunction<R> intFunction) {
        requireNonNull(intFunction);
        return intFunction::apply;
    }

    public static IntUnaryOperator fItI(java.util.function.IntUnaryOperator intUnaryOperator) {
        requireNonNull(intUnaryOperator);
        return intUnaryOperator::applyAsInt;
    }

    public static IntToDoubleFunction fItD(java.util.function.IntToDoubleFunction intToDoubleFunction) {
        requireNonNull(intToDoubleFunction);
        return intToDoubleFunction::applyAsDouble;
    }

    public static IntToLongFunction fItL(java.util.function.IntToLongFunction intToLongFunction) {
        requireNonNull(intToLongFunction);
        return intToLongFunction::applyAsLong;
    }

    public static <R> DoubleFunction<R> fD(java.util.function.DoubleFunction<R> doubleFunction) {
        requireNonNull(doubleFunction);
        return doubleFunction::apply;
    }

    public static DoubleToIntFunction fDtI(java.util.function.DoubleToIntFunction doubleToIntFunction) {
        requireNonNull(doubleToIntFunction);
        return doubleToIntFunction::applyAsInt;
    }

    public static DoubleUnaryOperator fDtD(java.util.function.DoubleUnaryOperator doubleUnaryOperator) {
        requireNonNull(doubleUnaryOperator);
        return doubleUnaryOperator::applyAsDouble;
    }

    public static DoubleToLongFunction fDtL(java.util.function.DoubleToLongFunction doubleToLongFunction) {
        requireNonNull(doubleToLongFunction);
        return doubleToLongFunction::applyAsLong;
    }

    public static <R> LongFunction<R> fL(java.util.function.LongFunction<R> longFunction) {
        requireNonNull(longFunction);
        return longFunction::apply;
    }

    public static LongToIntFunction fLtI(java.util.function.LongToIntFunction longToIntFunction) {
        requireNonNull(longToIntFunction);
        return longToIntFunction::applyAsInt;
    }

    public static LongToDoubleFunction fLtD(java.util.function.LongToDoubleFunction longToDoubleFunction) {
        requireNonNull(longToDoubleFunction);
        return longToDoubleFunction::applyAsDouble;
    }

    public static LongUnaryOperator fLtL(java.util.function.LongUnaryOperator longUnaryOperator) {
        requireNonNull(longUnaryOperator);
        return longUnaryOperator::applyAsLong;
    }

    public static <T> Predicate<T> p(java.util.function.Predicate<T> predicate) {
        requireNonNull(predicate);
        return predicate::test;
    }

    public static IntPredicate pI(java.util.function.IntPredicate intPredicate) {
        requireNonNull(intPredicate);
        return intPredicate::test;
    }

    public static DoublePredicate pD(java.util.function.DoublePredicate doublePredicate) {
        requireNonNull(doublePredicate);
        return doublePredicate::test;
    }

    public static LongPredicate pL(java.util.function.LongPredicate longPredicate) {
        requireNonNull(longPredicate);
        return longPredicate::test;
    }
}

package raystark.atelier.api.util;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public final class FunctionUtils {
    private FunctionUtils() {}

    /**
     * Objects.isNull(predicate) ? (T e) -> false : predicateとなる述語を返します。
     *
     * <p>このメソッドはラムダ式やメソッド参照によって渡されたpredicateに対してand、or、negateを適用するために使用されます。
     *
     * @param predicate 述語
     * @param <T> 述語の入力の型
     * @return 引数の述語
     */
    public static <T> Predicate<T> predicate(Predicate<T> predicate) {
        return predicate == null ? e -> false : predicate;
    }

    public static IntPredicate intPredicate(IntPredicate predicate) {
        return predicate == null ? e -> false : predicate;
    }

    @FunctionalInterface
    public interface BiIntPredicate {
        boolean test(int e1, int e2);

        static BiIntPredicate biPredicate(BiIntPredicate predicate) {
            return predicate == null ? (e1, e2) ->  false : predicate;
        }

        static BiIntPredicate equal() {
            return (e1, e2) -> e1 == e2;
        }

        static IntPredicate equal(int e1) { return equal().applyPartially(e1); }

        default IntFunction<IntPredicate> currying() {
            return e1 -> e2 -> test(e1, e2);
        }

        default IntPredicate applyPartially(int e1) {
            return currying().apply(e1);
        }

        default BiIntPredicate negate() {
            return (e1, e2) -> !test(e1, e2);
        }

        default BiIntPredicate change() {
            return (e1, e2) -> test(e2, e1);
        }
    }
}

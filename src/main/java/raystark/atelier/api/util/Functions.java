package raystark.atelier.api.util;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public final class Functions {
    private Functions() {}

    /**
     * {@code predicate != null ? predicate : (T e) -> false}となる述語を返します。
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
}

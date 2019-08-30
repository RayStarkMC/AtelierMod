package raystark.atelier.api.alchemy.recipe;

/**
 * 特定のアイテム/ブロックを指定する要求物を表すインターフェース。
 *
 * <p>
 *
 * @param <T> 要求物の型
 * @param <S>
 */
public interface ISpecifiedRequirement<T, S> extends IRequirement<S> {
    /**
     *
     * @return
     */
    T getSpecified();

    /**
     *
     * @return
     */
    int getMeta();
}

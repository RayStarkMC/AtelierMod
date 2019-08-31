package raystark.atelier.api.alchemy.recipe;

/**
 * 特定のアイテム/ブロックを指定する要求物を表すインターフェース。
 *
 * @param <S> アイテムスタックの型
 */
public interface ISpecifiedRequirement <S> extends IRequirement<S> {
    S getSpecified();
}

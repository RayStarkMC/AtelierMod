package raystark.atelier.api.alchemy.recipe;

/**
 * 錬金術レシピの要求物を表すインターフェース。
 *
 * @param <S> アイテムスタックの型
 *
 * @author RayStark
 */
public interface IRequirement<S> {

    /**
     * 引数に渡されたアイテムスタックが材料と利用できるか調べます。
     *
     * @param input アイテムスタック
     * @return アイテムがレシピの材料として使用できる場合true
     */
    boolean isAppliciableAsMaterial(S input);
}
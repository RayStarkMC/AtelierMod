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
     * 引数に渡されたアイテムスタックが材料として利用できるか調べます。
     *
     * @param input アイテムスタック
     * @return アイテムがレシピの材料として使用できる場合true
     *
     * @throws NullPointerException inputがnullの場合
     */
    boolean isApplicableAsMaterial(S input);

    /**
     * この要求物の名前を返します。
     *
     * @return 要求物名
     */
    String getName();
}
package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.category.IMaterialCategory;

/**
 * 特定のカテゴリを指定する要求物を表すインターフェース。
 *
 * <p>この要求物では特定のアイテムを指定せず、そのアイテムが指定したカテゴリに属すことを要求します。
 * これにより、同じレシピの完成品を違う材料で作成することが出来ます。
 *
 * @param <S> アイテムスタックの型
 */
public interface ICategoryRequirement<S> extends IRequirement<S>{
    /**
     * 引数に渡されたアイテムスタックが材料として利用できるか調べます。
     *
     * <p>このメソッドでは引数に渡されたアイテムスタックがgetCategoryで取得できるカテゴリに属すか検証します。
     *
     * @param input アイテムスタック
     * @return inputが指定するカテゴリに属しているならばtrue
     */
    @Override
    boolean isApplicableAsMaterial(S input);

    /**
     * この要求物が指定するカテゴリを返します。
     *
     * @return カテゴリ
     */
    IMaterialCategory getCategory();
}

package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ElementOwner;

import java.util.List;

/**
 * 錬金レシピを表すインターフェース.
 *
 * <p>レシピは材料としてアイテムそのものを指定できるほか、アイテムの属すカテゴリを指定することが出来る。
 * 材料としてカテゴリが指定された場合、そのカテゴリに属す任意のアイテムをレシピの材料として用いることが出来る。
 *
 * @param <S> アイテムスタックの型
 */
public interface IAlchemicalRecipe<S> {

    /**
     * このレシピの要求物のリストを返します。
     *
     * @return このレシピの要求物の不変リスト
     */
    List<IAlchemicalRecipeRequirement<S>> getRequirements();

    /**
     * 属性値と潜在能力から完成品のアイテムスタックを生成します。
     *
     * @param elements レシピに適応する属性値
     * @param abilities アイテムに適応する潜在能力
     * @return 性能決定後の完成品のアイテムスタック
     */
    S getProduct(ElementOwner elements, List<IPotentialAbility> abilities);
}
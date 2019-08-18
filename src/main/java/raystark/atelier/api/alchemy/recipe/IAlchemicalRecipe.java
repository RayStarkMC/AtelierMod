package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ElementOwner;

import java.util.List;

/**
 * 錬金レシピを表すインターフェース.
 *
 * <p>レシピは材料としてアイテムそのものを指定できるほか、アイテムの属すカテゴリを指定することが出来る。
 * 材料としてカテゴリが指定された場合、そのカテゴリに属す任意のアイテムをレシピの材料として用いることが出来る。
 *
 *
 * @param <S> アイテムスタックの型
 */
public interface IAlchemicalRecipe<S> {

    /**
     * このレシピで作成するアイテムの返します。
     *
     * @return このレシピで作成するアイテム
     */
    IAlchemicalProduct getProduct();

    /**
     * このレシピの要求物のリストを返します。
     *
     * @return このレシピの要求物の不変リスト
     */
    List<IRequirement<S>> getRequirements();

    /**
     *
     *
     * @return 効果予測のリスト
     */
    List<IEffectEstimated<? extends IEffect>> getEffectsEstimated();

    /**
     * 材料のリストを受け取り、レシピが実行できるか調べます。
     *
     * @param inputs 材料のリスト
     * @return リストの材料でこのレシピを実行できる場合true
     */
    boolean isReady(List<S> inputs);

    /**
     * 属性値と潜在能力から完成品のアイテムスタックを生成します。
     *
     * <o>
     *
     * @param quality アイテムに適用する品質値
     * @param elements アイテムに適用する属性値
     * @param abilities アイテムに適用する潜在能力
     * @return 性能決定後の完成品のアイテムスタック
     */
    S createProduct(int quality, ElementOwner elements, List<IPotentialAbility> abilities);
}
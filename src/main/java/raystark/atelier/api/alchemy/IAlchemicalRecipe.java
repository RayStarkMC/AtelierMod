package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.api.alchemy.status.PotentialAbilityOwner;

import java.util.List;

/**
 * 錬金レシピを表すインターフェース.
 *
 * <p>レシピは材料のアイテム指定
 *
 * @param <I> アイテムの型
 * @param <B> ブロックの型
 * @param <S> アイテムスタックの型
 */
public interface IAlchemicalRecipe<I, B, S> {

    /**
     * このレシピを用いてクラフトを実行します。
     *
     * @param elements レシピに適応する属性値
     * @param abilities アイテムに適応する潜在能力
     * @return 性能決定後の完成品
     */
    S getProduct(ElementOwner elements, List<IPotentialAbility> abilities);
}
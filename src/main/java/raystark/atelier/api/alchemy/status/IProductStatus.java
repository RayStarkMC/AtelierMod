package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;

/**
 * 錬金術製品の能力値を表すインターフェース
 */
public interface IProductStatus extends Quality, EffectOwner, PotentialAbilityOwner {

    /**
     * 品質値を返します。
     *
     * <p>品質値は不変でなければなりません。
     *
     * @return 品質値
     */
    @Override
    int getQuality();

    /**
     * 効果の不変リストを返します。
     *
     * <p>このメソッドを経由して効果のリストを直接編集することはできません。
     * @return 効果の不変リスト
     */
    @Override
    List<IEffect> getEffectList();

    /**
     * 潜在能力の不変リストを返します。
     *
     * <p>このメソッドを経由して潜在能力のリストを直接編集することはできません。
     * @return 潜在能力の不変リスト
     */
    @Override
    List<IPotentialAbility> getPotentialAbilityList();
}

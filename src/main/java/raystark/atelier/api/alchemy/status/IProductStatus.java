package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;

public interface IProductStatus extends Quality, EffectOwner, PotentialAbilityOwner{
    /**
     * このアイテムの品質値を返す。<p/>
     *
     * このアイテムが品質値を持たなかった場合Quality.MIN_VALUEを返す。
     * @return 品質値
     */
    @Override int getQuality();

    /**
     * このアイテムに付与された効果一覧<p/>
     *
     * @return 効果のリスト
     */
    @Override List<IEffect> getEffectList();

    /**
     * このアイテムに付与された潜在能力一覧
     * @return 潜在能力のリスト
     */
    @Override List<IPotentialAbility> getPotentialAbilityList();
}

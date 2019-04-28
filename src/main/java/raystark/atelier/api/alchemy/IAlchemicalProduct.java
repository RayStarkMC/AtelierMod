package raystark.atelier.api.alchemy;

import java.util.List;

/**
 * 錬金術によって作られた製品を表すインターフェース。
 */
public interface IAlchemicalProduct {
    /**
     * このアイテムに付与された効果一覧
     * @return 効果のリスト
     */
    List<IEffect> getEffectList();

    /**
     * このアイテムに付与された潜在能力一覧
     * @return 潜在能力のリスト
     */
    List<IPotentialAbility> getPotentialAbilityList();
}

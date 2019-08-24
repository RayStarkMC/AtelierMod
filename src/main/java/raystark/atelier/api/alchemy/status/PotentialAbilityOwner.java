package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;

/**
 * 潜在能力を持ったものを表すインターフェース。
 *
 * @author RayStark
 */
public interface PotentialAbilityOwner {
    /**
     * このアイテムに付与された潜在能力一覧
     * @return 潜在能力のリスト
     */
    List<IPotentialAbility> getPotentialAbilityList();
}

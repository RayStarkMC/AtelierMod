package raystark.atelier.api.alchemy;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.potential.IPotentialAbility;

import java.util.List;

/**
 * 潜在能力を持ったものを表すインターフェース。
 */
public interface PotentialAbilityOwner {
    /**
     * このアイテムに付与された潜在能力一覧
     * @param stackAlchemy アイテムスタック
     * @return 潜在能力のリスト
     */
    List<IPotentialAbility> getPotentialAbilityList(ItemStack stackAlchemy);
}

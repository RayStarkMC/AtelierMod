package raystark.atelier.api.alchemy;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.effect.IEffect;

import java.util.List;

/**
 * 錬金術によって作られた製品を表すインターフェース。
 */
public interface IAlchemicalProduct {
    /**
     * このアイテムに付与された効果一覧
     * @param stackAlchemy アイテムスタック
     * @return 効果のリスト
     */
    List<IEffect> getEffectList(ItemStack stackAlchemy);

    /**
     * このアイテムに付与された潜在能力一覧
     * @param stackAlchemy アイテムスタック
     * @return 潜在能力のリスト
     */
    List<IPotentialAbility> getPotentialAbilityList(ItemStack stackAlchemy);
}

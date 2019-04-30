package raystark.atelier.api.alchemy;


import net.minecraft.item.ItemStack;
import raystark.atelier.api.effect.IEffect;

import java.util.List;

/**
 * 効果を持つものを表すインターフェース
 */
public interface EffectOwner {
    /**
     * このアイテムに付与された効果一覧
     * @param stackAlchemy アイテムスタック
     * @return 効果のリスト
     */
    List<IEffect> getEffectList(ItemStack stackAlchemy);
}

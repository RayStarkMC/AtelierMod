package raystark.atelier.api.alchemy;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.effect.IEffect;

/**
 * 品質値を持つものを表すインターフェース.<p/>
 *
 * 品質値は完成品の{@link IEffect 効果}に影響を与える場合がある.
 */
public interface Quality {
    /**
     * 品質の最低値
     */
    int MIN_VALUE = 1;

    /**
     * 品質値を返す
     * @param itemStack アイテムスタック
     * @return 品質値
     */
    int getQuality(ItemStack itemStack);
}

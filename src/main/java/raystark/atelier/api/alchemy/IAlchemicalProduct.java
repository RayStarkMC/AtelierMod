package raystark.atelier.api.alchemy;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.effect.IEffect;

import java.util.List;

/**
 * 錬金術によって作られた製品を表すインターフェース。
 */
public interface IAlchemicalProduct extends Quality, EffectOwner, PotentialAbilityOwner{

    /**
     * このアイテムの品質値を返す。<p/>
     *
     * このアイテムが品質値を持たなかった場合Quality.MIN_VALUEを返す。
     * @param itemStack アイテムスタック
     * @return 品質値
     */
    @Override int getQuality(ItemStack itemStack);

    /**
     * このアイテムに付与された効果一覧<p/>
     *
     * @param stackAlchemy アイテムスタック
     * @return 効果のリスト
     */
    @Override List<IEffect> getEffectList(ItemStack stackAlchemy);

    /**
     * このアイテムに付与された潜在能力一覧
     * @param stackAlchemy アイテムスタック
     * @return 潜在能力のリスト
     */
    @Override List<IPotentialAbility> getPotentialAbilityList(ItemStack stackAlchemy);
}

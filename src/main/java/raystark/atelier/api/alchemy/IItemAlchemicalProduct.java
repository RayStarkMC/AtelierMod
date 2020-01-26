package raystark.atelier.api.alchemy;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られたアイテムを表すインターフェース。
 *
 * @author RayStark
 */
public interface IItemAlchemicalProduct extends IAlchemicalProduct<ItemStack>{
    /**
     * stackのステータスを取得します。
     *
     * @param stack アイテムスタック
     * @return stackのステータス。
     * @throws NullPointerException stackがnullの場合
     * @throws IllegalArgumentException stackがステータスを持たない場合
     */
    @Override
    IProductStatus getStatus(ItemStack stack);
}

package raystark.atelier.common.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * AtelierModで追加されるBlockクラスに対応するItemBlockクラス。
 *
 * <p>
 */
public abstract class ItemBlockModBase extends ItemBlock {
    protected ItemBlockModBase(Block block) {
        super(block);
    }
}

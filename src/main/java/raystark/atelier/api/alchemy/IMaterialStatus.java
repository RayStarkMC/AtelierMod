package raystark.atelier.api.alchemy;


import net.minecraft.item.Item;

/**
 * 錬金素材の性能を表すインターフェース
 */
public interface IMaterialStatus extends Quality, ElementOwner {
    /**
     * 錬金材料として使われたアイテムを返す.
     * @return 錬金材料
     */
    Item getItem();

    // TODO 潜在能力について型を継承させる。
}

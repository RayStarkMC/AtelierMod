package raystark.atelier.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.api.util.ToolClasses;
import raystark.atelier.common.AtelierMod;
import raystark.atelier.common.block.itemblock.ItemBlockModBase;

import java.util.Objects;

import static raystark.atelier.common.AtelierMod.TAB_ATELIER;

/**
 * AtelierModで追加されるブロックの基底クラス
 *
 * <p>インスタンス生成時にブロック名、テクスチャ名、クリエイティブタブが自動的に設定されます。
 * またブロックはGameRegistryに自動的に登録されます。
 *
 * <p>この実装ではブロックの設定をするためにいくつかのメソッドを追加します。
 */
public abstract class BlockModBase extends Block {
    protected final IAtelierRegistry<Item, Block, ItemStack> registry;

    /**
     * 指定したマテリアル、ブロック名、ItemBlockクラスからブロックを生成します。
     *
     * <p>itemBlockh
     *
     * @param mat ブロックのマテリアスクラス
     * @param blockName ブロック名
     * @param itemBlock ItemBlockクラス
     */
    protected BlockModBase(Material mat, String blockName, Class<? extends ItemBlockModBase> itemBlock) {
        super(Objects.requireNonNull(mat));
        Objects.requireNonNull(blockName);
        registry = AtelierMod.getInstance().getRegistry();
        setBlockName(AtelierMod.MODID + "." + blockName);
        setBlockTextureName((AtelierMod.MODID + ":" + blockName).toLowerCase());
        setCreativeTab(TAB_ATELIER);
        GameRegistry.registerBlock(this, itemBlock == null ? ItemBlockModBase.class : itemBlock, blockName);
    }

    /**
     * このブロックの指定したメタデータのHarvestLevelを設定します。
     *
     * <p>引数にツールを表すStringの代わりにToolClasses型を受け取ります。
     * このメソッドはBlock#setHarvestLevel(String toolClass, int level, int meta)を呼び出します。
     *
     * @param toolClass ツールクラス
     * @param level レベル
     * @param metadata メタデータ
     */
    protected void setHarvestLevel(ToolClasses toolClass, int level, int metadata) {
        super.setHarvestLevel(toolClass == null ? null : toolClass.value(), level, metadata);
    }

    /**
     * このブロックのHarvestLevelを設定します。
     *
     * <p>このブロックのすべてのメタデータのHarvestLevelを設定します。
     *
     * @param toolClass ツールクラス
     * @param level レベル
     */
    protected void setHarvestLevel(ToolClasses toolClass, int level) {
        super.setHarvestLevel(toolClass == null ? null : toolClass.value(), level);
    }
}

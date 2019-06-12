package raystark.atelier.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.common.AtelierMod;
import raystark.atelier.common.block.itemblock.ItemBlockModBase;

import static raystark.atelier.common.AtelierMod.TAB_ATELIER;

public abstract class BlockModBase extends Block {
    protected final IAtelierRegistry<Item, Block, ItemStack> registry;

    protected BlockModBase(Material mat, String blockName, Class<? extends ItemBlockModBase> itemBlock) {
        super(mat);
        registry = AtelierMod.getInstance().getRegistry();
        setBlockName(AtelierMod.MODID + "." + blockName);
        setBlockTextureName((AtelierMod.MODID + ":" + blockName).toLowerCase());
        setCreativeTab(TAB_ATELIER);
        GameRegistry.registerBlock(this, itemBlock, blockName);
    }
}

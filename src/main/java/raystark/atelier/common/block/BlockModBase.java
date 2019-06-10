package raystark.atelier.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import raystark.atelier.common.AtelierMod;
import raystark.atelier.common.block.itemblock.ItemBlockModBase;

import static raystark.atelier.common.AtelierMod.TAB_ATELIER;

public abstract class BlockModBase extends Block {

    protected BlockModBase(Material mat, String blockName, Class<? extends ItemBlockModBase> itemBlock) {
        super(mat);
        setBlockName(AtelierMod.MODID + "." + blockName);
        setBlockTextureName(AtelierMod.DOMAIN_NAME + ":" + blockName.toLowerCase());
        setCreativeTab(TAB_ATELIER);
        GameRegistry.registerBlock(this, itemBlock, blockName);
    }
}

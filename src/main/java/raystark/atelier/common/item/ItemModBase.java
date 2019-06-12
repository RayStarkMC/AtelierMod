package raystark.atelier.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import raystark.atelier.common.AtelierMod;

import static raystark.atelier.common.AtelierMod.TAB_ATELIER;

public abstract class ItemModBase extends Item {
    protected ItemModBase(String itemName, int defaultStackSize) {
        setUnlocalizedName(AtelierMod.MODID + "." + itemName);
        setCreativeTab(TAB_ATELIER);
        setMaxStackSize(defaultStackSize);
        setTextureName((AtelierMod.MODID + ":" + itemName).toLowerCase());

        GameRegistry.registerItem(this, itemName);
    }
}

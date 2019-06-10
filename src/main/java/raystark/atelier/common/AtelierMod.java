package raystark.atelier.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import raystark.atelier.common.block.AtelierBlocks;
import raystark.atelier.api.alchemy.effect.Effects;
import raystark.atelier.common.block.tile.SampleTileProduct;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.api.alchemy.potential.Potentials;

@Mod(modid = AtelierMod.MODID, version = AtelierMod.VERSION)
public class AtelierMod {
    public static final String MODID = "AtelierMod";
    public static final String DOMAIN_NAME = MODID.toLowerCase();
    public static final String VERSION = "0.1a";

    public static final CreativeTabs TAB_ATELIER = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return AtelierItems.samplePickaxe;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AtelierItems.init();
        AtelierBlocks.init();

        GameRegistry.registerTileEntity(SampleTileProduct.class, "testID");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Effects.init();
        Potentials.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
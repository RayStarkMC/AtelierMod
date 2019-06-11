package raystark.atelier.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.common.block.AtelierBlocks;
import raystark.atelier.common.block.tile.SampleTileProduct;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.registry.AtelierRegistry;

@Mod(modid = AtelierMod.MODID, version = AtelierMod.VERSION)
public class AtelierMod {
    public static final String MODID = "AtelierMod";
    public static final String VERSION = "0.1a";

    @Instance(MODID)
    private static AtelierMod instance;

    public static final CreativeTabs TAB_ATELIER = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return AtelierItems.samplePickaxe;
        }
    };

    public static AtelierMod getInstance() { return instance; }

    private IAtelierRegistry<Item, Block> atelierRegistry;

    public IAtelierRegistry<Item, Block> getRegistry() { return this.atelierRegistry; }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        atelierRegistry = new AtelierRegistry();
        AtelierItems.init();
        AtelierBlocks.init();

        GameRegistry.registerTileEntity(SampleTileProduct.class, "testID");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        atelierRegistry.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
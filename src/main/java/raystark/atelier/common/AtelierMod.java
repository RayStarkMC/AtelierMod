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
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.recipe.IAlchemicalRecipe;
import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.common.alchemy.recipe.RecipeBandage;
import raystark.atelier.common.block.AtelierBlocks;
import raystark.atelier.common.block.tile.SampleTileProduct;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.registry.AtelierRegistry;

@Mod(
        modid = AtelierMod.MODID,
        name = AtelierMod.NAME,
        version = AtelierMod.VERSION
)
public class AtelierMod {
    public static final String MODID = "AtelierMod";
    public static final String NAME = "AtelierMod";
    public static final String VERSION = "0.0.0.0";

    @Instance(MODID)
    private static AtelierMod instance;

    public static final CreativeTabs TAB_ATELIER = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return AtelierItems.bandage;
        }
    };

    public static AtelierMod getInstance() { return instance; }

    @InstanceFactory
    public static AtelierMod newInstance() { return new AtelierMod(); }

    private AtelierMod() {

    }

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
        recipe = new RecipeBandage();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    public IAlchemicalRecipe<ItemStack> recipe;

    private IAtelierRegistry<Item, Block, ItemStack> atelierRegistry;

    public IAtelierRegistry<Item, Block, ItemStack> getRegistry() { return this.atelierRegistry; }
}
package raystark.atelier.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import raystark.atelier.common.block.AtelierBlocks;
import raystark.atelier.common.effect.Effects;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.potential.Potentials;

@Mod(modid = AtelierMod.MODID, version = AtelierMod.VERSION)
public class AtelierMod {
    public static final String MODID = "ateliermod";
    public static final String VERSION = "0.1a";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AtelierItems.init();
        AtelierBlocks.init();
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
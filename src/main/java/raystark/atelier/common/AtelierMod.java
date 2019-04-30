package raystark.atelier.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ibxm.Sample;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import raystark.atelier.api.effect.Effects;
import raystark.atelier.common.effect.EffectDiamondMiningLevel;
import raystark.atelier.common.effect.EffectIronMiningLevel;
import raystark.atelier.common.effect.EffectStoneMiningLevel;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.item.SamplePickaxe;

@Mod(modid = AtelierMod.MODID, version = AtelierMod.VERSION)
public class AtelierMod {
    public static final String MODID = "ateliermod";
    public static final String VERSION = "0.1a";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AtelierItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Effects.init();
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
package raystark.atelier;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = AtelierMod.MODID, version = AtelierMod.VERSION)
public class AtelierMod {
    public static final String MODID = "ateliermod";
    public static final String VERSION = "0.1a";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
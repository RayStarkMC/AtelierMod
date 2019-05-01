package raystark.atelier.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AtelierBlocks {
    public static Block sampleBlock;
    private AtelierBlocks() {}

    public static void init() {
        sampleBlock = new SampleBlock(Material.clay);
    }
}

package raystark.atelier.common.block;

import net.minecraft.block.Block;

public class AtelierBlocks {
    public static Block sampleBlock;
    private AtelierBlocks() {}

    public static void init() {
        sampleBlock = new SampleBlock();
    }
}

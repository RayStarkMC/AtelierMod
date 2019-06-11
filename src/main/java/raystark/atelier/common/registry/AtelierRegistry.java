package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import raystark.atelier.api.registry.IMaterialRegistry;

public class AtelierRegistry extends AbstractAtelierRegistry<Item, Block> {
    IMaterialRegistry<Item, Block> materialRegistry;

    public AtelierRegistry() {
        materialRegistry = new MaterialRegistry();
    }

    public void init() {
        effectRegistry.init();
        abilityRegistry.init();
        materialRegistry.init();
    }

    @Override
    public IMaterialRegistry<Item, Block> getMaterialRegistry() {
        return materialRegistry;
    }
}

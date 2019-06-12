package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import raystark.atelier.api.registry.IMaterialRegistry;

public class AtelierRegistry extends AbstractAtelierRegistry<Item, Block> {
    private IMaterialRegistry<Item, Block> materialRegistry;
    private boolean hasInit;

    public AtelierRegistry() {
        materialRegistry = new MaterialRegistry();
        hasInit = false;
    }

    public void init() {
        if(!hasInit()) {
            effectRegistry.init();
            abilityRegistry.init();
            materialRegistry.init();
        }
        hasInit = true;
    }

    @Override
    public boolean hasInit() {
        return hasInit;
    }

    @Override
    public IMaterialRegistry<Item, Block> getMaterialRegistry() {
        return materialRegistry;
    }
}

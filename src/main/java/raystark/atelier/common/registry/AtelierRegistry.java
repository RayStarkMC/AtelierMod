package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.registry.ICategoryRegistry;
import raystark.atelier.api.registry.IMaterialRegistry;

public class AtelierRegistry extends AbstractAtelierRegistry<Item, Block, ItemStack> {
    private IMaterialRegistry<Item, Block> materialRegistry;
    private ICategoryRegistry<Item, Block, ItemStack> categoryRegistry;

    private boolean hasInit;

    public AtelierRegistry() {
        materialRegistry = new MaterialRegistry();
        categoryRegistry = new CategoryRegistry();
        hasInit = false;
    }

    public void init() {
        if(hasInit()) return;

        effectRegistry.init();
        abilityRegistry.init();
        materialRegistry.init();
        categoryRegistry.init();

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

    @Override
    public ICategoryRegistry<Item, Block, ItemStack> getCategoryRegistry() {
        return categoryRegistry;
    }
}

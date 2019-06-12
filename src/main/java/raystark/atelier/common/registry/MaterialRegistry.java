package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.common.alchemy.status.SimpleMaterialStatus;
import raystark.atelier.common.block.AtelierBlocks;
import raystark.atelier.common.item.AtelierItems;

import java.util.Collections;
import java.util.List;

public class MaterialRegistry extends AbstractMaterialRegistry<Item, Block> {
    private boolean hasInit;

    public MaterialRegistry() {
        super();
        hasInit = false;
    }

    @Override
    public void init() {
        if(!hasInit()) {
            @SuppressWarnings("unchecked") List<IPotentialAbility> EMPTY_LIST = Collections.EMPTY_LIST;

            registerDefaultBlockStatus(AtelierBlocks.sampleBlock, new SimpleMaterialStatus(10, EMPTY_LIST, 40, 40, 40, 40));
            registerDefaultItemStatus(AtelierItems.samplePickaxe, IMaterialStatus.DEFAULT_STATUS);
        }
        hasInit = true;
    }

    @Override
    public boolean hasInit() {
        return hasInit;
    }
}

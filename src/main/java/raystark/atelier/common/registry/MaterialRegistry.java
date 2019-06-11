package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ClassicalElement;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.common.block.AtelierBlocks;

import java.util.Collections;
import java.util.List;

public class MaterialRegistry extends AbstractMaterialRegistry<Item, Block> {
    @Override
    public void init() {
        registerDefaultBlockStatus(AtelierBlocks.sampleBlock, new IMaterialStatus() {
            @Override
            public int getElementValue(ClassicalElement elementType) {
                return 10;
            }

            @Override
            public List<IPotentialAbility> getPotentialAbilityList() {
                @SuppressWarnings("unchecked") List<IPotentialAbility> ret = Collections.EMPTY_LIST;
                return ret;
            }

            @Override
            public int getQuality() {
                return 40;
            }
        });
    }
}

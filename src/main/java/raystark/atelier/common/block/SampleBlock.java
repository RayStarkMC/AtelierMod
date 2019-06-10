package raystark.atelier.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.effect.Effects;

import java.util.List;

public class SampleBlock extends BlockProductBase {

    public SampleBlock(Material mat, String blockName) {
        super(mat, blockName, SampleItemBlock.class);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs p_150895_2_, List list) {
        IEffect[] effects = new IEffect[]{
                Effects.STONE_MINING_LEVEL,
                Effects.IRON_MINING_LEVEL,
                Effects.DIAMOND_MINING_LEVEL
        };

        //listはItemStackを格納しているためこのキャストは正しい
        @SuppressWarnings("unchecked") List<ItemStack> subItems = list;

        for (IEffect effect : effects)
            subItems.add(addEffect(applyDefaultTag(new ItemStack(item, 1, 0)), effect));
    }

    @Override
    public String getHarvestTool(int metadata) {
        return super.getHarvestTool(metadata);
    }
}

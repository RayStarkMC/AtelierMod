package raystark.atelier.common.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;

public class SampleBlockItem extends ItemBlock implements IAlchemicalProduct<ItemStack> {

    public SampleBlockItem(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        list.add("SampleBlock");
    }

    private boolean isBlockAlchemicalProduct(Block block) { return block instanceof IAlchemicalProduct;}

    @Override
    public IProductStatus getStatus(ItemStack dataSource) {
        return null;
    }
}

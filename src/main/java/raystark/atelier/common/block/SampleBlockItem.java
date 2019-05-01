package raystark.atelier.common.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.Quality;
import raystark.atelier.api.effect.IEffect;
import raystark.atelier.api.item.IAlchemicalProduct;
import raystark.atelier.api.potential.IPotentialAbility;

import java.util.List;

public class SampleBlockItem extends ItemBlock implements IAlchemicalProduct {

    public SampleBlockItem(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        list.add("SampleBlock");
    }

    @Override
    public int getQuality(ItemStack itemStack) {
        return isBlockAlchemicalProduct(field_150939_a) ? ((IAlchemicalProduct)field_150939_a).getQuality(itemStack) : Quality.MIN_VALUE;
    }

    @Override
    public List<IEffect> getEffectList(ItemStack stackAlchemy) {
        return ((IAlchemicalProduct)field_150939_a).getEffectList(stackAlchemy);
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList(ItemStack stackAlchemy) {
        return ((IAlchemicalProduct)field_150939_a).getPotentialAbilityList(stackAlchemy);
    }

    private boolean isBlockAlchemicalProduct(Block block) { return block instanceof IAlchemicalProduct;}
}

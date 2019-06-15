package raystark.atelier.common.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;
import raystark.atelier.common.registry.EffectRegistry;
import raystark.atelier.api.util.ToolClasses;

import java.util.*;

import static raystark.atelier.common.util.AtelierModUtil.addEffect;
import static raystark.atelier.common.util.AtelierModUtil.applyDefaultTag;

/**
 * 錬金術によって作られたピッケルのサンプル
 */
public class SamplePickaxe extends ItemProductBase implements IAlchemicalProduct<ItemStack> {
    public SamplePickaxe() {
        super("SamplePickaxe", 1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
        IEffect[] effects = new IEffect[]{
                EffectRegistry.EFFECT_STONE_MINING,
                EffectRegistry.EFFECT_IRON_MINING,
                EffectRegistry.EFFECT_DIAMOND_MINING
        };

        //listはItemStackを格納しているためこのキャストは正しい
        @SuppressWarnings("unchecked") List<ItemStack> subItems = list;

        for (IEffect effect : effects)
            subItems.add(addEffect(applyDefaultTag(new ItemStack(item, 1, 0)), effect));

        subItems.add(applyDefaultTag(new ItemStack(item, 1, 100)));
    }

    @Override
    public int getHarvestLevel(ItemStack itemStack, String toolClass) {
        if(itemStack == null || !(itemStack.getItem() instanceof SamplePickaxe)) {
            return -1;
        }

        if(!Objects.equals(toolClass, ToolClasses.PICKAXE.value())) {
            return -1;
        }

        return getStatus(itemStack).getEffectList().stream()
                .filter(e -> e instanceof IEffectMiningLevel)
                .findFirst()
                .map(e -> ((IEffectMiningLevel) e).getMiningLevel())
                .orElse(-1);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 1000;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase entityLivingBase) {
        p_150894_1_.damageItem(1, entityLivingBase);
        return super.onBlockDestroyed(p_150894_1_, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, entityLivingBase);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        return Objects.equals(block.getHarvestTool(metadata), ToolClasses.PICKAXE.value()) ? 8.0f : 1.0f;
    }
}

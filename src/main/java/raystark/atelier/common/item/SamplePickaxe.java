package raystark.atelier.common.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;
import raystark.atelier.api.util.ToolClasses;
import raystark.atelier.common.registry.EffectRegistry;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;
import java.util.Objects;

/**
 * 錬金術によって作られたピッケルのサンプル
 */
public class SamplePickaxe extends ItemProductBase implements IItemAlchemicalProduct<ItemStack> {
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

        for(IEffect effect : effects) {
            ItemStack stack = new ItemStack(item, 1, 0);
            stack.setTagCompound(AtelierModUtil.newAtelierTagBuilder().addEffect(effect).build());
            subItems.add(stack);
        }
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

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(world.isRemote) return itemStack;

        printMOP(getMovingObjectPositionFromPlayer(world, entityPlayer, false), world);
        printMOP(getMovingObjectPositionFromPlayer(world, entityPlayer, true), world);

        return itemStack;
    }

    private void printMOP(MovingObjectPosition mop, World world) {
        if(mop == null) {
            System.out.println("mop is null.");
            return;
        }

        Block block = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
        //ForgeDirection face = ForgeDirection.getOrientation(mop.sideHit);

        //System.out.printf("side: %d ForgeDir: %-5s Block: %s mop: %s%n", mop.sideHit, face, block, mop);
        System.out.println("mopF: Block=" + block + ", " +  mop);
    }
}

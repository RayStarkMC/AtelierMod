package raystark.atelier.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import raystark.atelier.api.alchemy.Quality;
import raystark.atelier.api.effect.IEffect;
import raystark.atelier.api.item.IAlchemicalProduct;
import raystark.atelier.api.potential.IPotentialAbility;
import raystark.atelier.api.util.NBTType;
import raystark.atelier.common.effect.Effects;
import raystark.atelier.common.potential.Potentials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SampleBlock extends Block implements IAlchemicalProduct{
    private static boolean hasAtelierTag(ItemStack itemStack) {
        return itemStack != null
                && itemStack.getItem() instanceof IAlchemicalProduct
                && itemStack.hasTagCompound()
                && itemStack.getTagCompound().hasKey("ModAtelier");
    }

    //テスト用ヘルパメソッド アイテムにエフェクトを付与
    private static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムにデフォルトのタグを付与
    private static ItemStack applyDefaultTag(ItemStack stack) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger("Quality", Quality.MIN_VALUE);
        tagAtelier.setTag("Effect", new NBTTagList());
        tagAtelier.setTag("Ability", new NBTTagList());

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag("ModAtelier", tagAtelier);

        stack.setTagCompound(tagCompound);
        return stack;
    }

    public SampleBlock(Material p_i45394_1_) {
        super(p_i45394_1_);
        setBlockName("SampleBlock");
        GameRegistry.registerBlock(this, SampleBlockItem.class, getUnlocalizedName());
        setCreativeTab(CreativeTabs.tabTools);
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

    @Override
    public int getQuality(ItemStack itemStack) {
        return hasAtelierTag(itemStack) ? itemStack.getTagCompound().getCompoundTag("ModAtelier").getInteger("Quality") : Quality.MIN_VALUE;
    }

    @Override
    public List<IEffect> getEffectList(ItemStack itemStack) {
        if(!hasAtelierTag(itemStack)) {
            @SuppressWarnings("unchecked") List<IEffect> list = Collections.EMPTY_LIST;
            return list;
        }

        List<IEffect> effectList = new ArrayList<>();

        NBTTagList tagList = itemStack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID());
        for(int i=0; i<tagList.tagCount() ;i++)
            Effects.getEffects(tagList.getStringTagAt(i)).ifPresent(effectList::add); //effectList.add(Effects.getEffects(tagList.getStringTagAt(i)));

        return effectList;
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList(ItemStack itemStack) {
        if(!hasAtelierTag(itemStack)) {
            @SuppressWarnings("unchecked") List<IPotentialAbility> list = Collections.EMPTY_LIST;
            return list;
        }

        List<IPotentialAbility> abilityList = new ArrayList<>();

        NBTTagList tagList = itemStack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Potential", NBTType.STRING.getID());
        for(int i=0; i<tagList.tagCount() ;i++)
            Potentials.getPotential(tagList.getStringTagAt(i)).ifPresent(abilityList::add);
        return abilityList;
    }
}

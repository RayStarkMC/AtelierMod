package raystark.atelier.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.effect.Effects;
import raystark.atelier.api.effect.IEffect;
import raystark.atelier.api.alchemy.IPotentialAbility;
import raystark.atelier.api.effect.IEffectMiningLevel;
import raystark.atelier.common.util.NBTType;

import java.util.*;

/**
 * 錬金術によって作られたピッケルのサンプル
 */
public class SamplePickaxe extends Item implements IAlchemicalProduct {

    public SamplePickaxe() {
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("samplePickaxe");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "samplePickaxe");
    }

    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
        //listはItemStackを格納しているためこのキャストは正しい
        @SuppressWarnings("unchecked") List<ItemStack> subItems = list;
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagCompound tagAtelier = new NBTTagCompound();

        tagCompound.setTag("ModAtelier", tagAtelier);

        NBTTagList tagEffectList = new NBTTagList();
        NBTTagList tagPotentialAbilityList = new NBTTagList();

        tagAtelier.setTag("Effect", tagEffectList);
        tagAtelier.setTag("PotentialAbility", tagPotentialAbilityList);

        NBTTagCompound[] tagCompounds = new NBTTagCompound[3];
        for(int i=0; i<tagCompounds.length; i++)
            tagCompounds[i] = (NBTTagCompound)tagCompound.copy();

        addEffect(tagCompounds[0], Effects.STONE_MINING_LEVEL);
        addEffect(tagCompounds[1], Effects.IRON_MINING_LEVEL);
        addEffect(tagCompounds[2], Effects.DIAMOND_MINING_LEVEL);

        ItemStack[] stack = new ItemStack[tagCompounds.length];
        for(int i=0; i<stack.length; i++) {
            stack[i] = new ItemStack(this, 1, 0);
            stack[i].setTagCompound(tagCompounds[i]);
            subItems.add(stack[i]);
        }
    }

    private void addEffect(NBTTagCompound tag, IEffect effect) {
        tag.getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        // listは文字列を格納したListであるためこのキャストは正しい
        @SuppressWarnings("unchecked") List<String> toolTipList = list;
        if(!GuiScreen.isShiftKeyDown()) return;

        NBTTagCompound tagAtelier = itemStack.getTagCompound().getCompoundTag("ModAtelier");

        toolTipList.add(EnumChatFormatting.RED + "[Effects]");
        NBTTagList effectList = tagAtelier.getTagList("Effect", NBTType.STRING.getID());
        for(int i=0;i < effectList.tagCount();i++)
            toolTipList.add(Effects.getEffects(effectList.getStringTagAt(i)).getToolTipText());

        toolTipList.add(EnumChatFormatting.LIGHT_PURPLE + "[PotentialAbilities]");
        NBTTagList potentialList = tagAtelier.getTagList("PotentialAbility", NBTType.STRING.getID());
        for(int i=0; i < potentialList.tagCount(); i++)
            toolTipList.add(potentialList.getStringTagAt(i));
    }

    @Override
    public List<IEffect> getEffectList(ItemStack itemStack) {
        if(itemStack == null || !isAlchemicalProduct(itemStack)) {
            @SuppressWarnings("unchecked") List<IEffect> list = Collections.EMPTY_LIST;
            return list;
        }


        // if(!(itemStack.getItem() instanceof IAlchemicalProduct)) return C;

        List<IEffect> list = new ArrayList<>();
            // TODO インターフェース実装
        return null;
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList(ItemStack itemStack) {
            // TODO インターフェース実装
        return null;
    }

    private boolean isAlchemicalProduct(ItemStack stack) {
        return stack.getItem() instanceof IAlchemicalProduct;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        if(stack == null || !(stack.getItem() instanceof SamplePickaxe)) {
            return -1;
        }

        if(toolClass == null) {
            return -1;
        }

        if(!stack.hasTagCompound()) {
            return -1;
        }

        NBTTagList list = stack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID());
        for(int i=0; i < list.tagCount(); i++) {
            IEffect effect = Effects.getEffects(list.getStringTagAt(i));
            if(effect instanceof IEffectMiningLevel)
                return ((IEffectMiningLevel)effect).getMiningLevel();
        }
        return -1;
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        return 8.0f;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}

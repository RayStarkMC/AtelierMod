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
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.ItemProductStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.alchemy.effect.Effects;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.effect.IEffectMiningLevel;
import raystark.atelier.api.util.ToolClasses;
import raystark.atelier.api.util.NBTType;

import java.util.*;
import java.util.stream.Collectors;

import static raystark.atelier.api.util.NBTTagNames.*;

/**
 * 錬金術によって作られたピッケルのサンプル
 */
public class SamplePickaxe extends Item implements IAlchemicalProduct<ItemStack> {

    //テスト用ヘルパメソッド アイテムにエフェクトを付与
    private static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag(TAG_MOD.name()).getTagList(TAG_EFFECT.name(), NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムにデフォルトのタグを付与
    private static ItemStack applyDefaultTag(ItemStack stack) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger(TAG_QUALITY.name(), Quality.MIN_VALUE);
        tagAtelier.setTag(TAG_EFFECT.name(), new NBTTagList());
        tagAtelier.setTag(TAG_POTENTIAL.name(), new NBTTagList());

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag(TAG_MOD.name(), tagAtelier);

        stack.setTagCompound(tagCompound);
        return stack;
    }

    public SamplePickaxe() {
        setUnlocalizedName("samplePickaxe");
        GameRegistry.registerItem(this, getUnlocalizedName());
        setCreativeTab(CreativeTabs.tabTools);
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
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
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isDebugMode) {
        if(!GuiScreen.isShiftKeyDown()) return;

        IProductStatus status = getStatus(itemStack);

        // listは文字列を格納したListであるためこのキャストは正しい
        @SuppressWarnings("unchecked") List<String> toolTipList = list;

        String quality = String.valueOf(status.getQuality());
        List<String> effectList = status.getEffectList().stream()
                .map(IEffect::getToolTipText)
                .collect(Collectors.toList());
        List<String> abilityList = status.getPotentialAbilityList().stream()
                .map(IPotentialAbility::getToolTipText)
                .collect(Collectors.toList());

        toolTipList.add(EnumChatFormatting.AQUA + "[Quality]");
        toolTipList.add(quality);
        toolTipList.add(EnumChatFormatting.AQUA + "[Effects]");
        toolTipList.addAll(effectList);
        toolTipList.add(EnumChatFormatting.AQUA + "[PotentialAbility]");
        toolTipList.addAll(abilityList);
    }

    @Override
    public int getHarvestLevel(ItemStack itemStack, String toolClass) {
        if(itemStack == null || !(itemStack.getItem() instanceof SamplePickaxe)) {
            return -1;
        }

        if(!Objects.equals(toolClass, ToolClasses.PICKAXE.value())) {
            return -1;
        }

        if(!itemStack.hasTagCompound()) {
            return -1;
        }

        List<IEffect> effectList = getStatus(itemStack).getEffectList();
        return effectList.stream()
                .filter(e -> e instanceof IEffectMiningLevel)
                .findFirst()
                .map(e -> ((IEffectMiningLevel) e).getMiningLevel())
                .orElse(-1);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        return Objects.equals(block.getHarvestTool(metadata), ToolClasses.PICKAXE.value()) ? 8.0f : 1.0f;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }

    @Override
    public IProductStatus getStatus(ItemStack dataSource) {
        return new ItemProductStatus(dataSource);
    }
}

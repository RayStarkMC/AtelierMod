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
import raystark.atelier.api.alchemy.Quality;
import raystark.atelier.api.effect.Effects;
import raystark.atelier.api.effect.IEffect;
import raystark.atelier.api.alchemy.IPotentialAbility;
import raystark.atelier.api.effect.IEffectMiningLevel;
import raystark.atelier.api.item.ToolClasses;
import raystark.atelier.common.util.NBTType;

import java.util.*;
import java.util.stream.Collectors;

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

    //テスト用ヘルパメソッド アイテムスタックに指定した効果を追加
    private static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムスタックにModデフォルトのタグを付与
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

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isDebugMode) {
        if(!GuiScreen.isShiftKeyDown()) return;

        // listは文字列を格納したListであるためこのキャストは正しい
        @SuppressWarnings("unchecked") List<String> toolTipList = list;
        List<String> effectList = getEffectList(itemStack).stream()
                .map(IEffect::getToolTipText)
                .collect(Collectors.toList());
        List<String> abilityList = getPotentialAbilityList(itemStack).stream()
                .map(IPotentialAbility::getToolTipText)
                .collect(Collectors.toList());

        toolTipList.add(EnumChatFormatting.AQUA + "[Effects]");
        toolTipList.addAll(effectList);
        toolTipList.add(EnumChatFormatting.AQUA + "[PotentialAbility]");
        toolTipList.addAll(abilityList);
    }

    @Override
    public int getQuality(ItemStack stack) {
        return 0;
    }


    //テスト用ヘルパメソッド
    private static boolean hasEffectOrPotentialList(ItemStack itemStack) {
        return itemStack != null
                && itemStack.getItem() instanceof IAlchemicalProduct
                && itemStack.hasTagCompound()
                && itemStack.getTagCompound().hasKey("ModAtelier");
    }

    @Override
    public List<IEffect> getEffectList(ItemStack itemStack) {
        if(!hasEffectOrPotentialList(itemStack)) {
            @SuppressWarnings("unchecked") List<IEffect> list = Collections.EMPTY_LIST;
            return list;
        }

        NBTTagList tagList = itemStack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID());

        List<IEffect> effectList = new ArrayList<>();
        for(int i=0; i<tagList.tagCount() ;i++)
            Effects.getEffects(tagList.getStringTagAt(i)).ifPresent(effectList::add); //effectList.add(Effects.getEffects(tagList.getStringTagAt(i)));

        return effectList;
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList(ItemStack itemStack) {
        if(!hasEffectOrPotentialList(itemStack)) {
            @SuppressWarnings("unchecked") List<IPotentialAbility> list = Collections.EMPTY_LIST;
            return list;
        }

        // TODO 潜在能力実装

        //List<IPotentialAbility> abilityList = new ArrayList<>();
        //for(int i=0; i<tagList.tagCount() ;i++)
        //    effectList.add(PotentialAbilities.getPotentialAbility(tagList.getStringTagAt(i)));

        //return abilityList;

        @SuppressWarnings("unchecked") List<IPotentialAbility> list = Collections.EMPTY_LIST;
        return list;
    }

    private boolean isAlchemicalProduct(ItemStack stack) {
        return stack.getItem() instanceof IAlchemicalProduct;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        if(stack == null || !(stack.getItem() instanceof SamplePickaxe)) {
            return -1;
        }

        if(!Objects.equals(toolClass, ToolClasses.PICKAXE.value())) {
            return -1;
        }

        if(!stack.hasTagCompound()) {
            return -1;
        }

        List<IEffect> effectList = this.getEffectList(stack);
        return effectList.stream()
                .filter(e -> e instanceof IEffectMiningLevel)
                .findFirst()
                .map(e -> ((IEffectMiningLevel) e).getMiningLevel())
                .orElse(-1);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        return block.getHarvestTool(metadata).equals(ToolClasses.PICKAXE.value()) ? 8.0f : 1.0f;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}

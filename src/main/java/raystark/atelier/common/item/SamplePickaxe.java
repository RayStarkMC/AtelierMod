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
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.IEffect;
import raystark.atelier.api.alchemy.IPotentialAbility;
import raystark.atelier.common.util.NBTType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        @SuppressWarnings("unchecked") List<ItemStack> subItems = (List<ItemStack>)list;
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagCompound tagAtelier = new NBTTagCompound();

        tagCompound.setTag("ModAtelier", tagAtelier);

        NBTTagList tagEffectList = new NBTTagList();
        NBTTagList tagPotentialAbilityList = new NBTTagList();

        tagAtelier.setTag("Effect", tagEffectList);
        tagAtelier.setTag("PotentialAbility", tagPotentialAbilityList);


        tagEffectList.appendTag(new NBTTagString("TestEffect1"));
        tagEffectList.appendTag(new NBTTagString("TestEffect2"));

        tagPotentialAbilityList.appendTag(new NBTTagString("testPotential1"));
        tagPotentialAbilityList.appendTag(new NBTTagString("testPotential2"));


        ItemStack stack = new ItemStack(this, 1, 0);
        stack.setTagCompound(tagCompound);

        subItems.add(stack);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        // listは文字列を格納したListであるためこのキャストは正しい
        @SuppressWarnings("unchecked") List<String> toolTipList = (List<String>)list;
        if(!GuiScreen.isShiftKeyDown()) return;

        NBTTagCompound tagAtelier = itemStack.getTagCompound().getCompoundTag("ModAtelier");

        toolTipList.add("[Effects]");
        NBTTagList effectList = tagAtelier.getTagList("Effect", NBTType.STRING.getID());
        for(int i=0;i < effectList.tagCount();i++)
            toolTipList.add(effectList.getStringTagAt(i));

        toolTipList.add("[PotentialAbilities]");
        NBTTagList potentialList = tagAtelier.getTagList("PotentialAbility", NBTType.STRING.getID());
        for(int i=0; i < potentialList.tagCount(); i++)
            toolTipList.add(potentialList.getStringTagAt(i));
    }

    @Override
    public List<IEffect> getEffectList(ItemStack itemStack) {
        List<IEffect> list = new ArrayList<>();

        return null;
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList(ItemStack itemStack) {
        return null;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        System.out.println("[test]getHarvestLevel" + toolClass);

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
        for(int i=0; i < list.tagCount(); i++)
            if (list.getStringTagAt(i).equals("TestEffect1"))
                return 2;
        return -1;
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        if(!itemstack.hasTagCompound())
            return 1.0f;

        if(!Objects.equals(block.getHarvestTool(metadata), "pickaxe"))
            return 1.0f;

        NBTTagList list = itemstack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID());
        for(int i=0; i < list.tagCount(); i++)
            if (list.getStringTagAt(i).equals("TestEffect2"))
                return 5.0f;

        return super.getDigSpeed(itemstack, block, metadata);
    }
}

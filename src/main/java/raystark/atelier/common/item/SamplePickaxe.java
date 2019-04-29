package raystark.atelier.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.IEffect;
import raystark.atelier.api.alchemy.IPotentialAbility;

import java.util.ArrayList;
import java.util.List;

/**
 * 錬金術によって作られたピッケルのサンプル
 */
public class SamplePickaxe extends ItemPickaxe implements IAlchemicalProduct {

    public SamplePickaxe() {
        super(ToolMaterial.IRON);
        setCreativeTab(CreativeTabs.tabTools);
        GameRegistry.registerItem(this, "samplePickaxe");
        setUnlocalizedName("samplePickaxe");
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List p_150895_3_) {
        ItemStack stack = new ItemStack(this, 1, 0);
        System.out.println(item == this);

    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        // listは文字列を格納したListであるためこのキャストは正しい
        @SuppressWarnings("unchecked") List<String> toolTipList = (List<String>)list;
        if(GuiScreen.isShiftKeyDown())
            toolTipList.add("Effects");

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
}

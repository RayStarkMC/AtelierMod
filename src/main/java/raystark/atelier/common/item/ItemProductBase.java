package raystark.atelier.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.common.alchemy.status.ItemProductStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.common.util.ItemUtil;
import raystark.atelier.common.util.NBTType;

import java.util.List;

import static raystark.atelier.api.util.NBTTagNames.*;
import static raystark.atelier.api.util.NBTTagNames.TAG_ATELIER;

public abstract class ItemProductBase extends ItemModBase implements IAlchemicalProduct<ItemStack> {

    //テスト用ヘルパメソッド アイテムにエフェクトを付与
    protected static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag(TAG_ATELIER.name()).getTagList(TAG_EFFECT.name(), NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムにデフォルトのタグを付与
    protected static ItemStack applyDefaultTag(ItemStack stack) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger(TAG_QUALITY.name(), Quality.MIN_VALUE);
        tagAtelier.setTag(TAG_EFFECT.name(), new NBTTagList());
        tagAtelier.setTag(TAG_POTENTIAL.name(), new NBTTagList());

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag(TAG_ATELIER.name(), tagAtelier);

        stack.setTagCompound(tagCompound);
        return stack;
    }

    protected ItemProductBase(String itemName, int stackSize) {
        super(itemName, stackSize);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isDebugMode) {
        ItemUtil.addProductInformation(itemStack, entityPlayer, list, isDebugMode);
    }

    @Override
    public IProductStatus getStatus(ItemStack dataSource) {
        return new ItemProductStatus(dataSource);
    }
}

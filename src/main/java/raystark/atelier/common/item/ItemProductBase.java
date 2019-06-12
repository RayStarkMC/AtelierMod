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
import raystark.atelier.common.util.AtelierModUtil;
import raystark.atelier.common.util.NBTType;

import java.util.List;

import static raystark.atelier.common.util.NBTTagNames.*;
import static raystark.atelier.common.util.NBTTagNames.TAG_ATELIER;

public abstract class ItemProductBase extends ItemModBase implements IAlchemicalProduct<ItemStack> {
    protected ItemProductBase(String itemName, int stackSize) {
        super(itemName, stackSize);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isDebugMode) {
        AtelierModUtil.addProductInformation(itemStack, entityPlayer, list, isDebugMode);
    }

    @Override
    public IProductStatus getStatus(ItemStack dataSource) {
        return new ItemProductStatus(dataSource);
    }
}

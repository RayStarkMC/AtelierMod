package raystark.atelier.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.common.alchemy.status.ItemProductStatus;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;

public abstract class ItemProductBase extends ItemModBase implements ItemAlchemicalProduct<ItemStack> {
    protected ItemProductBase(String itemName, int defaultStackSize) {
        super(itemName, defaultStackSize);
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

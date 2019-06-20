package raystark.atelier.common.alchemy.status;

import net.minecraft.item.ItemStack;

public class ItemProductStatus extends ModProductStatusBase {
    public ItemProductStatus(ItemStack dataSource) {
        super();
        if(dataSource == null) throw new NullPointerException("dataSource must not be null.");
        readFromNBT(dataSource.getTagCompound());
    }
}

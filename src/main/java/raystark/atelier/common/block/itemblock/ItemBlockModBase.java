package raystark.atelier.common.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.ItemProductStatus;
import raystark.atelier.common.util.ItemUtil;
import raystark.atelier.common.block.tile.SampleTileProduct;

import java.util.List;

public abstract class ItemBlockModBase extends ItemBlock  implements IAlchemicalProduct<ItemStack> {
    protected ItemBlockModBase(Block block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if(!ret) return false;

        ((SampleTileProduct)world.getTileEntity(x, y, z)).readStatusFromNBT(stack.getTagCompound());
        return true;
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

package raystark.atelier.common.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.common.alchemy.status.NBTReadableStatus;
import raystark.atelier.common.block.tile.AbstractTileProduct;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;

public abstract class ItemBlockProductBase extends ItemBlockModBase implements ItemAlchemicalProduct<ItemStack> {
    protected ItemBlockProductBase(Block block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if(!super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata)) return false;

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(!(tileEntity instanceof AbstractTileProduct))
            return false;

        ((AbstractTileProduct)tileEntity).readStatusFromNBT(stack.getTagCompound());
        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isDebugMode) {
        AtelierModUtil.addProductInformation(itemStack, entityPlayer, list, isDebugMode);
    }

    @Override
    public IProductStatus getStatus(ItemStack stack) {
        NBTReadableStatus status = new NBTReadableStatus();
        status.readFromNBT(stack.getTagCompound());
        return status;
    }
}

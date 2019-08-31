package raystark.atelier.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import raystark.atelier.common.alchemy.status.NBTWritableProductStatus;
import raystark.atelier.api.alchemy.status.IProductStatus;

public abstract class AbstractTileProduct extends TileEntity {
    private NBTWritableProductStatus status;

    protected AbstractTileProduct() {
        this.status = new NBTWritableProductStatus();
    }

    public IProductStatus getStatus() {
        return status;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        readStatusFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        writeStatusToNBT(tagCompound);
    }

    public void readStatusFromNBT(NBTTagCompound tagCompound) {
        status.readFromNBT(tagCompound);
    }

    public void writeStatusToNBT(NBTTagCompound tagCompound) {
        status.writeToNBT(tagCompound);
    }
}

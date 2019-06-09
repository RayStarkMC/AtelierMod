package raystark.atelier.api.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import raystark.atelier.api.alchemy.status.BlockProductStatus;
import raystark.atelier.api.alchemy.status.IProductStatus;

public abstract class AbstractTileProduct extends TileEntity {
    private IProductStatus status;

    protected AbstractTileProduct() {
        this.status = new BlockProductStatus(this);
    }

    public IProductStatus getStatus() {
        return status;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        readStatus(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        ((BlockProductStatus)status).writeToNBT(tagCompound);
    }

    public void readStatus(NBTTagCompound tagCompound) {
        ((BlockProductStatus)status).readFromNBT(tagCompound);
    }
}

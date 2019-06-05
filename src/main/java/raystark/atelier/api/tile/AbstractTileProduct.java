package raystark.atelier.api.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import raystark.atelier.api.alchemy.status.BlockProductStatus;

public abstract class AbstractTileProduct extends TileEntity {
    private BlockProductStatus status;

    protected AbstractTileProduct() {
        this.status = new BlockProductStatus(this);
    }

    public BlockProductStatus getStatus() {
        return status;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        ((BlockProductStatus)status).readFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        ((BlockProductStatus)status).writeToNBT(tagCompound);
    }
}

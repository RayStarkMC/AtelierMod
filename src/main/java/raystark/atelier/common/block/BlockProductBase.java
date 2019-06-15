package raystark.atelier.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.common.block.tile.AbstractTileProduct;
import raystark.atelier.common.block.itemblock.ItemBlockProductBase;
import raystark.atelier.common.block.tile.SampleTileProduct;

import java.util.ArrayList;

public abstract class BlockProductBase extends BlockModBase implements ITileEntityProvider, IAlchemicalProduct<AbstractTileProduct> {

    private static final ArrayList<ItemStack> EMPTY_LIST = new ArrayList<>(0);

    protected BlockProductBase(Material mat, String blockName, Class<? extends ItemBlockProductBase> itemBlock) {
        super(mat, blockName, itemBlock);
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer p_149681_6_) {
        if(!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(!(tileEntity instanceof AbstractTileProduct)) return;

            ItemStack stack = new ItemStack(this, 1, meta);
            stack.setTagCompound(((AbstractTileProduct)tileEntity).writeStatusToNBT(new NBTTagCompound()));

            world.spawnEntityInWorld(new EntityItem(world, x+.5, y+.5, z+.5, stack));
        }

        super.onBlockHarvested(world, x, y, z, meta, p_149681_6_);
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return EMPTY_LIST;
    }

    @Override
    public IProductStatus getStatus(AbstractTileProduct dataSource) {
        return dataSource.getStatus();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new SampleTileProduct();
    }
}

package raystark.atelier.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IBlockAlchemicalProduct;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.common.block.tile.AbstractTileProduct;
import raystark.atelier.common.block.itemblock.ItemBlockProductBase;

import java.util.ArrayList;
//TODO BlockModBaseの継承解除
public abstract class BlockProductBase extends BlockModBase implements ITileEntityProvider, IBlockAlchemicalProduct<World> {

    private static final ArrayList<ItemStack> EMPTY_DROPS = new ArrayList<>(0);

    protected BlockProductBase(Material mat, String blockName, Class<? extends ItemBlockProductBase> itemBlock) {
        super(mat, blockName, itemBlock);
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer p_149681_6_) {
        if(!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if(!(tileEntity instanceof AbstractTileProduct)) return;

            AbstractTileProduct tileProduct = (AbstractTileProduct)tileEntity;

            ItemStack stack = new ItemStack(this, 1, meta);
            NBTTagCompound tagCompound = new NBTTagCompound();
            tileProduct.writeStatusToNBT(tagCompound);
            stack.setTagCompound(tagCompound);

            world.spawnEntityInWorld(new EntityItem(world, x+.5, y+.5, z+.5, stack));
        }

        super.onBlockHarvested(world, x, y, z, meta, p_149681_6_);
    }

    @Override
    protected final boolean canSilkHarvest() {
        return false;
    }

    @Override
    public final ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return EMPTY_DROPS;
    }

    @Override
    public IProductStatus getStatus(int x, int y, int z, World world) {
        return ((AbstractTileProduct)world.getTileEntity(x, y, z)).getStatus();
    }
}

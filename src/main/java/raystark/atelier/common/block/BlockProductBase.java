package raystark.atelier.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.common.block.tile.AbstractTileProduct;
import raystark.atelier.common.util.NBTType;
import raystark.atelier.common.block.itemblock.ItemBlockProductBase;
import raystark.atelier.common.block.tile.SampleTileProduct;

import java.util.ArrayList;

import static raystark.atelier.api.util.NBTTagNames.*;
import static raystark.atelier.api.util.NBTTagNames.TAG_ATELIER;

public abstract class BlockProductBase extends BlockModBase implements ITileEntityProvider, IAlchemicalProduct<AbstractTileProduct> {

    private static final ArrayList<ItemStack> EMPTY_LIST = new ArrayList<>(0);

    //テスト用ヘルパメソッド アイテムにエフェクトを付与
    protected static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag(TAG_ATELIER.name()).getTagList(TAG_EFFECT.name(), NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムにデフォルトのタグを付与
    protected static ItemStack applyDefaultTag(ItemStack stack) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger(TAG_QUALITY.name(), 10);
        tagAtelier.setTag(TAG_EFFECT.name(), new NBTTagList());
        tagAtelier.setTag(TAG_POTENTIAL.name(), new NBTTagList());

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag(TAG_ATELIER.name(), tagAtelier);

        stack.setTagCompound(tagCompound);
        return stack;
    }

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

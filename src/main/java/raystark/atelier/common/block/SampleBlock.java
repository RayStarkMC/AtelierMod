package raystark.atelier.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.util.NBTType;
import raystark.atelier.api.alchemy.effect.Effects;

import java.util.List;

public class SampleBlock extends Block implements IAlchemicalProduct<TileEntity>{

    //テスト用ヘルパメソッド アイテムにエフェクトを付与
    private static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag("ModAtelier").getTagList("Effect", NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムにデフォルトのタグを付与
    private static ItemStack applyDefaultTag(ItemStack stack) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger("Quality", Quality.MIN_VALUE);
        tagAtelier.setTag("Effect", new NBTTagList());
        tagAtelier.setTag("Ability", new NBTTagList());

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag("ModAtelier", tagAtelier);

        stack.setTagCompound(tagCompound);
        return stack;
    }

    public SampleBlock(Material p_i45394_1_) {
        super(p_i45394_1_);
        setBlockName("SampleBlock");
        GameRegistry.registerBlock(this, SampleBlockItem.class, getUnlocalizedName());
        setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs p_150895_2_, List list) {
        IEffect[] effects = new IEffect[]{
                Effects.STONE_MINING_LEVEL,
                Effects.IRON_MINING_LEVEL,
                Effects.DIAMOND_MINING_LEVEL
        };

        //listはItemStackを格納しているためこのキャストは正しい
        @SuppressWarnings("unchecked") List<ItemStack> subItems = list;

        for (IEffect effect : effects)
            subItems.add(addEffect(applyDefaultTag(new ItemStack(item, 1, 0)), effect));
    }

    @Override
    public String getHarvestTool(int metadata) {
        return super.getHarvestTool(metadata);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        /*if(!world.isRemote)*/System.out.println("harvestBlock");
        super.harvestBlock(world, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer p_149681_6_) {
        if(!world.isRemote) {
            System.out.println("onBlockHarvested");
            world.spawnEntityInWorld(new EntityItem(world, x+.5, y+.5, z+.5, new ItemStack(Blocks.dirt, 1, meta)));
        }
        super.onBlockHarvested(world, x, y, z, meta, p_149681_6_);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public IProductStatus getStatus(TileEntity dataSource) {
        return null;
    }
}
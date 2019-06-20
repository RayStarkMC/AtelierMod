package raystark.atelier.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.category.Category;
import raystark.atelier.common.block.itemblock.SampleItemBlock;
import raystark.atelier.common.block.tile.SampleTileProduct;
import raystark.atelier.common.registry.EffectRegistry;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;

public class SampleBlock extends BlockProductBase {
    public SampleBlock() {
        super(Material.clay, "sampleBlock", SampleItemBlock.class);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs p_150895_2_, List list) {
        IEffect[] effects = new IEffect[]{
                EffectRegistry.EFFECT_STONE_MINING,
                EffectRegistry.EFFECT_IRON_MINING,
                EffectRegistry.EFFECT_DIAMOND_MINING
        };
        //listはItemStackを格納しているためこのキャストは正しい
        @SuppressWarnings("unchecked") List<ItemStack> subItems = list;

        for (IEffect effect : effects) {
            ItemStack stack = new ItemStack(item, 1, 0);
            stack.setTagCompound(AtelierModUtil.newTagBuilder().addEffect(effect).build());
            subItems.add(stack);
        }
    }

    @Override
    public String getHarvestTool(int metadata) {
        return super.getHarvestTool(metadata);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float sideX, float sideY, float sideZ) {
        if(!world.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + String.valueOf(registry.getCategoryRegistry().isItemStackBelongToCategory(new ItemStack(this, 1, 0), Category.METAL))));
            /*IMaterialStatus status = registry.getMaterialRegistry().getDefaultBlockStatus(this);

            List<String> chatlist = new ArrayList<>();
            chatlist.add("Status of Material");
            chatlist.add("[Quality] " + String.valueOf(status.getQuality()));
            chatlist.add("");
            chatlist.add("[Element]");
            chatlist.add(String.valueOf(EnumChatFormatting.RED + "[" + Elements.FIRE + "] " + String.valueOf(status.getElementValue(Elements.FIRE))));
            chatlist.add(String.valueOf(EnumChatFormatting.AQUA + "[" + Elements.WATER + "] " + String.valueOf(status.getElementValue(Elements.WATER))));
            chatlist.add(String.valueOf(EnumChatFormatting.YELLOW + "[" + Elements.AIR + "] " + String.valueOf(status.getElementValue(Elements.AIR))));
            chatlist.add(String.valueOf(EnumChatFormatting.GREEN + "[" + Elements.EARTH + "] " + String.valueOf(status.getElementValue(Elements.EARTH))));
            chatlist.add("");
            chatlist.add("[PotentialAbility]");
            for (IPotentialAbility abilitty : status.getPotentialAbilityList())
                chatlist.add(abilitty.getName());

            for(String text : chatlist)
                player.addChatComponentMessage(new ChatComponentText(text));
            */

        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new SampleTileProduct();
    }
}

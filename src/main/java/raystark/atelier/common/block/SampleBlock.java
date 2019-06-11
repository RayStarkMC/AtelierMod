package raystark.atelier.common.block;

import javafx.scene.effect.Effect;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.ClassicalElement;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.common.block.itemblock.SampleItemBlock;
import raystark.atelier.common.registry.EffectRegistry;

import java.util.ArrayList;
import java.util.List;

public class SampleBlock extends BlockProductBase {

    public SampleBlock(Material mat, String blockName) {
        super(mat, blockName, SampleItemBlock.class);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs p_150895_2_, List list) {
        IEffect[] effects = new IEffect[]{
                EffectRegistry.STONE_MINING_LEVEL,
                EffectRegistry.IRON_MINING_LEVEL,
                EffectRegistry.DIAMOND_MINING_LEVEL
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float sideX, float sideY, float sideZ) {
        if(!world.isRemote) {
            IMaterialStatus status = registry.getMaterialRegistry().getDefaultBlockStatus(this);

            List<String> chatlist = new ArrayList<>();
            chatlist.add("Status of Material");
            chatlist.add("[Quality] " + String.valueOf(status.getQuality()));
            chatlist.add("");
            chatlist.add("[Element]");
            chatlist.add(String.valueOf(EnumChatFormatting.RED + "[" + ClassicalElement.FIRE + "] " + String.valueOf(status.getElementValue(ClassicalElement.FIRE))));
            chatlist.add(String.valueOf(EnumChatFormatting.AQUA + "[" + ClassicalElement.WATER + "] " + String.valueOf(status.getElementValue(ClassicalElement.WATER))));
            chatlist.add(String.valueOf(EnumChatFormatting.YELLOW + "[" + ClassicalElement.AIR + "] " + String.valueOf(status.getElementValue(ClassicalElement.AIR))));
            chatlist.add(String.valueOf(EnumChatFormatting.GREEN + "[" + ClassicalElement.EARTH + "] " + String.valueOf(status.getElementValue(ClassicalElement.EARTH))));
            chatlist.add("");
            chatlist.add("[PotentialAbility]");
            for (IPotentialAbility abilitty : status.getPotentialAbilityList())
                chatlist.add(abilitty.getName());

            for(String text : chatlist)
                player.addChatComponentMessage(new ChatComponentText(text));

        }
        return true;
    }
}

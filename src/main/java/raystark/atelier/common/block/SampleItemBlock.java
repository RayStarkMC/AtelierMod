package raystark.atelier.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.ItemProductStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;
import java.util.stream.Collectors;

public class SampleItemBlock extends ItemBlock implements IAlchemicalProduct<ItemStack> {

    public SampleItemBlock(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isDebugMode) {
        if(!GuiScreen.isShiftKeyDown()) return;

        IProductStatus status = getStatus(itemStack);

        // listは文字列を格納したListであるためこのキャストは正しい
        @SuppressWarnings("unchecked") List<String> toolTipList = list;

        String quality = String.valueOf(status.getQuality());
        List<String> effectList = status.getEffectList().stream()
                .map(IEffect::getToolTipText)
                .collect(Collectors.toList());
        List<String> abilityList = status.getPotentialAbilityList().stream()
                .map(IPotentialAbility::getToolTipText)
                .collect(Collectors.toList());

        toolTipList.add(EnumChatFormatting.AQUA + "[Quality]");
        toolTipList.add(quality);
        toolTipList.add(EnumChatFormatting.AQUA + "[Effects]");
        toolTipList.addAll(effectList);
        toolTipList.add(EnumChatFormatting.AQUA + "[PotentialAbility]");
        toolTipList.addAll(abilityList);
    }
    @Override
    public IProductStatus getStatus(ItemStack dataSource) {
        return new ItemProductStatus(dataSource);
    }
}

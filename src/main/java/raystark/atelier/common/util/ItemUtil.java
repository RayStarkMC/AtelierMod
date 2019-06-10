package raystark.atelier.common.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.IProductStatus;

import java.util.List;
import java.util.stream.Collectors;

public final class ItemUtil {
    private ItemUtil() { }

    public static void addProductInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<?> list, boolean isDebugMode) {
        if(!(itemStack.getItem() instanceof IAlchemicalProduct)) {
            throw new IllegalArgumentException("ItemStack is not Alchemical Product!");
        }

        if(!GuiScreen.isShiftKeyDown()) return;

        @SuppressWarnings("unchecked") IProductStatus status = ((IAlchemicalProduct<ItemStack>)itemStack.getItem()).getStatus(itemStack);
        @SuppressWarnings("unchecked") List<String> toolTip = (List<String>)list;

        String quality = String.valueOf(status.getQuality());
        List<String> effectList = status.getEffectList().stream()
                .map(IEffect::getToolTipText)
                .collect(Collectors.toList());
        List<String> abilityList = status.getPotentialAbilityList().stream()
                .map(IPotentialAbility::getToolTipText)
                .collect(Collectors.toList());

        toolTip.add(EnumChatFormatting.AQUA + "[Quality]");
        toolTip.add(quality);
        toolTip.add(EnumChatFormatting.AQUA + "[Effects]");
        toolTip.addAll(effectList);
        toolTip.add(EnumChatFormatting.AQUA + "[PotentialAbility]");
        toolTip.addAll(abilityList);
    }
}

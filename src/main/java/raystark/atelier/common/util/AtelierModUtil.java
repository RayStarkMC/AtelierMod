package raystark.atelier.common.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.Quality;

import java.util.List;
import java.util.stream.Collectors;

import static raystark.atelier.api.util.NBTTagNames.*;
import static raystark.atelier.api.util.NBTTagNames.TAG_ATELIER;

public final class AtelierModUtil {
    private AtelierModUtil() { }

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

    //テスト用ヘルパメソッド アイテムにエフェクトを付与
    public static ItemStack addEffect(ItemStack itemStack, IEffect effect) {
        itemStack.getTagCompound().getCompoundTag(TAG_ATELIER.name()).getTagList(TAG_EFFECT.name(), NBTType.STRING.getID()).appendTag(new NBTTagString(effect.getName()));
        return itemStack;
    }

    //テスト用ヘルパメソッド アイテムにデフォルトのタグを付与
    public static ItemStack applyDefaultTag(ItemStack stack) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger(TAG_QUALITY.name(), Quality.MIN_VALUE);
        tagAtelier.setTag(TAG_EFFECT.name(), new NBTTagList());
        tagAtelier.setTag(TAG_POTENTIAL.name(), new NBTTagList());

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag(TAG_ATELIER.name(), tagAtelier);

        stack.setTagCompound(tagCompound);
        return stack;
    }
}

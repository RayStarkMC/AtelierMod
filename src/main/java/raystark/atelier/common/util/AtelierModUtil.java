package raystark.atelier.common.util;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.Quality;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static raystark.atelier.api.util.NBTTagNames.*;

public final class AtelierModUtil {
    private AtelierModUtil() { }

    public static void addProductInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<?> list, boolean isDebugMode) {
        if(!(itemStack.getItem() instanceof ItemAlchemicalProduct)) {
            throw new IllegalArgumentException("ItemStack is not Alchemical Product!");
        }

        if(!GuiScreen.isShiftKeyDown()) return;

        @SuppressWarnings("unchecked") IProductStatus status = ((ItemAlchemicalProduct<ItemStack>)itemStack.getItem()).getStatus(itemStack);
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

    public static AtelierTagBuilder newTagBuilder() {
        return newTagBuilder(new NBTTagCompound());
    }

    public static AtelierTagBuilder newTagBuilder(NBTTagCompound tagCompound) {
        return new AtelierTagBuilder(tagCompound);
    }

    public static class AtelierTagBuilder {
        private final NBTTagCompound tagCompound;
        private int quality;
        private final List<IEffect> effects;
        private final List<IPotentialAbility> abilities;

        private AtelierTagBuilder(NBTTagCompound tagCompound) {
            this.tagCompound = tagCompound;
            quality = Quality.MIN_VALUE;
            effects = new ArrayList<>();
            abilities = new ArrayList<>();
        }

        public AtelierTagBuilder setQuality(int quality) {
            if(quality < Quality.MIN_VALUE) throw new IllegalArgumentException("quality must be positive.");
            this.quality = quality;
            return this;
        }

        public AtelierTagBuilder addEffect(IEffect effect) {
            effects.add(Optional.ofNullable(effect).orElseThrow(() -> new NullPointerException("effect must not be null.")));
            return this;
        }

        public AtelierTagBuilder addAllEffects(Collection<? extends IEffect> effects) {
            this.effects.addAll(effects);
            return this;
        }

        public AtelierTagBuilder addPotentialAbility(IPotentialAbility ability) {
            abilities.add(Optional.ofNullable(ability).orElseThrow(() -> new NullPointerException("effect must not be null.")));
            return this;
        }

        public AtelierTagBuilder addAllPotentials(Collection<? extends IPotentialAbility> abilities) {
            this.abilities.addAll(abilities);
            return this;
        }

        public NBTTagCompound build() {
            NBTTagCompound tagAtelier = new NBTTagCompound();
            NBTTagList tagEffects = new NBTTagList();
            NBTTagList tagPotentials = new NBTTagList();

            this.tagCompound.setTag(TAG_ATELIER.name(), tagAtelier);

            tagAtelier.setInteger(TAG_QUALITY.name(), quality);
            tagAtelier.setTag(TAG_EFFECT.name(), tagEffects);
            tagAtelier.setTag(TAG_POTENTIAL.name(), tagPotentials);

            for(IEffect effect : effects)
                tagEffects.appendTag(new NBTTagString(effect.getName()));

            for(IPotentialAbility ability : abilities)
                tagPotentials.appendTag(new NBTTagString(ability.getName()));

            return this.tagCompound;
        }
    }
}

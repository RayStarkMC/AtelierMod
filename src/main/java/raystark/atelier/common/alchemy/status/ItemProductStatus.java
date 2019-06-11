package raystark.atelier.common.alchemy.status;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.common.util.NBTType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static raystark.atelier.api.util.NBTTagNames.*;

public class ItemProductStatus extends ProductStatus<ItemStack> {
    private boolean isQualityChecked;

    public ItemProductStatus(ItemStack dataSource) {
        super(dataSource);
        isQualityChecked = false;
    }

    @Override
    public int getQuality() {
        if(!isQualityChecked) {
            isQualityChecked = true;
            quality = hasSourceAtelierTag() ? dataSource.getTagCompound().getCompoundTag(TAG_ATELIER.name()).getInteger(TAG_QUALITY.name()) : Quality.MIN_VALUE;
        }
        return quality;
    }

    @Override
    public List<IEffect> getEffectList() {
        if(effectList == null) {
            if(!hasSourceAtelierTag()) {
                @SuppressWarnings("unchecked")  List<IEffect> l = Collections.EMPTY_LIST;
                effectList = l;
            } else {
                effectList = new ArrayList<>();

                NBTTagList tagList = dataSource.getTagCompound().getCompoundTag(TAG_ATELIER.name()).getTagList(TAG_EFFECT.name(), NBTType.STRING.getID());
                for (int i = 0; i < tagList.tagCount(); i++)
                    effectRegistry.getEffect(tagList.getStringTagAt(i)).ifPresent(effectList::add); //effectList.add(Effects.getEffect(tagList.getStringTagAt(i)));
            }
        }
        return Collections.unmodifiableList(effectList);
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList() {
        if(potentialAbilityList == null) {
            if(!hasSourceAtelierTag()) {
                @SuppressWarnings("unchecked")  List<IPotentialAbility> l = Collections.EMPTY_LIST;
                potentialAbilityList = l;
            } else {
                potentialAbilityList = new ArrayList<>();

                NBTTagList tagList = dataSource.getTagCompound().getCompoundTag(TAG_ATELIER.name()).getTagList(TAG_POTENTIAL.name(), NBTType.STRING.getID());
                for(int i=0; i<tagList.tagCount() ;i++)
                    abilityRegistry.getPotentialAbility(tagList.getStringTagAt(i)).ifPresent(potentialAbilityList::add);
            }
        }

        return Collections.unmodifiableList(potentialAbilityList);
    }

    private boolean hasSourceAtelierTag() {
        return dataSource.getItem() instanceof IAlchemicalProduct
                && dataSource.hasTagCompound()
                && dataSource.getTagCompound().hasKey(TAG_ATELIER.name());
    }
}

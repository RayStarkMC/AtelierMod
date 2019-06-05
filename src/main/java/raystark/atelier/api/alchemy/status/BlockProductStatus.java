package raystark.atelier.api.alchemy.status;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import raystark.atelier.api.alchemy.effect.Effects;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.potential.Potentials;
import raystark.atelier.api.tile.AbstractTileProduct;
import raystark.atelier.api.util.NBTType;

import java.util.ArrayList;
import java.util.List;

import static raystark.atelier.api.util.NBTTagNames.*;

public class BlockProductStatus extends ProductStatus<AbstractTileProduct> {

    public BlockProductStatus(AbstractTileProduct dataSource) {
        super(dataSource);
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public List<IEffect> getEffectList() {
        return effectList;
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList() {
        return potentialAbilityList;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        System.out.println(tagCompound.hasKey(TAG_ATELIER.name()));

        NBTTagCompound tagAtelier = tagCompound.getCompoundTag(TAG_ATELIER.name());
        NBTTagList tagEffectList = tagAtelier.getTagList(TAG_EFFECT.name(), NBTType.STRING.getID());
        NBTTagList tagPotentialList = tagAtelier.getTagList(TAG_POTENTIAL.name(), NBTType.STRING.getID());

        this.quality = tagAtelier.getInteger(TAG_QUALITY.name());

        this.effectList = new ArrayList<>();
        for (int i = 0; i < tagEffectList.tagCount(); i++)
            Effects.getEffects(tagEffectList.getStringTagAt(i)).ifPresent(effectList::add); //effectList.add(Effects.getEffects(tagList.getStringTagAt(i)));

        this.potentialAbilityList = new ArrayList<>();
        for(int i=0; i<tagPotentialList.tagCount() ;i++)
            Potentials.getPotential(tagPotentialList.getStringTagAt(i)).ifPresent(potentialAbilityList::add);
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        NBTTagCompound tagAtelier = new NBTTagCompound();
        tagAtelier.setInteger(TAG_QUALITY.name(), Quality.MIN_VALUE);
        tagAtelier.setTag(TAG_EFFECT.name(), new NBTTagList());
        tagAtelier.setTag(TAG_POTENTIAL.name(), new NBTTagList());

        tagCompound.setTag(TAG_ATELIER.name(), tagAtelier);
    }
}

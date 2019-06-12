package raystark.atelier.common.alchemy.status;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.common.block.tile.AbstractTileProduct;
import raystark.atelier.common.util.NBTType;

import java.util.ArrayList;
import java.util.Collections;
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
        return Collections.unmodifiableList(effectList);
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList() {
        return Collections.unmodifiableList(potentialAbilityList);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        System.out.println(tagCompound.hasKey(TAG_ATELIER.name()));

        NBTTagCompound tagAtelier = tagCompound.getCompoundTag(TAG_ATELIER.name());
        NBTTagList tagEffectList = tagAtelier.getTagList(TAG_EFFECT.name(), NBTType.STRING.getID());
        NBTTagList tagPotentialList = tagAtelier.getTagList(TAG_POTENTIAL.name(), NBTType.STRING.getID());

        this.quality = tagAtelier.getInteger(TAG_QUALITY.name());

        this.effectList = new ArrayList<>();
        for (int i = 0; i < tagEffectList.tagCount(); i++)
            effectRegistry.getEffect(tagEffectList.getStringTagAt(i)).ifPresent(effectList::add); //effectList.add(Effects.getEffect(tagList.getStringTagAt(i)));

        this.potentialAbilityList = new ArrayList<>();
        for(int i=0; i<tagPotentialList.tagCount() ;i++)
            abilityRegistry.getPotentialAbility(tagPotentialList.getStringTagAt(i)).ifPresent(potentialAbilityList::add);
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        NBTTagCompound tagAtelier = new NBTTagCompound();

        tagAtelier.setInteger(TAG_QUALITY.name(), quality);

        NBTTagList tagEffectList = new NBTTagList();
        for(IEffect effect : effectList)
            tagEffectList.appendTag(new NBTTagString(effect.getName()));
        tagAtelier.setTag(TAG_EFFECT.name(), tagEffectList);


        NBTTagList tagPotentialList = new NBTTagList();
        for(IPotentialAbility ability : potentialAbilityList)
            tagPotentialList.appendTag(new NBTTagString(ability.getName()));
        tagAtelier.setTag(TAG_POTENTIAL.name(), tagPotentialList);

        tagCompound.setTag(TAG_ATELIER.name(), tagAtelier);
    }
}

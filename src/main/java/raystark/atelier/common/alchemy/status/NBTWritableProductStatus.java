package raystark.atelier.common.alchemy.status;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import static raystark.atelier.api.util.NBTTagNames.*;

public class NBTWritableProductStatus extends NBTReadableProductStatus {
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

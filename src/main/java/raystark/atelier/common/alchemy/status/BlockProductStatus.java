package raystark.atelier.common.alchemy.status;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.common.block.tile.AbstractTileProduct;
import raystark.atelier.common.util.NBTType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static raystark.atelier.api.util.NBTTagNames.*;

public class BlockProductStatus extends ProductStatusWithRegistry<AbstractTileProduct> {

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

    /**
     * 引数で渡されたNBTからステータスを読み取ります。
     *
     * <p>NBTのアトリエタグ(Key: TAG_ATELIER)を取り出し、品質、効果、潜在能力のそれぞれを読み取ります。
     *
     * @param tagCompound ステータスを保存したNBT
     * @throws IllegalStateException 引数のNBTがアトリエタグを持っていない場合
     */
    public void readFromNBT(NBTTagCompound tagCompound) {
        if(!tagCompound.hasKey(TAG_ATELIER.name()))
            throw new IllegalStateException("tagCompound is illegal.");

        NBTTagCompound tagAtelier = tagCompound.getCompoundTag(TAG_ATELIER.name());
        NBTTagList tagEffectList = tagAtelier.getTagList(TAG_EFFECT.name(), NBTType.STRING.getID());
        NBTTagList tagPotentialList = tagAtelier.getTagList(TAG_POTENTIAL.name(), NBTType.STRING.getID());

        this.quality = Math.max(tagAtelier.getInteger(TAG_QUALITY.name()), Quality.MIN_VALUE);

        List<IEffect> effectList;
        if(tagEffectList.tagCount() == 0)
            effectList = Collections.emptyList();
        else {
            effectList = new ArrayList<>();
            for (int i = 0; i < tagEffectList.tagCount(); i++)
                effectRegistry.getEffect(tagEffectList.getStringTagAt(i)).ifPresent(effectList::add);
        }
        this.effectList = effectList;

        List<IPotentialAbility> potentialAbilityList;
        if(tagPotentialList.tagCount() == 0)
            potentialAbilityList = Collections.emptyList();
        else {
            potentialAbilityList = new ArrayList<>();
            for(int i=0; i<tagPotentialList.tagCount() ;i++)
                abilityRegistry.getPotentialAbility(tagPotentialList.getStringTagAt(i)).ifPresent(potentialAbilityList::add);
        }
        this.potentialAbilityList = potentialAbilityList;
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

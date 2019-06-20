package raystark.atelier.common.alchemy.status;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.alchemy.status.SimpleProductStatus;
import raystark.atelier.api.registry.IAtelierRegistry;
import raystark.atelier.api.registry.IEffectRegistry;
import raystark.atelier.api.registry.IPotentialAbilityRegistry;
import raystark.atelier.common.AtelierMod;
import raystark.atelier.common.util.NBTType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static raystark.atelier.api.util.NBTTagNames.*;

/**
 * NBTからステータスを読み取る機能が追加されProductStatus
 */
public abstract class ModProductStatusBase extends SimpleProductStatus {

    /**
     * NBTからステータスを読み取ります。
     *
     * <p>NBTタグがアトリエタグを持っていない場合、IllegalArgumentExceptionをスローします。
     *
     * @throws NullPointerException 引数がnullの場合
     * @throws IllegalArgumentException 引数がアトリエタグを持っていない場合
     * * @param tagCompound NBTタグ
     */
    public void readFromNBT(NBTTagCompound tagCompound) {
        if(tagCompound == null) throw new NullPointerException("tagCompound must not be null.");
        if(!tagCompound.hasKey(TAG_ATELIER.name())) throw new IllegalArgumentException("tagCompound doesn't have TAG_ATELIER.");

        IAtelierRegistry<Item, Block, ItemStack> registry = AtelierMod.getInstance().getRegistry();
        IEffectRegistry effectRegistry = registry.getEffectRegistry();
        IPotentialAbilityRegistry abilityRegistry = registry.getPotentialAbilityRegistry();

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
}

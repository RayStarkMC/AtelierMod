package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IProductStatusの標準実装
 *
 * @author RayStark
 */
public class SimpleProductStatus implements IProductStatus {

    protected int quality;
    protected List<IEffect> effectList;
    protected List<IPotentialAbility> potentialAbilityList;

    public SimpleProductStatus() {
        quality = Quality.MIN_VALUE;
        effectList = new ArrayList<>();
        potentialAbilityList = new ArrayList<>();
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
}

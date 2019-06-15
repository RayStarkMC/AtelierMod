package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.List;
import java.util.Optional;

public class EffedtEstimated<T extends IEffect> implements IEffectEstimated<T> {
    @Override
    public Elements getElementType() {
        return null;
    }

    @Override
    public List<EffectEntry<T>> getEffectList() {
        return null;
    }

    @Override
    public Optional<T> getEffect(ElementOwner owner) {
        return Optional.empty();
    }

    @Override
    public String getEffectString() {
        return null;
    }
}

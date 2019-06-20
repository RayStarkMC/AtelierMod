package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAlchemicalRecipe<S> implements IAlchemicalRecipe<S> {
    private ItemAlchemicalProduct<?> product;

    private List<IRequirement<S>> requirements;
    private List<IEffectEstimated<? extends IEffect>> effectsEstimated;

    protected AbstractAlchemicalRecipe(ItemAlchemicalProduct<?> product) {
        if (product == null) throw new NullPointerException("product must not be null.");
        this.product = product;
        this.requirements = new ArrayList<>();
        this.effectsEstimated = new ArrayList<>();
    }

    protected void addRequirement(IRequirement<S> requirement) {
        if (requirement == null) throw new NullPointerException("requirement must not be null.");
        requirements.add(requirement);
    }

    protected void addeffectEstimated(IEffectEstimated<? extends IEffect> effectEstimated) {
        if (effectEstimated == null) throw new NullPointerException("effectEstimated must not be null");
        effectsEstimated.add(effectEstimated);
    }

    @Override
    public ItemAlchemicalProduct<?> getProduct() {
        return product;
    }

    @Override
    public List<IRequirement<S>> getRequirements() {
        return Collections.unmodifiableList(requirements);
    }

    @Override
    public List<IEffectEstimated<? extends IEffect>> getEffectsEstimated() {
        return Collections.unmodifiableList(effectsEstimated);
    }

    @Override
    public boolean isReady(List<S> inputs) {
        return false;
    }
}

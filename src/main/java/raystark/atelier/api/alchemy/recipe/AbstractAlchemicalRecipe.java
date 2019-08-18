package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * IAlchemicalRecipeの骨格実装。
 * @param <S>
 */
public abstract class AbstractAlchemicalRecipe<S> implements IAlchemicalRecipe<S> {
    private IAlchemicalProduct product;

    private List<IRequirement<S>> requirements;
    private List<IEffectEstimated<? extends IEffect>> effectsEstimated;

    protected AbstractAlchemicalRecipe(ItemAlchemicalProduct<?> product) {
        this.product = Objects.requireNonNull(product, "product must not be null.");
        this.requirements = new ArrayList<>();
        this.effectsEstimated = new ArrayList<>();
    }

    protected void addRequirement(IRequirement<S> requirement) {
        requirements.add(Objects.requireNonNull(requirement, "requirement must not be null."));
    }

    protected void addEffectEstimated(IEffectEstimated<? extends IEffect> effectEstimated) {
        effectsEstimated.add(Objects.requireNonNull(effectEstimated, "effectEstimated must not be null"));
    }

    @Override
    public IAlchemicalProduct getProduct() {
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

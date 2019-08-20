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
    private List<IEffectEstimated> effectsEstimated;

    protected AbstractAlchemicalRecipe(IAlchemicalProduct product) {
        this.product = Objects.requireNonNull(product, "product must not be null.");
        this.requirements = new ArrayList<>();
        this.effectsEstimated = new ArrayList<>();

        prepareRequirements(requirements);
        prepareEffectsEstimated(effectsEstimated);
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
    public List<IEffectEstimated> getEffectsEstimated() {
        return Collections.unmodifiableList(effectsEstimated);
    }

    /**
     * レシピの要求物を準備します。
     *
     * <p>このメソッドはコンストラクタで1度だけ呼び出されます。引数のリストに要求物を追加してください。
     */
    protected abstract void prepareRequirements(List<IRequirement<S>> requirements);

    /**
     * レシピの効果予測を準備します。
     *
     * <p>このメソッドはコンストラクタで1度だけ呼び出されます。引数のリストに効果予測を追加してください。
     */
    protected abstract void prepareEffectsEstimated(List<IEffectEstimated> effectsEstimated);

    // future
    // protected abstract void preparePotentials();
}

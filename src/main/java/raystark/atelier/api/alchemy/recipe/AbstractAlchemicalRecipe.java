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
 *
 * <p>このクラスはMinecraft本体のバージョンに依存しない実装を提供します。
 * @param <S> アイテムスタックの型
 *
 * @author RayStark
 */
public abstract class AbstractAlchemicalRecipe<S> implements IAlchemicalRecipe<S> {
    private IAlchemicalProduct product;

    private List<IRequirement<S>> requirements;
    private List<IEffectEstimated> effectsEstimated;

    /**
     * 錬金術レシピを生成する直前に呼ばれます。
     *
     * <p>このコンストラクタではレシピの完成品、材料、効果予測の登録を行います。
     *
     * @param product このレシピの完成品
     *
     * @throws NullPointerException productがnullの場合
     */
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
     * レシピの要求物を登録します。
     *
     * <p>このメソッドはコンストラクタで1度だけ呼び出されます。引数のリストに要求物を追加してください。
     */
    protected abstract void prepareRequirements(List<IRequirement<S>> requirements);

    /**
     * レシピの効果予測を登録します。
     *
     * <p>このメソッドはコンストラクタで1度だけ呼び出されます。引数のリストに効果予測を追加してください。
     */
    protected abstract void prepareEffectsEstimated(List<IEffectEstimated> effectsEstimated);

    // future
    // protected abstract void preparePotentials();
}

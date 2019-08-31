package raystark.atelier.api.alchemy.recipe;

import java.util.Objects;

/**
 * ISpecifiedRequirementの骨格実装。
 *
 * <p>このクラスではMinecraftのバージョンに依存しない実装を提供します。
 *
 * @param <S> アイテムスタックの型
 */
public abstract class AbstractSpecifiedRequirement<S> implements ISpecifiedRequirement<S> {

    /**
     * 要求物
     */
    protected final S specified;

    /**
     * アイテムスタックが指定された要求物を生成します。
     *
     * @param specified 要求物
     * @throws NullPointerException specifiedがnullの場合
     */
    protected AbstractSpecifiedRequirement(S specified) {
        this.specified = Objects.requireNonNull(specified, "specified must not be null.");
    }

    @Override
    public S getSpecified() {
        return specified;
    }
}

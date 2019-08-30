package raystark.atelier.api.alchemy.recipe;

import java.util.Objects;

/**
 * ISpecifiedRequirementの骨格実装。
 *
 * <p>このクラスではMinecraftのバージョンに依存しない実装を提供します。
 *
 * @param <T> 要求物の型
 * @param <S> アイテムスタックの型
 */
public abstract class AbstractSpecifiedRequirement<T, S> implements ISpecifiedRequirement<T, S> {

    private final T specified;
    private final int metadata;

    /**
     * アイテム/ブロックが指定された要求物を生成します。
     *
     * <p>このコンストラクタでは要求物のnull検査とメタデータの正当性検査が行われます。
     * メタデータの正当性検査のためにisIllegalが呼ばれます。実装クラスではisIllegalを正しく実装する必要があります。
     *
     * @param specified 要求物
     *
     * @throws NullPointerException specifiedがnullの場合
     * @throws IllegalArgumentException メタデータが不正の場合
     */
    protected AbstractSpecifiedRequirement(T specified, int metadata) {
        if(isIllegal(metadata)) throw new IllegalArgumentException("metadata: " + metadata + " is illegal.");
        this.specified = Objects.requireNonNull(specified, "specified must not be null.");
        this.metadata = metadata;
    }

    /**
     * メタデータが不正であるか検証します。
     *
     * <p>このメソッドはコンストラクタよるメタデータの正当性検査のために呼ばれます。
     *
     * @param metadata 正当性検査するメタデータ
     * @return メタデータが不正であればtrue
     */
    protected abstract boolean isIllegal(int metadata);

    @Override
    public T getSpecified() {
        return specified;
    }

    @Override
    public int getMeta() {
        return metadata;
    }
}

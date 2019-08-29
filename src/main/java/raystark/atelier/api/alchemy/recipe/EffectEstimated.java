package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.*;

/**
 * IEffectEstimatedの実装。
 *
 * <p>ビルダーを利用してこのクラスのインスタンスを生成できます。一度生成されたインスタンスは以後不変です。
 *
 * <p>この実装では、属性値の最小要求値が重複するエントリを追加することが出来ません。そのようなエントリを追加しようとした場合、
 * IllegalArgumentExceptionがスローされます。
 *
 * @author RayStark
 */
public final class EffectEstimated implements IEffectEstimated {

    /**
     * EffectEntryの実装
     *
     * EffectEstimatedが属性値の最小要求値の重複を認めないため、compareToでは属性値のみを比較します。
     *
     * @author RayStark
     */
    private static final class EffectEntry implements IEffectEntry {
        private final int minimumRequired;
        private final IEffect effect;

        private EffectEntry(int minimumRequired, IEffect effect) {
            if(minimumRequired < ElementOwner.MIN_VALUE || ElementOwner.MAX_VALUE < minimumRequired)
                throw new IllegalArgumentException("minimumRequired must be during 0-100.");

            this.minimumRequired = minimumRequired;
            this.effect = effect;
        }

        @Override
        public Optional<IEffect> getEffect() {
            return Optional.ofNullable(effect);
        }

        @Override
        public int getMinimumRequired() {
            return minimumRequired;
        }

        /**
         * この実装では属性値が等しい場合、0を返します。
         *
         * @param other 他方のエントリ
         * @return このエントリと他方のエントリを比較し、高いなら正、等しいならゼロ、低いなら負の整数
         */
        @Override
        public int compareTo(IEffectEntry other) {
            return Integer.compare(minimumRequired, Objects.requireNonNull(other, "other must not be null").getMinimumRequired());
        }
    }

    /**
     * EffectEstimatedのビルダークラス。
     *
     * <p>ビルダーを通じて効果予測を設定することが出来ます。
     *
     * @author RayStark
     */
    public static class EffectEstimatedBuilder {
        private final Elements elementRequired;
        private final String effectString;
        private final List<IEffectEntry> entryList;

        private EffectEstimatedBuilder(Elements elementRequired, String effectString) {
            this.elementRequired = Objects.requireNonNull(elementRequired);
            this.effectString = Objects.requireNonNull(effectString);
            this.entryList = new ArrayList<>();
        }

        /**
         * 効果予測に効果を追加します。
         *
         * @param minimumRequired 最低要求属性値
         * @param effect 効果
         * @return このビルダー
         *
         * @throws IllegalArgumentException 要求値が不正、または既に利用されている場合
         */
        public final EffectEstimatedBuilder addEffect(int minimumRequired, IEffect effect) {
            if (entryList.stream()
                    .mapToInt(IEffectEntry::getMinimumRequired)
                    .anyMatch(e -> e == minimumRequired)
                    //.anyMatch(FunctionUtils.BiIntPredicate.equal(minimumRequired))
            ) throw new IllegalArgumentException("minimumRequired: " + minimumRequired + "is already used.");

            entryList.add(new EffectEntry(minimumRequired, effect));
            return this;
        }

        /**
         * 効果予測に空の効果を追加します。
         *
         * @param minimumRequired 最低要求属性値
         * @return このビルダー
         *
         * @throws IllegalArgumentException 要求値が不正、または既に利用されている場合
         */
        public final EffectEstimatedBuilder addEmptyEffect(int minimumRequired) {
            return addEffect(minimumRequired, null);
        }

        /**
         * 効果予測を生成します。
         *
         * 効果予測は少なくとも1つの効果を含んでいる必要があります。もし効果が存在しない、
         * または空のエントリのみが登録されていた場合にはビルドは失敗し、IllegalStateExceptionがスローされます。
         * 効果予測はビルド後に不変になります。
         *
         * @return 生成された効果予測
         *
         * @throws IllegalStateException 効果が無い場合。
         */
        public IEffectEstimated build() {
            if(entryList.stream()
                    .map(IEffectEntry::getEffect)
                    .noneMatch(Optional::isPresent)
            ) throw new IllegalStateException("There is no effect in this EffectEstimated.");
            return new EffectEstimated(this);
        }
    }

    /**
     * この効果予測のビルダーを返すファクトリメソッド。
     *
     * 要求属性と効果の文字列表現を指定してビルダーを生成します。
     *
     * @param elementRequired この効果予測の要求属性
     * @param effectString この効果の文字列表現
     * @return ビルダー
     *
     * @throws NullPointerException elementRequired, またはeffectStringがnullの場合
     */
    public static EffectEstimatedBuilder newBuilder(Elements elementRequired, String effectString) { return new EffectEstimatedBuilder(elementRequired, effectString); }

    private final Elements elementRequired;

    private final String effectString;

    private final List<IEffectEntry> entryList;

    private EffectEstimated(EffectEstimatedBuilder builder) {
        this.elementRequired = builder.elementRequired;
        this.effectString = builder.effectString;
        this.entryList = builder.entryList;
    }

    @Override
    public final Elements getElementRequired() {
        return elementRequired;
    }

    @Override
    public final List<IEffectEntry> getEntryList() {
        return Collections.unmodifiableList(entryList);
    }

    @Override
    public final Optional<IEffect> getEffectFromElement(ElementOwner owner) {
        return entryList.stream()
                .filter(e -> e.getMinimumRequired() <= owner.getElementValue(elementRequired))
                .max(Comparator.naturalOrder())
                .flatMap(IEffectEntry::getEffect);
    }

    @Override
    public final String getEffectString() {
        return effectString;
    }
}

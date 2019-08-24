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
 * @author RayStark
 */
public final class EffectEstimated implements IEffectEstimated {

    /**
     * EffectEntryの実装
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

        @Override
        public int compareTo(IEffectEntry other) {
            return Integer.compare(this.getMinimumRequired(), Objects.requireNonNull(other, "other must not be null").getMinimumRequired());
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
            this.entryList.stream()
                    .filter(e -> e.getMinimumRequired() == minimumRequired)
                    .findAny()
                    .ifPresent(e -> {throw new IllegalArgumentException("要求値: " + minimumRequired + "は既に使用されています。");});

            this.entryList.add(new EffectEntry(minimumRequired, effect));
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
            if(isIllegalState())
                throw new IllegalStateException("There is no effect in this EffectEstimated.");
            return new EffectEstimated(this);
        }

        private boolean isIllegalState() {
            if(entryList.isEmpty()) return true;

            for(IEffectEntry entry : entryList) {
                if(!entry.getEffect().isPresent())
                    return true;
            }

            return false;
        }
    }

    private static final IEffectEntry EMPTY_ENTRY = new EffectEntry(0, null);

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
                .filter(e -> e.getMinimumRequired() <= owner.getElementValue(getElementRequired()))
                .max(Comparator.naturalOrder())
                .orElse(EMPTY_ENTRY)
                .getEffect();
    }

    @Override
    public final String getEffectString() {
        return effectString;
    }
}

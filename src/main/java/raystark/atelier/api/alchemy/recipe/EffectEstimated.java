package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.*;

/**
 * IEffectEstimatedの実装。
 */
public final class EffectEstimated implements IEffectEstimated {
    private static final class EffectEntry implements IEffectEntry {
        private int minimumRequired;
        private final IEffect effect;

        private EffectEntry(int minimumRequired, IEffect effect) {
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

    public static class EffectEstimatedBuilder {
        private final Elements elementRequired;
        private final String effectString;
        private final List<IEffectEntry> entryList;

        private EffectEstimatedBuilder(Elements elementRequired, String effectString) {
            this.elementRequired = Objects.requireNonNull(elementRequired);
            this.effectString = Objects.requireNonNull(effectString);
            this.entryList = new ArrayList<>();
        }

        public final EffectEstimatedBuilder addEffect(int minimumRequired, IEffect effect) {
            this.entryList.stream()
                    .filter(e -> e.getMinimumRequired() == minimumRequired)
                    .findAny()
                    .ifPresent(e -> {throw new IllegalArgumentException();});

            this.entryList.add(new EffectEntry(minimumRequired, effect));
            return this;
        }

        public final EffectEstimatedBuilder addEmptyEffect(int minimumRequired) {
            return addEffect(minimumRequired, null);
        }

        public IEffectEstimated build() {
            return new EffectEstimated(this);
        }
    }

    private static final IEffectEntry EMPTY_ENTRY = new EffectEntry(0, null);

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
        IEffectEntry entry = entryList.stream()
                .filter(e -> e.getMinimumRequired() <= owner.getElementValue(getElementRequired()))
                .max(Comparator.naturalOrder())
                .orElse(EMPTY_ENTRY);
        return entry.getEffect();
    }

    @Override
    public final String getEffectString() {
        return effectString;
    }
}

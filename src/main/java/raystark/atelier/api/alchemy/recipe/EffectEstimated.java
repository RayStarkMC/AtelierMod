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
        private IEffect effect;

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
            if(other == null) throw new NullPointerException("other must not be null");

            return Integer.compare(this.getMinimumRequired(), other.getMinimumRequired());
        }
    }

    private static final IEffectEntry EMPTY_ENTRY = new EffectEntry(0, null);

    private final Elements elementRequired;

    private final String effectString;

    private final List<IEffectEntry> entryList;

    public EffectEstimated(Elements elementRequired, String effectString) {
        this.elementRequired = Objects.requireNonNull(elementRequired);
        this.effectString = Objects.requireNonNull(effectString);
        entryList = new ArrayList<>();
    }

    public final EffectEstimated addEffect(int minimumRequired, IEffect effect) {
        entryList.stream()
                .filter(e -> e.getMinimumRequired() == minimumRequired)
                .findAny()
                .ifPresent(e -> {throw new IllegalArgumentException();});

        entryList.add(new EffectEntry(minimumRequired, effect));
        return this;
    }

    public final EffectEstimated addEmptyEffect(int minimumRequired) {
        return addEffect(minimumRequired, null);
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

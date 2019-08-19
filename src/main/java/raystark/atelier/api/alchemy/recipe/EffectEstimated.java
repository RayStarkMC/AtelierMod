package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.*;

public final class EffectEstimated<T extends IEffect> implements IEffectEstimated<T> {

    private final Elements elementRequired;
    private final String effectString;

    private final List<IEffectEntry<T>> entryList;

    public EffectEstimated(Elements elementRequired, String effectString) {
        this.elementRequired = Objects.requireNonNull(elementRequired);
        this.effectString = Objects.requireNonNull(effectString);
        entryList = new ArrayList<>();
    }

    public final EffectEstimated<T> addEffect(int minimumRequired, T effect) {
        entryList.stream()
                .filter(e -> e.getMinimumRequired() == minimumRequired)
                .findAny()
                .ifPresent(e -> {throw new IllegalArgumentException();});

        entryList.add(new EffectEntry<>(minimumRequired, effect));
        return this;
    }

    public final EffectEstimated<T> addEmptyEffect(int minimumRequired) {
        addEffect(minimumRequired, null);
        return this;
    }

    @Override
    public final Elements getElementRequired() {
        return elementRequired;
    }

    @Override
    public final List<IEffectEntry<T>> getEntryList() {
        return Collections.unmodifiableList(entryList);
    }

    @Override
    public final Optional<T> getEffectFromElement(ElementOwner owner) {
        Optional<IEffectEntry<T>> entryOptional = entryList.stream()
                .filter(e -> e.getMinimumRequired() <= owner.getElementValue(getElementRequired()))
                .max(Comparator.naturalOrder());

        //TODO このへんのゴチャっとした感じを直す。

        return entryOptional.isPresent() ? entryOptional.get().getEffect() : Optional.empty();
    }

    @Override
    public final String getEffectString() {
        return effectString;
    }

    private static final class EffectEntry<T extends IEffect> implements IEffectEntry<T> {
        private int minimumRequired;
        private T effect;

        private EffectEntry(int minimumRequired, T effect) {
            this.minimumRequired = minimumRequired;
            this.effect = effect;
        }

        @Override
        public Optional<T> getEffect() {
            return Optional.ofNullable(effect);
        }

        @Override
        public int getMinimumRequired() {
            return minimumRequired;
        }

        @Override
        public int compareTo(IEffectEntry<T> other) {
            if(other == null) throw new NullPointerException("other must not be null");

            return Integer.compare(this.getMinimumRequired(), other.getMinimumRequired());
        }
    }
}

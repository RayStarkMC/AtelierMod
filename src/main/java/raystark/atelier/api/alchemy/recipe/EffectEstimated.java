package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.*;

public class EffectEstimated<T extends IEffect> implements IEffectEstimated<T> {

    private Elements elementRequired;
    private String effectString;

    private List<IEffectEntry<T>> entryList;

    public EffectEstimated(Elements elementRequired, String effectString) {
        if(elementRequired == null) throw new NullPointerException();
        if(effectString == null) throw new NullPointerException();

        this.elementRequired = elementRequired;
        this.effectString = effectString;
        entryList = new ArrayList<>();
    }

    public EffectEstimated<T> addEffect(int minimumRequired, T effect) {
        Optional<IEffectEntry<T>> optional = entryList.stream()
                .filter(e -> e.getMinimumRequired() == minimumRequired)
                .findAny();
        if(optional.isPresent()) throw new IllegalArgumentException();

        entryList.add(new EffectEntry<>(minimumRequired, effect));
        return this;
    }

    public EffectEstimated<T> addEmptyEffect(int minimumRequired) {
        addEffect(minimumRequired, null);
        return this;
    }

    @Override
    public Elements getElementRequired() {
        return elementRequired;
    }

    @Override
    public List<IEffectEntry<T>> getEntryList() {
        return Collections.unmodifiableList(entryList);
    }

    @Override
    public Optional<T> getEffect(ElementOwner owner) {
        int elementValue = owner.getElementValue(getElementRequired());

        Optional<IEffectEntry<T>> entryOptional = getEntryList().stream()
                .filter(e -> e.getMinimumRequired() <= elementValue)
                .max(Comparator.naturalOrder());

        return entryOptional.isPresent() ? entryOptional.get().getEffect() : Optional.empty();
    }

    @Override
    public String getEffectString() {
        return effectString;
    }

    private static class EffectEntry<T extends IEffect> implements IEffectEntry<T> {
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

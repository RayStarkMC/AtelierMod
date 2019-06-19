package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.Elements;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.api.alchemy.status.Quality;

import java.util.Collections;
import java.util.List;

public class SimpleMaterialStatus implements IMaterialStatus {
    private List<IPotentialAbility> list;
    private int quality;
    private ElementContainer elements;

    public SimpleMaterialStatus(int quality, List<IPotentialAbility> list, ElementContainer elements) {
        if(quality < Quality.MIN_VALUE) throw new IllegalArgumentException("quality must be positive.");
        if(list == null) throw new NullPointerException("list must not be null.");
        if(elements == null) throw new NullPointerException("elements must not be null");
        this.quality = quality;
        this.list = list;
        this.elements = elements;
    }

    public SimpleMaterialStatus(int quality, List<IPotentialAbility> list, int fire, int water, int air, int earth) {
        this(quality, list, new ElementContainer(fire, water, air, earth));
    }

    @Override
    public int getElementValue(Elements elementType) {
        return elements.getElementValue(elementType);
    }

    @Override
    public List<IPotentialAbility> getPotentialAbilityList() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public int getQuality() {
        return quality;
    }

    public static class ElementContainer implements ElementOwner {
        private int fire, water, air, earth;


        public ElementContainer(int fire, int water, int air, int earth) {
            if(fire < MIN_VALUE || MAX_VALUE < fire
                    || water < MIN_VALUE || MAX_VALUE < water
                    || air < MIN_VALUE || MAX_VALUE < air
                    || earth < MIN_VALUE || MAX_VALUE < earth)
                throw new IllegalArgumentException("Element must be during 0-100.");

            this.fire = fire;
            this.water = water;
            this.air = air;
            this.earth = earth;
        }

        @Override
        public int getElementValue(Elements elementType) {
            switch (elementType) {
                case FIRE:
                    return fire;
                case WATER:
                    return water;
                case AIR:
                    return air;
                case EARTH:
                    return earth;
            }
            return MIN_VALUE;
        }
    }
}

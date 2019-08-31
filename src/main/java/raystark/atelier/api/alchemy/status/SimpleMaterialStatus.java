package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * IMaterialStatusの標準実装
 *
 * @author RayStark
 */
public class SimpleMaterialStatus implements IMaterialStatus {

    /**
     * 錬金素材の属性値を保持するクラス
     *
     * @author RayStark
     */
    private static class ElementOwner implements IElementOwner {
        private int fire, water, air, earth;

        public ElementOwner(int fire, int water, int air, int earth) {
            if(isIllegalElement(fire) || isIllegalElement(water) || isIllegalElement(air) || isIllegalElement(earth))
                throw new IllegalArgumentException("Element must be during 0-100.");

            this.fire = fire;
            this.water = water;
            this.air = air;
            this.earth = earth;
        }

        private boolean isIllegalElement(int element) {
            return element < MIN_VALUE || MAX_VALUE < element;
        }

        @Override
        public int getElementValue(Elements elementType) {
            switch (Objects.requireNonNull(elementType)) {
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

    private final List<IPotentialAbility> list;
    private final int quality;

    private final ElementOwner elements;

    public SimpleMaterialStatus(int quality, List<IPotentialAbility> list, int fire, int water, int air, int earth) {
        if(quality < Quality.MIN_VALUE) throw new IllegalArgumentException("quality must be positive.");

        this.quality = quality;
        this.list = Objects.requireNonNull(list, "list must not be null.");
        this.elements = new ElementOwner(fire, water, air, earth);
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
}

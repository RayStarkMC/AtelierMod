package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;

/**
 *
 * @param <T>
 */
public interface IProductStatusWithDataSource<T> extends IProductStatus {
    /**
     *
     *
     * @return
     */
    @Override
    int getQuality();

    /**
     *
     * @return
     */
    @Override
    List<IEffect> getEffectList();

    /**
     *
     * @return
     */
    @Override
    List<IPotentialAbility> getPotentialAbilityList();

    /**
     *
     * @return
     */
    T getDataSource();
}

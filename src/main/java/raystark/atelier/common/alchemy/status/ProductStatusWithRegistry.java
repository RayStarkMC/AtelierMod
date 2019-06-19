package raystark.atelier.common.alchemy.status;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.IProductStatus;
import raystark.atelier.api.alchemy.status.Quality;
import raystark.atelier.api.registry.IEffectRegistry;
import raystark.atelier.api.registry.IPotentialAbilityRegistry;
import raystark.atelier.common.AtelierMod;

import java.util.List;

/**
 * アトリエModのレジストリに対して参照を持つProductStatusです。
 *
 * <p>このクラスはブロックとアイテム用のProductStatusで継承されることを前提に設計されています。
 *
 *
 * @param <T>
 */
public abstract class ProductStatusWithRegistry<T> implements IProductStatus {
    protected IEffectRegistry effectRegistry;
    protected IPotentialAbilityRegistry abilityRegistry;

    protected int quality;
    protected List<IEffect> effectList;
    protected List<IPotentialAbility> potentialAbilityList;

    protected T dataSource;

    protected ProductStatusWithRegistry(T dataSource) {
        if(dataSource == null) throw new NullPointerException("dataSource must not be null.");
        this.dataSource = dataSource;
        quality = Quality.MIN_VALUE;

        AtelierMod mod = AtelierMod.getInstance();

        effectRegistry = mod.getRegistry().getEffectRegistry();
        abilityRegistry = mod.getRegistry().getPotentialAbilityRegistry();
    }
}

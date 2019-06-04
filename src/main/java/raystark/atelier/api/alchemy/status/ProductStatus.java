package raystark.atelier.api.alchemy.status;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;

import java.util.List;

public abstract class ProductStatus<T> implements IProductStatus {
    protected int quality;
    protected List<IEffect> effectList;
    protected List<IPotentialAbility> potentialAbilityList;

    protected T dataSource;

    protected ProductStatus(T dataSource) {
        if(dataSource == null) throw new NullPointerException("dataSource must not be null.");
        this.dataSource = dataSource;
        quality = Quality.MIN_VALUE;
    }
}

package raystark.atelier.common.alchemy.recipe;

import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffectInstantHeal;
import raystark.atelier.api.alchemy.recipe.EffectEstimated;
import raystark.atelier.api.alchemy.recipe.IEffectEstimated;
import raystark.atelier.api.alchemy.status.Elements;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.registry.EffectRegistry;

public class RecipeBandAid extends AlchemicalRecipe{
    public RecipeBandAid() {
        super((ItemAlchemicalProduct<?>) AtelierItems.bandAid);
        initRecipe();
    }

    private void initRecipe() {
        IEffectEstimated<IEffectInstantHeal> instantHeal = new EffectEstimated<IEffectInstantHeal>(Elements.WATER, "Efficacy")
                .addEffect(0, EffectRegistry.EFFECT_WEAK_HEAL)
                .addEffect(70, EffectRegistry.EFFECT_MEDIUM_WEAK_HEAL);

        addEffectEstimated(instantHeal);
    }
}

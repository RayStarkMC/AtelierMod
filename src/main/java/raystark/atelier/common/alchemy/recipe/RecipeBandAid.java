package raystark.atelier.common.alchemy.recipe;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.effect.IEffectInstantHeal;
import raystark.atelier.api.alchemy.recipe.EffectEstimated;
import raystark.atelier.api.alchemy.recipe.IEffectEstimated;
import raystark.atelier.api.alchemy.recipe.IRequirement;
import raystark.atelier.api.alchemy.status.Elements;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.registry.EffectRegistry;

import java.util.List;

public class RecipeBandAid extends AlchemicalRecipeBase {
    public RecipeBandAid() {
        super((IAlchemicalProduct) AtelierItems.bandAid);
    }

    @Override
    protected void prepareRequirements(List<IRequirement<ItemStack>> requirements) {

    }

    @Override
    protected void prepareEffectsEstimated(List<IEffectEstimated> effectsEstimated) {
        effectsEstimated.add(new EffectEstimated(Elements.WATER, "Efficacy")
                .addEffect(0, EffectRegistry.EFFECT_WEAK_HEAL)
                .addEffect(70, EffectRegistry.EFFECT_MEDIUM_WEAK_HEAL));
    }
}

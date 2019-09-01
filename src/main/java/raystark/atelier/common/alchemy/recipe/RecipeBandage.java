package raystark.atelier.common.alchemy.recipe;

import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.recipe.EffectEstimated;
import raystark.atelier.api.alchemy.recipe.IEffectEstimated;
import raystark.atelier.api.alchemy.recipe.IRequirement;
import raystark.atelier.api.alchemy.status.Elements;
import raystark.atelier.common.item.AtelierItems;
import raystark.atelier.common.registry.EffectRegistry;

import java.util.List;

public final class RecipeBandage extends AlchemicalRecipeBase {
    public RecipeBandage() {
        super((IAlchemicalProduct) AtelierItems.bandage);
    }

    @Override
    protected void prepareRequirements(List<IRequirement<ItemStack>> requirements) {

    }

    @Override
    protected void prepareEffectsEstimated(List<IEffectEstimated> effectsEstimated) {
        effectsEstimated.add(
                EffectEstimated.newBuilder(Elements.WATER, "Efficacy")
                .addEffect(0, EffectRegistry.EFFECT_WEAK_HEAL)
                .addEffect(70, EffectRegistry.EFFECT_MEDIUM_WEAK_HEAL)
                .build()
        );
    }
}

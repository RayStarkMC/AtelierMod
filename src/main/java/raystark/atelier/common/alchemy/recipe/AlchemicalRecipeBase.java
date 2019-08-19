package raystark.atelier.common.alchemy.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.recipe.AbstractAlchemicalRecipe;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AbstractAlchemicalRecipeの1.7.10実装
 */
public abstract class AlchemicalRecipeBase extends AbstractAlchemicalRecipe<ItemStack> {

    protected AlchemicalRecipeBase(IAlchemicalProduct product) {
        super(product);
    }

    @Override
    public ItemStack createProduct(int quality, ElementOwner elements, List<IPotentialAbility> abilities) {
        List<IEffect> effects = getEffectsEstimated().stream()
                .map(e -> e.getEffectFromElement(elements))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        ItemStack product = getProduct() instanceof Item ? new ItemStack((Item) getProduct(), 1, 0) : new ItemStack((Block) getProduct(), 1, 0);
        product.setTagCompound(AtelierModUtil.newAtelierTagBuilder().setQuality(quality).addAllEffects(effects).addAllPotentials(abilities).build());
        product.stackSize = product.getMaxStackSize();

        return product;
    }

    @Override
    public boolean isReady(List<ItemStack> inputs) {
        return false;
    }
}

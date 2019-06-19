package raystark.atelier.common.alchemy.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.ItemAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.recipe.AbstractAlchemicalRecipe;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlchemicalRecipe extends AbstractAlchemicalRecipe<ItemStack> {

    protected AlchemicalRecipe(ItemAlchemicalProduct<?> product) {
        super(product);
    }

    @Override
    public ItemStack createProduct(int quality, ElementOwner elements, List<IPotentialAbility> abilities) {
        ItemStack product = getProduct() instanceof Item ? new ItemStack((Item) getProduct(), 1, 0) : new ItemStack((Block) getProduct(), 1, 0);
        List<Optional<? extends IEffect>> effectList = getEffectsEstimated().stream()
                .map(e -> e.getEffect(elements))
                .collect(Collectors.toList());

        AtelierModUtil.applyDefaultTag(product);
        for (Optional<? extends IEffect> optional : effectList) {
            optional.ifPresent(e -> AtelierModUtil.addEffect(product, optional.get()));
        }

        //TODO qualityとPotentialAbilityをアイテムスタックに記録する。

        product.stackSize = product.getMaxStackSize();
        return product;
    }
}

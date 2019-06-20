package raystark.atelier.common.alchemy.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        List<? extends IEffect> effects = getEffectsEstimated().stream()
                .map(e -> e.getOptionalEffect(elements))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        ItemStack product = getProduct() instanceof Item ? new ItemStack((Item) getProduct(), 1, 0) : new ItemStack((Block) getProduct(), 1, 0);
        product.setTagCompound(AtelierModUtil.newTagBuilder().setQuality(quality).addAllEffects(effects).addAllPotentials(abilities).build());
        product.stackSize = product.getMaxStackSize();

        return product;
    }
}

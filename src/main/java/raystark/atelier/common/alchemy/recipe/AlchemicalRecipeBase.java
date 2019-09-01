package raystark.atelier.common.alchemy.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.IAlchemicalProduct;
import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.recipe.AbstractAlchemicalRecipe;
import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.common.util.AtelierModUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * AbstractAlchemicalRecipeの1.7.10実装
 */
public abstract class AlchemicalRecipeBase extends AbstractAlchemicalRecipe<ItemStack> {

    protected AlchemicalRecipeBase(IAlchemicalProduct product) {
        super(product);
    }


    /**
     * ステータスからこのレシピの完成品のアイテムスタックを生成します。
     *
     * <p>このメソッドでは決定した品質値、効果、潜在能力を完成品のNBTタグに書き込みます。
     *
     * @param status このレシピに適応する材料のステータス。
     * @return 完成品のアイテムスタック
     */
    @Override
    public ItemStack createProduct(IMaterialStatus status) {
        Objects.requireNonNull(status, "status must not be null.");

        List<IEffect> effects = getEffectsEstimated().stream()
                .map(e -> e.getEffectFromElement(status))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        ItemStack product = getProduct() instanceof Item ? new ItemStack((Item) getProduct(), 1, 0) : new ItemStack((Block) getProduct(), 1, 0);
        product.setTagCompound(AtelierModUtil.newAtelierTagBuilder().setQuality(status.getQuality()).addAllEffects(effects).addAllPotentials(status.getPotentialAbilityList()).build());
        product.stackSize = product.getMaxStackSize();

        return product;
    }
}

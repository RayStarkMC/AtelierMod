package raystark.atelier.common.alchemy.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.alchemy.recipe.AbstractCategoryRequirement;
import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.registry.ICategoryRegistry;
import raystark.atelier.common.AtelierMod;

/**
 * AbstractCategoryRequirementの1.7.10実装
 */
public class CategoryRequirement extends AbstractCategoryRequirement<ItemStack> {

    private final ICategoryRegistry<Item, Block, ItemStack> categoryRegistry;

    public CategoryRequirement(IMaterialCategory category) {
        super(category);
        categoryRegistry = AtelierMod.getInstance().getRegistry().getCategoryRegistry();
    }

    @Override
    public boolean isApplicableAsMaterial(ItemStack input) {
        return categoryRegistry.getCategories(input).contains(getCategory());
    }
}

package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import raystark.atelier.api.category.Category;
import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.registry.ICategoryRegistry;
import raystark.atelier.common.block.AtelierBlocks;

public class CategoryRegistry implements ICategoryRegistry<Item, Block, ItemStack> {
    private boolean hasInit;

    public CategoryRegistry() {
        hasInit = false;
    }

    @Override
    public void registerItem(Item item, IMaterialCategory category) {
        OreDictionary.registerOre(category.getOreDictName(), item);
    }

    @Override
    public void registerBlock(Block block, IMaterialCategory category) {
        OreDictionary.registerOre(category.getOreDictName(), block);
    }

    @Override
    public void registerItemStack(ItemStack itemStack, IMaterialCategory category) {
        OreDictionary.registerOre(category.getOreDictName(), itemStack);
    }

    @Override
    public boolean isItemStackBelongToCategory(ItemStack itemStack, IMaterialCategory category) {
        for(ItemStack target : OreDictionary.getOres(category.getOreDictName()))
            if(OreDictionary.itemMatches(target, itemStack, false))
                return true;

        return false;
    }

    @Override
    public void init() {
        if(hasInit()) return;

        registerBlock(AtelierBlocks.sampleBlock, Category.METAL);

        hasInit = true;
    }

    @Override
    public boolean hasInit() {
        return hasInit;
    }
}

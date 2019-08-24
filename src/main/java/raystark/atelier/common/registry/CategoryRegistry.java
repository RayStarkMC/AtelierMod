package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import raystark.atelier.api.category.Category;
import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.registry.ICategoryRegistry;
import raystark.atelier.common.block.AtelierBlocks;

import java.util.List;
import java.util.Map;

public final class CategoryRegistry implements ICategoryRegistry<Item, Block, ItemStack> {
    //TODO カテゴリregistry実装

    private static class RegistryElement<E> {
        private E element;

        private RegistryElement(E element) {

        }
    }

    private boolean hasInit;

    public CategoryRegistry() {
        hasInit = false;
    }

    @Override
    public void registerItem(Item item, int meta, IMaterialCategory category) {
    }

    @Override
    public void registerItemIgnoringMetadata(Item item, IMaterialCategory category) {

    }

    @Override
    public void registerBlock(Block block, int meta, IMaterialCategory category) {
    }

    @Override
    public void registerBlockIgnoringMetadata(Block block, IMaterialCategory category) {

    }

    @Override
    public void registerItemStack(ItemStack itemStack, boolean ignoreMeta, IMaterialCategory category) {
    }

    @Override
    public List<IMaterialCategory> getCategories(ItemStack itemStack) {
        return null;
    }

    @Override
    public List<ItemStack> getItems(IMaterialCategory category) {
        return null;
    }

    @Override
    public void init() {
        if(hasInit()) return;

        registerBlockIgnoringMetadata(AtelierBlocks.sampleBlock, Category.METAL);

        hasInit = true;
    }

    @Override
    public boolean hasInit() {
        return hasInit;
    }
}

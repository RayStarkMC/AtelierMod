package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import raystark.atelier.api.category.Category;
import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.registry.ICategoryRegistry;
import raystark.atelier.common.block.AtelierBlocks;

import java.util.*;

/**
 * ICategoryRegistryの1.7.10実装。
 *
 *
 */
public final class CategoryRegistry implements ICategoryRegistry<Item, Block, ItemStack> {
    //TODO カテゴリregistry実装

    /**
     * レジストリの実装クラス。
     *
     * <p>この実装ではアイテムとブロックを分けて登録します。
     * この実装ではメタデータにワイルドカードが定義されており、任意のメタデータのアイテムを登録対象とする。
     */
    private static final class Registry {
        private final List<ElementWithMetadata<Item>> items;
        private final List<ElementWithMetadata<Block>> blocks;

        private static final int WILD_CARD = Integer.MIN_VALUE;

        private Registry() {
            items = new ArrayList<>();
            blocks = new ArrayList<>();
        }

        //TODO 重複登録の禁止実装
        private void addItem(Item item, int meta) {
            if(!containsItem(Objects.requireNonNull(item, "item must not be null."), checkItemMeta(meta)))
                items.add(new ElementWithMetadata<>(item, meta));
        }

        private int checkItemMeta(int meta) {
            if(meta < 0 && meta != WILD_CARD)
                throw new IllegalArgumentException("0 <= meta: " + meta);
            return meta;
        }

        private boolean containsItem(Item item, int meta) {
            return items.contains(new ElementWithMetadata<>(item, WILD_CARD)) || items.contains(new ElementWithMetadata<>(item, meta));
        }

        private void addBlock(Block block, int meta) {
            ElementWithMetadata<Block> element = new ElementWithMetadata<>(Objects.requireNonNull(block, "block must not be null."), checkBlockMeta(meta));
            if(!blocks.contains(element)) blocks.add(element);
        }

        private int checkBlockMeta(int meta) {
            if(meta != WILD_CARD && (meta < 0 || meta > 16))
                throw new IllegalArgumentException("0 <= meta <= 15: " + meta);
            return meta;
        }

        private List<ElementWithMetadata<Item>> getItems() {
            return Collections.unmodifiableList(items);
        }

        private List<ElementWithMetadata<Block>> getBlockw() {
            return Collections.unmodifiableList(blocks);
        }
    }

    /**
     * メタデータを持つ要素を表すクラス。
     *
     * 要素とint型で表されるメタデータを持ちます。
     *
     * @param <E> 要素の型
     */
    private static final class ElementWithMetadata<E> {
        private final E element;
        private final int metadata;

        /**
         * メタデータ付き要素を生成します。
         *
         * 要素としてnullを利用することが出来ます。
         *
         * @param element nullを許容する要素
         * @param metadata メタデータ
         */
        ElementWithMetadata(E element, int metadata){
            this.element = element;
            this.metadata = metadata;
        }

        /**
         * 要素を返します。
         *
         * @return 要素
         */
        private E getElement() {
            return element;
        }

        /**
         * メタデータを返します。
         *
         * @return メタデータ
         */
        private int getMetadata() {
            return metadata;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ElementWithMetadata<?> that = (ElementWithMetadata<?>) o;
            return metadata == that.metadata &&
                    Objects.equals(element, that.element);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, metadata);
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

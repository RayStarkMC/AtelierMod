package raystark.atelier.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import raystark.atelier.api.category.Category;
import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.registry.ICategoryRegistry;
import raystark.atelier.common.block.AtelierBlocks;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ICategoryRegistryの1.7.10実装。
 */
public final class CategoryRegistry implements ICategoryRegistry<Item, Block, ItemStack> {
    //TODO カテゴリレジストリテスト

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

        private void addItem(Item item, int meta) {
            if(Objects.requireNonNull(item, "item must not be null.") instanceof ItemBlock) {
                addBlock(((ItemBlock)item).field_150939_a, meta);
                return;
            }

            ElementWithMetadata<Item> element = new ElementWithMetadata<>(item, checkItemMeta(meta));

            if(meta == WILD_CARD) items.removeIf(e -> e.getElement() == item);
            else if(items.contains(new ElementWithMetadata<>(item, WILD_CARD)) || items.contains(element)) return;

            items.add(element);
        }

        private int checkItemMeta(int meta) {
            if(meta < 0 && meta != WILD_CARD)
                throw new IllegalArgumentException("0 <= meta: " + meta);
            return meta;
        }

        private void addBlock(Block block, int meta) {
            ElementWithMetadata<Block> element = new ElementWithMetadata<>(Objects.requireNonNull(block, "block must not be null."), checkBlockMeta(meta));

            if(meta == WILD_CARD) blocks.removeIf(e -> e.getElement() == block);
            else if(blocks.contains(new ElementWithMetadata<>(block, WILD_CARD)) || blocks.contains(element)) return;

            blocks.add(element);
        }

        private int checkBlockMeta(int meta) {
            if(meta != WILD_CARD && (meta < 0 || meta > 16))
                throw new IllegalArgumentException("0 <= meta <= 15: " + meta);
            return meta;
        }

        private boolean contains(ItemStack stack) {
            int meta = Objects.requireNonNull(stack, "stack must not be null.").getItemDamage();
            Item item = stack.getItem();
            return item instanceof ItemBlock ?
                    getBlocks().contains(new ElementWithMetadata<>(((ItemBlock) item).field_150939_a, WILD_CARD))
                    || getBlocks().contains(new ElementWithMetadata<>(((ItemBlock) item).field_150939_a, meta))
                    : getItems().contains(new ElementWithMetadata<>(item, WILD_CARD))
                    || getItems().contains(new ElementWithMetadata<>(item, meta));
        }

        private List<ElementWithMetadata<Item>> getItems() {
            return Collections.unmodifiableList(items);
        }

        private List<ElementWithMetadata<Block>> getBlocks() {
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

    private final Map<IMaterialCategory, Registry> categoryMap;

    public CategoryRegistry() {
        hasInit = false;
        categoryMap = new HashMap<>();
    }

    /**
     * アイテムを指定したカテゴリに登録します。
     *
     * <p>この実装では{@code item instanceof ItemBlock}を満たす場合itemはBlockとして登録されます。
     * 指定したメタデータが既に登録されていた場合、又は指定したアイテムがメタデータを無視して登録されていた場合、
     * このメソッドは何もしません。
     *
     * @param item アイテム
     * @param meta メタデータ
     * @param category カテゴリ
     *
     * @throws NullPointerException アイテム又はカテゴリがnullの場合
     * @throws IllegalArgumentException メタデータが不正な場合
     */
    @Override
    public void registerItem(Item item, int meta, IMaterialCategory category) {
        if(!categoryMap.containsKey(Objects.requireNonNull(category, "category must not be null")))
            categoryMap.put(category, new Registry());

        categoryMap.get(category).addItem(item, meta);
    }

    /**
     * アイテムを指定したカテゴリにメタデータを無視して登録します。
     *
     * <p>この実装ではアイテムの登録にregisterItem(Item item, int meta, IMaterialCategory category)を利用します。
     * 指定したアイテムがメタデータを無視して登録されていた場合、このメソッドは何もしません。
     * 指定したアイテムがメタデータを指定して登録されていた場合、このメソッドはそれらの要素を全て消去し、
     * 改めてメタデータを無視して登録されます。
     *
     * @param item アイテム
     * @param category カテゴリ
     * @throws NullPointerException アイテム又はカテゴリがnullの場合
     */
    @Override
    public void registerItemIgnoringMetadata(Item item, IMaterialCategory category) {
        registerItem(item, Registry.WILD_CARD, category);
    }

    /**
     * ブロックを指定したカテゴリに登録します。
     *
     * 指定したメタデータが既に登録されていた場合、又は指定したブロックがメタデータを無視して登録されていた場合、
     * このメソッドは何もしません。
     *
     * @param block ブロック
     * @param meta メタデータ
     * @param category カテゴリ
     * @throws NullPointerException ブロック又はカテゴリがnullの場合
     * @throws IllegalArgumentException メタデータが不正の場合
     */
    @Override
    public void registerBlock(Block block, int meta, IMaterialCategory category) {
        if(!categoryMap.containsKey(Objects.requireNonNull(category, "category must not be null.")))
            categoryMap.put(category, new Registry());

        categoryMap.get(category).addBlock(block, meta);
    }

    /**
     * ブロックを指定したカテゴリにメタデータを無視して登録します。
     *
     * <p>この実装ではブロックの登録にregisterBlock(Block block, int meta, IMaterialCategory category)を利用します。
     * 指定したブロックがメタデータを無視して登録されていた場合、このメソッドは何もしません。
     * 指定したブロックがメタデータを指定して登録されていた場合、このメソッドはそれらの要素を全て消去し、
     * 改めてメタデータを無視して登録されます。
     *
     * @param block ブロック
     * @param category カテゴリ
     * @throws NullPointerException アイテム又はカテゴリがnullの場合
     */
    @Override
    public void registerBlockIgnoringMetadata(Block block, IMaterialCategory category) {
        registerBlock(block, Registry.WILD_CARD, category);
    }

    /**
     * アイテムスタックを指定したカテゴリに登録します。
     *
     * <p>ignoreMetaがtrueの場合、アイテムスタックのメタデータを無視して登録します。
     * falseの場合はアイテムスタックのメタデータを用いて登録します。
     *
     * <p>この実装ではregisterItem(Item item, int meta, IMaterialCategory category)を利用します。
     *
     * @param itemStack アイテムスタック
     * @param ignoreMeta trueの場合メタデータを無視する。
     * @param category カテゴリ
     *
     * @throws NullPointerException アイテムスタック、又はカテゴリがnullの場合
     */
    @Override
    public void registerItemStack(ItemStack itemStack, boolean ignoreMeta, IMaterialCategory category) {
        registerItem(Objects.requireNonNull(itemStack, "itemStack must not be null.").getItem(), ignoreMeta ? Registry.WILD_CARD : itemStack.getItemDamage(), category);
    }

    @Override
    public List<IMaterialCategory> getCategories(ItemStack stack) {
        return categoryMap.entrySet().stream()
                .filter(e -> e.getValue().contains(Objects.requireNonNull(stack, "stack must not be null.")))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<IMaterialCategory> getCategories() {
        return Collections.unmodifiableList(new ArrayList<>(categoryMap.keySet()));
    }

    @Override
    public List<ItemStack> getItems(IMaterialCategory category) {
        if(!categoryMap.containsKey(Objects.requireNonNull(category)))
            categoryMap.put(category, new Registry());

        Registry registry = categoryMap.get(category);
        List<ItemStack> itemList = new ArrayList<>();
        itemList.addAll(registry.getItems().stream().map(e -> new ItemStack(e.getElement(), 1, e.getMetadata() == Registry.WILD_CARD ? 0 : e.getMetadata())).collect(Collectors.toList()));
        itemList.addAll(registry.getBlocks().stream().map(e -> new ItemStack(e.getElement(), 1, e.getMetadata() == Registry.WILD_CARD ? 0 : e.getMetadata())).collect(Collectors.toList()));
        return itemList;
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
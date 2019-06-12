package raystark.atelier.api.registry;

import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.util.Initializable;

/**
 * アイテム又はカテゴリをブロックに登録するクラスのインターフェース。
 * @param <I> アイテムの型
 * @param <B> ブロックの型
 * @param <S> アイテムスタックの型
 */
public interface ICategoryRegistry<I, B, S> extends Initializable {
    /**
     * アイテムを指定したカテゴリに登録します。
     *
     * @param item 登録するアイテム
     * @param category 登録するカテゴリ
     */
    void registerItem(I item, IMaterialCategory category);

    /**
     * ブロックを指定したカテゴリに登録します
     * @param block 登録するブロック
     * @param category 登録するカテゴリ
     */
    void registerBlock(B block, IMaterialCategory category);

    /**
     * アイテムスタックを指定したカテゴリに登録します
     * @param itemStack 登録するアイテムスタック
     * @param category 登録するカテゴリ
     */
    void registerItemStack(S itemStack, IMaterialCategory category);

    /**
     * アイテムスタックがそのカテゴリに属しているか調べます。
     * @param itemStack アイテムスタック
     * @param category カテゴリ
     * @return アイテムスタックがカテゴリに属している場合true
     */
    boolean isItemStackBelongToCategory(S itemStack, IMaterialCategory category);
}

package raystark.atelier.api.registry;

import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.util.Initializable;

import java.util.List;

/**
 * アイテムのカテゴリ分けのレジストリ。
 *
 * <p>カテゴリをキーにアイテム, ブロックをレジストリに登録することが出来ます。
 * このレジストリはレシピに参照され、レシピのカテゴリ要求に利用されます。
 *
 * @param <I> アイテムの型
 * @param <B> ブロックの型
 * @param <S> アイテムスタックの型
 *
 * @author RayStark
 */
public interface ICategoryRegistry<I, B, S> extends Initializable {
    /**
     * アイテムを指定したカテゴリに登録します。
     *
     * @param item アイテム
     * @param meta メタデータ
     * @param category カテゴリ
     */
    void registerItem(I item, int meta, IMaterialCategory category);

    /**
     * アイテムを指定したカテゴリにメタデータを無視して登録します。
     *
     * @param item アイテム
     * @param category カテゴリ
     */
    void registerItemIgnoringMetadata(I item, IMaterialCategory category);

    /**
     * ブロックを指定したカテゴリに登録します。
     *
     * @param block ブロック
     * @param meta メタデータ
     * @param category カテゴリ
     */
    void registerBlock(B block, int meta, IMaterialCategory category);

    /**
     * ブロックを指定したカテゴリにメタデータを無視して登録します。
     *
     * @param block ブロック
     * @param category カテゴリ
     */
    void registerBlockIgnoringMetadata(B block, IMaterialCategory category);

    /**
     * アイテムスタックを指定したカテゴリに登録します。
     *
     * <p>ignoreMetaがtrueの場合、アイテムスタックのメタデータを無視して登録します。
     * falseの場合はアイテムスタックのメタデータを用いて登録します。
     *
     * @param itemStack アイテムスタック
     * @param ignoreMeta trueの場合メタデータを無視する。
     * @param category カテゴリ
     */
    void registerItemStack(S itemStack, boolean ignoreMeta, IMaterialCategory category);

    /**
     * 引数のアイテムスタックが属すカテゴリの不変リストを返します。
     *
     * @param itemStack アイテムスタック
     * @return カテゴリの不変リスト
     */
    List<IMaterialCategory> getCategories(S itemStack);

    /**
     * 引数のカテゴリに属すアイテムスタックの不変リストを返します。
     *
     * @param category カテゴリ
     * @return アイテムの不変リスト
     */
    List<S> getItems(IMaterialCategory category);
}

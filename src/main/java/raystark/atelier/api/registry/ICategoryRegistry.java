package raystark.atelier.api.registry;

import raystark.atelier.api.category.IMaterialCategory;
import raystark.atelier.api.util.Initializable;

import java.util.List;

/**
 * アイテムのカテゴリ分けのレジストリ。
 *
 * <p>カテゴリをキーにアイテム, ブロックをレジストリに登録することが出来ます。
 * このレジストリはレシピに参照され、レシピのカテゴリ要求に利用されます。
 * レジストリは重複要素を許さず、そのような要素を登録しようとした場合は何も起こりません。
 * レジストリはnull要素、又はnullカテゴリを認めず、登録しようとした場合はNullPointerExceptionがスローされます。
 * メタデータは不正値を定めることが出来、レジストリに追加しようとした場合にIllegalArgumentExceptionがスローされます。
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
     *
     * @throws NullPointerException アイテム、又はカテゴリがnullの場合
     * @throws IllegalArgumentException メタデータ、またはアイテムが不正の場合
     */
    void registerItem(I item, int meta, IMaterialCategory category);

    /**
     * アイテムを指定したカテゴリにメタデータを無視して登録します。
     *
     * @param item アイテム
     * @param category カテゴリ
     *
     * @throws NullPointerException アイテム、又はカテゴリがnullの場合
     */
    void registerItemIgnoringMetadata(I item, IMaterialCategory category);

    /**
     * ブロックを指定したカテゴリに登録します。
     *
     * @param block ブロック
     * @param meta メタデータ
     * @param category カテゴリ
     *
     * @throws NullPointerException アイテム、又はカテゴリがnullの場合
     * @throws IllegalArgumentException メタデータが不正の場合
     */
    void registerBlock(B block, int meta, IMaterialCategory category);

    /**
     * ブロックを指定したカテゴリにメタデータを無視して登録します。
     *
     * @param block ブロック
     * @param category カテゴリ
     *
     * @throws NullPointerException アイテム、又はカテゴリがnullの場合
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
     *
     * @throws NullPointerException アイテムスタック、又はカテゴリがnullの場合
     */
    void registerItemStack(S itemStack, boolean ignoreMeta, IMaterialCategory category);

    /**
     * 引数のアイテムスタックが属すカテゴリの不変リストを返します。
     *
     * @param itemStack アイテムスタック
     * @return カテゴリの不変リスト
     *
     * @throws NullPointerException アイテムスタックがnullの場合
     */
    List<IMaterialCategory> getCategories(S itemStack);

    /**
     * このレジストリに登録されているすべてのカテゴリの不変リストを返します。
     *
     * @return カテゴリの不変リスト
     */
    List<IMaterialCategory> getCategories();

    /**
     * 引数のカテゴリに属すアイテムスタックの不変リストを返します。
     *
     * @param category カテゴリ
     * @return アイテムの不変リスト
     *
     * @throws NullPointerException categoryがnullの場合
     */
    List<S> getItems(IMaterialCategory category);
}

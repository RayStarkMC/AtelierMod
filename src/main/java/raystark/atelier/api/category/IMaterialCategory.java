package raystark.atelier.api.category;

/**
 * 錬金素材のカテゴリを表すインターフェース.
 */
public interface IMaterialCategory {
    /**
     * 鉱石辞書名の接頭辞
     */
    String PREFIX = "category";

    //他Modがカテゴリ追加する可能性を考慮してインターフェースを定義

    /**
     * カテゴリに対応する鉱石辞書名を返します。
     *
     * 鉱石辞書名はprefixから始まる必要があります。
     * @return カテゴリの鉱石辞書名
     */
    String getOreDictName();
}

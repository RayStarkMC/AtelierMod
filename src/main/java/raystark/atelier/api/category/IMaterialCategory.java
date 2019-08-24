package raystark.atelier.api.category;

/**
 * 錬金素材のカテゴリを表すインターフェース.
 *
 * @author RayStark
 */
//他Modがカテゴリ追加する可能性を考慮してインターフェースを定義
public interface IMaterialCategory {
    // TODO 鉱石辞書への依存をなくす


    /**
     * カテゴリに対応する鉱石辞書名を返します。
     *
     * 鉱石辞書名はprefixから始まる必要があります。
     * @return カテゴリの鉱石辞書名
     */
    String getOreDictName();
}



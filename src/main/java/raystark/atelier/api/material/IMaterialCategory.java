package raystark.atelier.api.material;

/**
 * 錬金素材のカテゴリを表すインターフェース.
 */
public interface IMaterialCategory {
    //他Modがカテゴリ追加する可能性を考慮してインターフェースを定義

    /**
     * カテゴリに対応する鉱石辞書名を返す.
     * @return カテゴリの鉱石辞書名
     */
    String getOreDictName();
}

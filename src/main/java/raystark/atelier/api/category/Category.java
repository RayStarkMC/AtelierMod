package raystark.atelier.api.category;

/**
 * 錬金素材のカテゴリの列挙
 *
 * 各カテゴリは対応した鉱石辞書名を持つ。
 */
public enum Category implements IMaterialCategory {
    METAL("Metal"),
    PLANT("Plant");

    Category(String oreDictName) {
        this.oreDictName = oreDictName;
    }

    private String oreDictName;

    @Override
    public String getOreDictName() {
        return PREFIX + oreDictName;
    }

    // TODO どんなカテゴリを実装しよう？
}

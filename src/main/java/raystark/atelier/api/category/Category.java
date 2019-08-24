package raystark.atelier.api.category;

/**
 * 錬金素材のカテゴリの列挙
 *
 * 各カテゴリは対応した鉱石辞書名を持つ。
 *
 * @author RayStark
 */
public enum Category implements IMaterialCategory {
    METAL("Metal"),         //金属
    PLANT("Plant"),         //植物
    DAILY("Daily"),         //日用品
    VALUABLE("Valuable"),   //貴重品
    WOOD("wood");           //木材



    Category(String oreDictName) {
        this.oreDictName = oreDictName;
    }

    private String oreDictName;

    @Override
    public String getOreDictName() {
        return oreDictName;
    }

    // TODO どんなカテゴリを実装しよう？
}

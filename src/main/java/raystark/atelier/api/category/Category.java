package raystark.atelier.api.category;

/**
 * 錬金素材のカテゴリの列挙
 *
 * @author RayStark
 */
public enum Category implements IMaterialCategory {
    /**
     * 金属カテゴリ
     */
    METAL("Metal"),

    /**
     * 植物カテゴリ
     */
    PLANT("Plant"),

    /**
     * 日用品カテゴリ
     */
    DAILY("Daily"),

    /**
     * 貴重品カテゴリ
     */
    VALUABLE("Valuable"),

    /**
     * 木材カテゴリ
     */
    WOOD("Wood");

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;

    @Override
    public String getCategoryName() {
        return categoryName;
    }
}

package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.category.IMaterialCategory;

import java.util.Objects;

/**
 * 要求物としてカテゴリを指定するIRequirementの骨格実装です。
 *
 * <p>このクラスではMinecraftのバージョンに依存しない実装を提供します。
 *
 * @param <S> アイテムスタックの型
 */
public abstract class AbstractCategoryRequirement<S> implements ICategoryRequirement<S> {

    private final IMaterialCategory category;

    protected AbstractCategoryRequirement(IMaterialCategory category) {
        this.category = Objects.requireNonNull(category, "category must not be null.");
    }

    @Override
    public String getName() {
        return category.getCategoryName();
    }

    @Override
    public IMaterialCategory getCategory() {
        return category;
    }
}

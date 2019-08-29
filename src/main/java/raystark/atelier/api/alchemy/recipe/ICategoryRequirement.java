package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.category.IMaterialCategory;

/**
 *
 * @param <S> アイテムスタックの型
 */
public interface ICategoryRequirement<S> extends IRequirement<S>{
    IMaterialCategory getCategory();
}

package raystark.atelier.api.material;

import java.util.Set;

/**
 * 分類分けされたものを表すインターフェース
 */
public interface Categorized {

    /**
     * その素材が属すカテゴリを返す。
     * @return 素材の属すカテゴリ
     */
    Set<IMaterialCategory> getCategory();
}

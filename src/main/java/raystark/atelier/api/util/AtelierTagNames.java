package raystark.atelier.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AtelierModで使用されるNBTのタグ名の列挙です。
 *
 * @author RayStark
 */
public enum AtelierTagNames {
    /**
     * AtelierModのタグ名。
     *
     * <p>本Modのタグはすべてこのタグの元に保存される。
     */
    ATELIER,

    /**
     * 品質値のタグ名。
     */
    ATELIER_QUALITY,

    /**
     * 効果リストのタグ名。
     */
    ATELIER_EFFECT,

    /**
     * 潜在能力リストのタグ名。
     */
    ATELIER_POTENTIAL;

    private static final List<AtelierTagNames> PRODUCT_STATUS_TAG_LIST;

    static {
        List<AtelierTagNames> productStatsList = new ArrayList<>();
        productStatsList.add(ATELIER_QUALITY);
        productStatsList.add(ATELIER_EFFECT);
        productStatsList.add(ATELIER_POTENTIAL);
        PRODUCT_STATUS_TAG_LIST = Collections.unmodifiableList(productStatsList);
    }

    public static List<AtelierTagNames> getProductStatusTagList() { return PRODUCT_STATUS_TAG_LIST; }
}

package raystark.atelier.api.util;

import java.util.EnumSet;
import java.util.Set;

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

    private static final Set<AtelierTagNames> PRODUCT_STATUS_TAG_SET = EnumSet.of(ATELIER_QUALITY, ATELIER_EFFECT, ATELIER_POTENTIAL);

    public static Set<AtelierTagNames> getProductStatusTagSet() { return PRODUCT_STATUS_TAG_SET; }
}

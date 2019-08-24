package raystark.atelier.api.util;

/**
 * AtelierModで使用されるNBTのタグ名の列挙です。
 *
 * @author RayStark
 */
public enum NBTTagNames {
    /**
     * AtelierModのタグ名。
     *
     * <p>本Modのタグはすべてこのタグの元に保存される。
     */
    TAG_ATELIER,

    /**
     * 品質値のタグ名。
     */
    TAG_QUALITY,

    /**
     * 効果リストのタグ名。
     */
    TAG_EFFECT,

    /**
     * 潜在能力リストのタグ名。
     */
    TAG_POTENTIAL
}

package raystark.atelier.api.alchemy.status;

/**
 * 属性値を表すインターフェース。
 *
 * <p>属性値は1種類
 * @param <E>
 */
public interface IElementValue<E extends Elements> {
    /**
     * この属性値の種類を返します。
     *
     * @return この属性値の種類
     */
    Elements getElementType();

    /**
     * この属性値の値を返します。
     *
     * @return この属性値の値
     */
    int getValue();
}

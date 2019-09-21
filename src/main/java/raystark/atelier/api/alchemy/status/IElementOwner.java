package raystark.atelier.api.alchemy.status;

/**
 * 属性値を持つものを表すインターフェース。
 * <p>
 * 属性値は0-100までの値で存在し, 錬金術において完成品の能力決定に使われる.
 * @author RayStark
 */
public interface IElementOwner {
    /**
     * 属性の最小値
     */
    int MIN_VALUE = 0;

    /**
     * 属性の最大値
     */
    int MAX_VALUE = 100;


    /**
     * 引数で渡された属性に対する属性値を返す。
     *
     * @param elementType 属性の種類
     * @return 属性値
     */
    int getElementValue(Elements elementType);
}

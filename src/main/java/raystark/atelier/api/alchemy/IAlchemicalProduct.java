package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術で作られた製品を表すインターフェース。
 *
 * <p>製品はステータスを持ちます。製品のステータスはgetStatusメソッドで取得できます。
 *
 * @param <S> この製品のステータスを保持するインスタンスの型
 * @see IProductStatus
 *
 * @author RayStark
 */
public interface IAlchemicalProduct<S> {
    /**
     * sourceのステータスを取得します。
     *
     * <p>このメソッドはnullを返しません。
     * @param source ステータスを保持するインスタンス
     * @return sourceのステータス
     * @throws NullPointerException sourceがnullの場合
     * @throws IllegalArgumentException sourceがステータスを持たない場合
     */
    IProductStatus getStatus(S source);
}

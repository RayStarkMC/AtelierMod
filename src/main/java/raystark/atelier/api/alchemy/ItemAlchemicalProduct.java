package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られた製品を表すインターフェース。
 *
 * @param <S> アイテムスタックの型
 */
public interface ItemAlchemicalProduct<S> extends IAlchemicalProduct{

    /**
     * この製品のステータスを返す。
     *
     * @param dataSource データの保存先
     * @return この製品のステータス
     */
    IProductStatus getStatus(S dataSource);

}

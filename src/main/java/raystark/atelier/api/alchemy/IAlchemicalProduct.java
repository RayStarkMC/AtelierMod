package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られた製品を表すインターフェース。
 *
 * @param <T> データを保存しているクラスの型
 */
public interface IAlchemicalProduct<T>{

    /**
     * この製品のステータスを返す。
     *
     * @param dataSource データの保存先
     * @return この製品のステータス
     */
    IProductStatus getStatus(T dataSource);

}

package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られたアイテムを表すインターフェース。
 *
 * @param <T> データを保存しているクラスの型
 */
public interface ItemAlchemicalProduct<T> extends IAlchemicalProduct{

    /**
     * この製品のステータスを返す。
     *
     * @param dataSource データの保存先
     * @return この製品のステータス
     */
    IProductStatus getStatus(T dataSource);

}

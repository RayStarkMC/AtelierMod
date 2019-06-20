package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られたブロックを表すインターフェース
 *
 * @param <W> ワールドの型
 */
public interface BlockAlchemicalProduct<W> extends IAlchemicalProduct {
    /**
     * この製品のステータスを返します。
     *
     * <p>worldのTileEntityからステータスを取り出します。
     */
    IProductStatus getStatus(int x, int y, int z, W world);
}

package raystark.atelier.api.alchemy;

import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られたブロックを表すインターフェース
 *
 * @param <W> ワールドの型
 *
 * @author RayStark
 */
public interface BlockAlchemicalProduct<W> extends IAlchemicalProduct {
    /**
     * この製品のステータスを返します。
     *
     * <p>worldのTileEntityからステータスを取り出します。
     * @param x x座標
     * @param y y座標
     * @param z z座標
     * @param world ワールド
     * @return ステータス
     */
    IProductStatus getStatus(int x, int y, int z, W world);
}

package raystark.atelier.api.alchemy;

import net.minecraft.tileentity.TileEntity;
import raystark.atelier.api.alchemy.status.IProductStatus;

/**
 * 錬金術によって作られたブロックを表すインターフェース
 *
 * @author RayStark
 */
public interface IBlockAlchemicalProduct extends IAlchemicalProduct<TileEntity> {
    /**
     * この製品のステータスを返します。
     *
     * <p>worldのTileEntityからステータスを取り出します。
     * @param tileEntity タイルエンティティ
     * @return ステータス
     * @throws NullPointerException tileEntityがnullの場合
     * @throws IllegalArgumentException tileEntityがステータスを持たない場合
     */
    IProductStatus getStatus(TileEntity tileEntity);
}

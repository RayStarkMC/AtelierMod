package raystark.atelier.api.registry;

import raystark.atelier.api.alchemy.status.IMaterialStatus;
import raystark.atelier.api.util.Initializable;

import java.util.Optional;

/**
 * 材料の属性値、およびデフォルトの品質値、潜在能力のレジストリ
 *
 * @param <I> アイテムクラスの型
 * @param <B> ブロッククラスの型
 *
 * @author RayStark
 */
public interface IMaterialRegistry<I, B> extends Initializable {

    /**
     * 引数で与えられたダメージ値のアイテムに対してデフォルトのステータスを登録します。
     *
     * @param materialItem 材料
     * @param damage ダメージ値
     * @param status デフォルトの能力値
     */
    Optional<IMaterialStatus> registerDefaultItemStatus(I materialItem, int damage, IMaterialStatus status);

    /**
     * 引数で与えられたアイテムに対して、ダメージ値を無視してデフォルトのステータスを登録します。
     *
     * @param materialItem 材料
     * @param status デフォルトの能力値
     */
    Optional<IMaterialStatus> registerDefaultItemStatus(I materialItem, IMaterialStatus status);

    /**
     * 引数で与えられたダメージ値のアイテムのデフォルトステータスを取り出します。
     *
     * @param materialItem 材料
     * @param damage ダメージ値
     * @return ステータス
     */
    IMaterialStatus getDefaultItemStatus(I materialItem, int damage);

    /**
     * 引数で与えられたアイテムのデフォルトステータスを取り出します。
     *
     * <p>ダメージ値0のアイテムのステータスを取り出します。
     *
     * @param materialItem 材料
     * @return ステータス
     */
    IMaterialStatus getDefaultItemStatus(I materialItem);

    /**
     * 引数で与えられたメタ値のブロックに対してデフォルトのステータスを登録します。
     *
     * @param materialBlock 材料
     * @param metadata メタデータ
     * @param status ステータス
     */
    Optional<IMaterialStatus> registerDefaultBlockStatus(B materialBlock, int metadata, IMaterialStatus status);

    /**
     * 引数で与えられたブロックに対して、メタ値を無視してステータスを登録します。
     *
     * @param materialBlock 材料
     * @param status ステータス
     */
    Optional<IMaterialStatus> registerDefaultBlockStatus(B materialBlock, IMaterialStatus status);

    /**
     * 引数で与えられたメタ値のブロックのデフォルトステータスを取り出します。
     *
     * @param materialBlock 材料
     * @param metadata メタ値
     * @return ステータス
     */
    IMaterialStatus getDefaultBlockStatus(B materialBlock, int metadata);

    /**
     * 引数で与えられたブロックのデフォルトステータスを取り出します。
     *
     * <p>メタ値0のブロックのステータスを取り出します。
     *
     * @param materialBlock 材料
     * @return ステータス
     */
    IMaterialStatus getDefaultBlockStatus(B materialBlock);

    interface IMaterialKey<E> {
        E getElement();
        int getMetadata();
    }
}

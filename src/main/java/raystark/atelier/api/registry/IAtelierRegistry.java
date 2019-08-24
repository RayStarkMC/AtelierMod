package raystark.atelier.api.registry;

import raystark.atelier.api.util.Initializable;

/**
 * エフェクトや潜在能力の種類と効果、各アイテムの属性値やデフォルトの潜在能力のレジストリを表すインターフェース。
 *
 * @param <I> アイテム
 * @param <B> ブロック
 * @param <S> アイテムスタック
 *
 * @author RayStark
 */
public interface IAtelierRegistry<I, B, S> extends Initializable {
    /**
     * エフェクトレジストリを返します。
     *
     * @return エフェクトのレジストリ
     */
    IEffectRegistry getEffectRegistry();

    /**
     * 潜在能力レジストリを返します。
     *
     * @return 潜在能力のレジストリ
     */
    IPotentialAbilityRegistry getPotentialAbilityRegistry();

    /**
     * 材料のデフォルトステータスのレジストリを返します。
     *
     * @return 材料のデフォルトステータスのレジストリ
     */
    IMaterialRegistry<I, B> getMaterialRegistry();

    /**
     * カテゴリレジストリを返します。
     *
     * @return カテゴリレジストリ
     */
    ICategoryRegistry<I, B, S> getCategoryRegistry();
}

package raystark.atelier.api.registry;

/**
 * エフェクトや潜在能力の種類と効果、各アイテムの属性値やデフォルトの潜在能力のレジストリを表すインターフェース。
 */
public interface IAtelierRegistry {

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
    IMaterialRegistry getMaterialRegistry();


}
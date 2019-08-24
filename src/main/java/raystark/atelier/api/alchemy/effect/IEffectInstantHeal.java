package raystark.atelier.api.alchemy.effect;

/**
 * 即時回復の効果を表すインターフェース
 *
 * @author RayStark
 */
public interface IEffectInstantHeal extends IEffect{
    /**
     * 回復するHP量を返します。
     * @return HP回復量
     */
    int getAmountOfHeal();
}

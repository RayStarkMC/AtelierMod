package raystark.atelier.api.alchemy.effect;

/**
 * アイテムに採掘レベルを与える効果
 */
public interface IEffectMiningLevel extends IEffect {
    /**
     * この効果の採掘レベル
     * @return 採掘レベル
     */
    int getMiningLevel();
}

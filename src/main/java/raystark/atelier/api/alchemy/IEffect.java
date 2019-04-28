package raystark.atelier.api.alchemy;

/**
 * 錬金術製品の効果を表すインターフェース.<p/>
 *
 * 製品の主な動作は効果によって決まる。製品によっては複数の効果が付与される場合がある。
 */
public interface IEffect {
    /**
     * 効果名を返す。
     * @return 効果名
     */
    String gatName();

    /**
     * ツールチップ上で表示される効果名を返す。
     * @return ツールチップ上のテキスト
     */
    String getToolTipText();
}
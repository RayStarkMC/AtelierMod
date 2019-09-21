package raystark.atelier.api.alchemy.effect;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.alchemy.status.Quality;

/**
 * 錬金術製品の効果を表すインターフェース.
 * <p>製品の主な動作は効果によって決まる。製品によっては複数の効果が付与される場合がある。
 * {@link IPotentialAbility 潜在能力}の影響を受ける場合がある。
 * {@link Quality 品質}の影響を受ける場合がある。
 *
 * @author RayStark
 */
public interface IEffect {
    /**
     * 効果名を返す。
     * <p>
     * 効果名はNBTタグのキーに利用される。
     * @return 効果名
     */
    String getName();

    /**
     * ツールチップ上で表示される効果名を返す。
     * @return ツールチップ上のテキスト
     */
    String getToolTipText();
}

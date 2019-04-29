package raystark.atelier.api.effect;

import raystark.atelier.api.alchemy.IPotentialAbility;
import raystark.atelier.api.alchemy.Quality;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 錬金術製品の効果を表すインターフェース.<p/>
 *
 * 製品の主な動作は効果によって決まる。製品によっては複数の効果が付与される場合がある。
 * {@link IPotentialAbility 潜在能力}の影響を受ける場合がある。
 * {@link Quality 品質}の影響を受ける場合がある。
 */
public interface IEffect {
    /**
     * 効果名を返す。<p/>
     * 効果名はNBTタグのキーに利用される。
     * @return 効果名
     */
    String getName();

    /**
     * 調合時に表示される効果の分類名を返す。
     * @return 効果の分類名
     */
    String getNameOnAlchemy();

    /**
     * ツールチップ上で表示される効果名を返す。
     * @return ツールチップ上のテキスト
     */
    String getToolTipText();
}

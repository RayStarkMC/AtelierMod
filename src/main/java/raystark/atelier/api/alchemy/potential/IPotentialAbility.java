package raystark.atelier.api.alchemy.potential;

import raystark.atelier.api.alchemy.effect.IEffect;

/**
 * 錬金術製品につく潜在能力を表すインターフェース。
 * <p>
 * {@link IEffect 効果}に対して影響を与え、その性能を強化・変化させる。効果を持たないアイテムに対しては機能しないが、錬金術によって他の製品に引き継ぐことが出来る。
 *
 * @author RayStark
 */
public interface IPotentialAbility {
    /**
     * この潜在能力の名前を返す。
     * @return 潜在能力名
     */
    String getName();

    /**
     * ツールチップ上で表示される潜在能力名を返す。
     * @return ツールチップ上のテキスト
     */
    String getToolTipText();
}

package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.ElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.List;
import java.util.Optional;

/**
 * 効果予測を表すインターフェース。
 *
 * <p>効果予測は属性値と効果の対応です。効果予測には1種類の属性が定義されており、ElementOwnerからその属性値を用いて発動
 * する効果が決定されます。効果予測は属性値が高いほど強力な効果が予測されるべきであり
 *
 * @param <T> 発動する効果の種類を表す型
 */
public interface IEffectEstimated<T extends IEffect> {

    /**
     * この効果の発動に必要な属性を返します。
     *
     * @return この効果予測に対応する属性
     */
    Elements getElementType();

    /**
     * 発動する効果のリストを返します。
     *
     * @return
     */
    List<EffectEntry<T>> getEffectList();

    /**
     * 属性値から発動する効果を返します。
     *
     * @param owner 属性値
     * @return 発動する効果のOptional
     */
    Optional<T> getEffect(ElementOwner owner);

    /**
     * 効果を表す文字列を返します。
     *
     * <p>ex. "Strength" 強さ
     *
     * @return 効果を表す文字列
     */
    String getEffectString();

    /**
     *
     *
     * <p>
     *
     * @param <T>
     */
    interface EffectEntry<T extends IEffect>{

        /**
         * この効果を返します。
         *
         * @return 効果のオプショナル
         */
        Optional<T> getEffect();

        /**
         * この効果を発動させるために必要な属性値の最小値を返します。
         *
         * @return 必要な属性値の最小値
         */
        int minimumRequired();
    }
}

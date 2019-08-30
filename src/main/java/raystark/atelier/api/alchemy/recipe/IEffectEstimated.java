package raystark.atelier.api.alchemy.recipe;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.status.IElementOwner;
import raystark.atelier.api.alchemy.status.Elements;

import java.util.List;
import java.util.Optional;

/**
 * 効果予測を表すインターフェース。
 *
 * <p>効果予測は属性値と効果の対応です。効果予測には1つ以上の効果と1種類の要求属性が定義されています。属性値の大きさによっ
 * て発動する効果が変化します。効果を決定するための属性はgetElementRequiredから取得でき、その値はgetEffectに
 * 渡されたElementOwnerが持つ属性値が適用されます。属性値と効果の対応はgetEntryListから取得できます。
 * 効果予測には発動する効果を抽象的に表す名前が付いており、getEffectStringから取得できます。
 *
 * <p>空の効果予測を作成することはできません。getEntryListで取得できるリストには効果を持ったエントリが少なくとも1つ含まれる必要があります。
 *
 * @author RayStark
 */
public interface IEffectEstimated {
    /**
     * この効果の発動に必要な属性を返します。
     *
     * @return この効果予測に対応する属性
     */
    Elements getElementRequired();

    /**
     * 属性値から発動する効果を返します。
     *
     * @param owner 属性値
     * @return 発動する効果のOptional
     */
    Optional<IEffect> getEffectFromElement(IElementOwner owner);

    /**
     * 属性値と効果の対応の不変リストを返します。
     *
     * <p>空のリスト、又は空の効果のエントリのみのリストを返しません。
     *
     * @return 対応の空でない不変リスト
     */
    List<IEffectEntry> getEntryList();

    /**
     * 効果を表す文字列を返します。
     *
     * <p>ex. "Strength" 強さ
     *
     * @return 効果を表す文字列
     */
    String getEffectString();

    /**
     * 属性値と効果の対応を表すインターフェース。
     *
     * <p>エントリには属性値の最小要求値が定義されています。調合時の属性値が最小要求値以上で、
     * かつ上位のエントリの最小要求値未満の時にこのエントリの効果が発動します。
     *
     * <p>エントリには効果が含まれていない場合があります。調合時の属性値がそのエントリの効果が発動する条件を満たしていた場合、
     * アイテムには効果が付与されません。効果が含まれていないエントリを用いることで「属性値が特定の範囲内にある時のみ効果を発動する」
     * といった効果予測を作ることが出来ます。
     *
     * @author RayStark
     */
    interface IEffectEntry extends Comparable<IEffectEntry>{

        /**
         * この効果を返します。
         *
         * <p>効果が存在しない場合は空のOptionalを返します。
         *
         * @return 効果のOptional
         */
        Optional<IEffect> getEffect();

        /**
         * この効果を発動させるための属性値の最小要求値を返します。
         *
         * @return 必要な属性値の最小値
         */
        int getMinimumRequired();

        /**
         * このエントリと指定されたエントリの順序を比較します。
         *
         * <p>このメソッドは効果予測が発動する効果を選択するときに利用されることが想定されています。
         * このエントリの属性値と他方のエントリの属性値を比較し、高い場合は正、低い場合は負の整数を返します。
         * 等しい場合については未定義で、実装依存です。
         *
         * @param other 他方のエントリ
         * @return このエントリが他方のエントリより大きい場合正、等しい場合ゼロ、小さい場合負の整数を返します。
         *
         * @throws NullPointerException 他方のエントリがnullの場合
         */
        @Override
        int compareTo(IEffectEntry other);
    }
}

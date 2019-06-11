package raystark.atelier.api.registry;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.util.Initializable;

import java.util.Optional;

public interface IEffectRegistry extends Initializable {
    /**
     * ライブラリにエフェクトを登録します。
     *
     * @param effect 登録するエフェクト
     * @return 以前に登録されていたエフェクトのOptional
     */
    Optional<IEffect> registerEffect(IEffect effect);

    /**
     * 引数で渡された文字列を名前に持つエフェクトを返します。
     *
     * @param effectName エフェクト名
     * @return 名前に対応するエフェクトのOptional
     */
    Optional<IEffect> getEffect(String effectName);
}

package raystark.atelier.api.library;

import raystark.atelier.api.alchemy.effect.IEffect;

import java.util.Optional;

public interface IEffectLibrary {
    /**
     * ライブラリにエフェクトを登録します。
     *
     * @param effect 登録するエフェクト
     * @return 以前に登録されていたエフェクトがあった場合かつその時に限りtrue
     */
    boolean registerEffect(IEffect effect);

    /**
     * 引数で渡された文字列を名前に持つエフェクトを返します。
     *
     * @param effectName エフェクト名
     * @return 名前に対応するエフェクトのOptional
     */
    Optional<IEffect> getEffects(String effectName);
}

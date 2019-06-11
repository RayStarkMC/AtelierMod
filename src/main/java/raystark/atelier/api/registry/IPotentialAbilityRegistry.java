package raystark.atelier.api.registry;

import raystark.atelier.api.alchemy.effect.IEffect;
import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.util.Initializable;

import java.util.Optional;

public interface IPotentialAbilityRegistry extends Initializable {
    /**
     * ライブラリに潜在能力を登録します。
     *
     * @param ability 登録する潜在能力
     * @return 以前に登録されていた潜在能力のOptional
     */
    Optional<IPotentialAbility> registerPotentialAbility(IPotentialAbility ability);

    /**
     * 引数で渡された文字列を名前に持つ潜在能力を返します。
     *
     * @param abilityName 潜在能力名
     * @return 名前に対応する潜在能力のOptional
     */
    Optional<IPotentialAbility> getPotentialAbility(String abilityName);
}

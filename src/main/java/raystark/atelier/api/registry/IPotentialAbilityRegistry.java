package raystark.atelier.api.registry;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.util.Initializable;

import java.util.Optional;

/**
 * 潜在能力のレジストリを表すインターフェース。
 *
 * <p>レジストリには潜在能力を登録できます。登録した潜在能力は潜在能力名から取得できます。
 *
 * @author RayStark
 */
public interface IPotentialAbilityRegistry extends Initializable {
    /**
     * レジストリに潜在能力を登録します。
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

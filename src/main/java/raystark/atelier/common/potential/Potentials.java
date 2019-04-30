package raystark.atelier.common.potential;

import raystark.atelier.api.potential.IPotentialAbility;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Potentials {
    private static final Map<String, IPotentialAbility> POTENTIALS = new HashMap<>();

    private Potentials() {}

    public static void init() {

    }

    /**
     * ライブラリに潜在能力を登録します。
     *
     * @param potentialAbility 登録する潜在能力
     * @return 以前に登録されていた潜在能力があった場合かつその時に限りtrue
     */
    public static boolean registerPotential(IPotentialAbility potentialAbility) {
        IPotentialAbility lastValue = POTENTIALS.put(potentialAbility.getName(), potentialAbility);
        return lastValue == null;
    }

    /**
     * 引数で渡された文字列を名前に持つ潜在能力を返します。
     *
     * @param potentialName 潜在能力名
     * @return 名前に対応する潜在能力、又は存在しなければnull
     */
    public static Optional<IPotentialAbility> getPotential(String potentialName) {
        return Optional.ofNullable(POTENTIALS.get(potentialName));
    }
}

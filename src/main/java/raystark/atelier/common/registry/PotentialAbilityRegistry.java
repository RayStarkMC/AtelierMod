package raystark.atelier.common.registry;

import raystark.atelier.api.alchemy.potential.IPotentialAbility;
import raystark.atelier.api.registry.IPotentialAbilityRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class PotentialAbilityRegistry implements IPotentialAbilityRegistry {
    private final Map<String, IPotentialAbility> abilities;
    private boolean hasInit;

    public PotentialAbilityRegistry() {
        abilities = new HashMap<>();
        hasInit = false;
    }

    public void init() {
        if(hasInit()) return;

        hasInit = true;
    }

    @Override
    public boolean hasInit() {
        return hasInit;
    }

    @Override
    public Optional<IPotentialAbility> registerPotentialAbility(IPotentialAbility ability) {
        return Optional.ofNullable(abilities.put(ability.getName(), ability));
    }

    @Override
    public Optional<IPotentialAbility> getPotentialAbility(String abilityName) {
        return Optional.ofNullable(abilities.get(abilityName));
    }
}

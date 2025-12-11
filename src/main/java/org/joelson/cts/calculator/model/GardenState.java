package org.joelson.cts.calculator.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GardenState {

    private final Map<String, GeneratorState> generatorStates;
    private final Set<String> upgradesBought;
    private boolean boosted;

    public GardenState() {
        this.generatorStates = new HashMap<>();
        this.upgradesBought = new HashSet<>();
        this.boosted = false;
    }

    private GardenState(GardenState that) {
        generatorStates = new HashMap<>(that.generatorStates);
        upgradesBought = new HashSet<>(that.upgradesBought);
        boosted = that.boosted;
    }

    public void setGeneratorState(Generator generator, GeneratorState state) {
        generatorStates.put(generator.getName(), state);
    }

    public void setGeneratorCount(Generator generator, int count) {
        GeneratorState state = generatorStates.computeIfPresent(generator.getName(),
                (n, s) -> new GeneratorState(count, s.efficiency()));
        if (state == null) {
            throw new NullPointerException("No generator " + generator.getName() + " present.");
        }
    }

    public GeneratorState getGeneratorState(Generator generator) {
        GeneratorState state = generatorStates.get(generator.getName());
        if (state == null) {
            state = GeneratorState.EMPTY;
        }
        if (boosted) {
            return new GeneratorState(state.count(), state.efficiency() * 2);
        }
        return state;
    }

    public void setUpgradeBought(Upgrade upgrade) {
        setUpgradeBought(upgrade, true);
    }

    public void setUpgradeBought(Upgrade upgrade, boolean bought) {
        if (bought) {
            upgradesBought.add(upgrade.getName());
        } else {
            upgradesBought.remove(upgrade.getName());
        }
    }

    public boolean isUpgradeBought(Upgrade upgrade) {
        return upgradesBought.contains(upgrade.getName());
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }

    public boolean isBoosted() {
        return boosted;
    }

    public GardenState copy() {
        return new GardenState(this);
    }
}

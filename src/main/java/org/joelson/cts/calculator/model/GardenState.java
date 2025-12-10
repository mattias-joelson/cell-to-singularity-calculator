package org.joelson.cts.calculator.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GardenState {

    private final Map<String, GeneratorState> generatorStates;
    private final Set<String> upgradesBought;

    public GardenState() {
        this.generatorStates = new HashMap<>();
        this.upgradesBought = new HashSet<>();
    }

    private GardenState(GardenState that) {
        generatorStates = new HashMap<>(that.generatorStates);
        upgradesBought = new HashSet<>(that.upgradesBought);
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
        return (state == null) ? GeneratorState.EMPTY : state;
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

    public GardenState copy() {
        return new GardenState(this);
    }
}

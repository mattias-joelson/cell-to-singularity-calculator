package org.joelson.cts.calculator.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GardenState {

    private final Map<String, GeneratorState> generatorStates;
    private final Set<String> upgradesBought;
    private int boost;

    public GardenState() {
        this.generatorStates = new HashMap<>();
        this.upgradesBought = new HashSet<>();
        this.boost = 1;
    }

    private GardenState(GardenState that) {
        generatorStates = new HashMap<>(that.generatorStates);
        upgradesBought = new HashSet<>(that.upgradesBought);
        boost = that.boost;
    }

    public void setGeneratorState(Generator generator, GeneratorState state) {
        generatorStates.put(generator.getName(), state);
    }

    public void setGeneratorCount(Generator generator, int count) {
        GeneratorState state = generatorStates.computeIfPresent(generator.getName(),
                (_, s) -> new GeneratorState(count, s.efficiency()));
        if (state == null) {
            throw new NullPointerException("No generator " + generator.getName() + " present.");
        }
    }

    public GeneratorState getGeneratorState(Generator generator) {
        GeneratorState state = generatorStates.get(generator.getName());
        if (state == null) {
            state = GeneratorState.EMPTY;
        }
        if (boost > 1) {
            return new GeneratorState(state.count(), state.efficiency() * boost);
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

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public int getBoost() {
        return boost;
    }

    public void updateEfficiency(Garden garden) {
        Map<String, Float> generatorEfficiencies = new HashMap<>(garden.getGenerators().size());
        for (Generator generator : garden.getGenerators()) {
            generatorEfficiencies.put(generator.getName(), 1f);
        }
        for (Upgrade upgrade : garden.getUpgrades()) {
            if (isUpgradeBought(upgrade)) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    generatorEfficiencies.compute(effect.generator().getName(),
                            (_, generatorEfficiency) -> generatorEfficiency * effect.efficiency());
                }
            }
        }
        for (Generator generator : garden.getGenerators()) {
            GeneratorState generatorState = getGeneratorState(generator);
            float efficiency = generatorEfficiencies.get(generator.getName());
            setGeneratorState(generator, new GeneratorState(generatorState.count(), efficiency));
        }
    }

    public GardenState copy() {
        return new GardenState(this);
    }
}

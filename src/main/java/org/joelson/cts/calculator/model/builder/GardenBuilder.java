package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorRequirement;
import org.joelson.cts.calculator.model.Unlockable;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeRequirement;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GardenBuilder {

    private final Garden garden;
    private final GardenState state;
    private final Map<String, List<UnresolvedRequirement>> unresolvedRequirements;
    private final int costMultiplier;
    private final int productionMultiplier;

    public GardenBuilder(Garden garden, GardenState state, int costMultiplier, int productionMultiplier) {
        this.garden = garden;
        this.state = state;
        this.unresolvedRequirements = new HashMap<>();
        this.costMultiplier = costMultiplier;
        this.productionMultiplier = productionMultiplier;
    }

    public GardenBuilder(Garden garden, GardenState state) {
        this(garden, state, 1, 1);
    }

    public GeneratorBuilder createGenerator(
            String name, Amount baseCost, float compoundingCost, Amount baseProduction) {
        Generator generator = new Generator(name, baseCost.multiplyBy(costMultiplier), compoundingCost,
                baseProduction.multiplyBy(productionMultiplier));
        garden.addGenerator(generator);
        return new GeneratorBuilder(this, generator);
    }

    public GeneratorBuilder createGenerator(
            String name, Amount baseCost, float compoundingCost, Amount baseProduction, int baseChargeTime) {
        Generator generator = new Generator(name, baseCost.multiplyBy(costMultiplier), compoundingCost, baseProduction,
                baseChargeTime / productionMultiplier);
        garden.addGenerator(generator);
        return new GeneratorBuilder(this, generator);
    }

    UpgradeBuilder addGeneratorUpgrade(
            Generator generator, String name, Amount cost, float efficiency, boolean bought) {
        return addGeneratorUpgrade(generator, name, cost, efficiency, 1, bought);
    }

    UpgradeBuilder addGeneratorUpgrade(
            Generator generator, String name, Amount cost, float efficiency, int speed, boolean bought) {
        Upgrade upgrade = new Upgrade(name, cost.multiplyBy(costMultiplier));
        upgrade.addEffect(new UpgradeEffect(generator, efficiency, speed));
        garden.addUpgrade(upgrade);
        state.setUpgradeBought(upgrade, bought);
        return new UpgradeBuilder(this, generator, upgrade);
    }

    void addUnresolvedRequirement(Unlockable unlockable, String generatorName, int count) {
        unresolvedRequirements.computeIfAbsent(generatorName, _ -> new ArrayList<>())
                .add(new UnresolvedGeneratorRequirement(unlockable, generatorName, count));
    }

    void addUnresolvedRequirement(Unlockable unlockable, String upgradeName) {
        unresolvedRequirements.computeIfAbsent(upgradeName, _ -> new ArrayList<>())
                .add(new UnresolvedUpgradeRequirement(unlockable, upgradeName));
    }

    public void resolveRequirements() {
        Map<String, Unlockable> unlockableMap = mapUnlockables();
        for (Map.Entry<String, List<UnresolvedRequirement>> entry : unresolvedRequirements.entrySet()) {
            Class<? extends UnresolvedRequirement> unresolvedRequirementClass = entry.getValue().getFirst().getClass();
            for (UnresolvedRequirement unresolvedRequirement : entry.getValue()) {
                if (!unresolvedRequirement.getClass().equals(unresolvedRequirementClass)) {
                    throw new IllegalStateException(
                            String.format("Expected type %s, actual type %s.", unresolvedRequirementClass.getName(),
                                    unresolvedRequirement.getClass().getName()));
                }
                if (unresolvedRequirement instanceof UnresolvedGeneratorRequirement(Unlockable unlockable,
                        String generatorName, int count)) {
                    Generator generator = (Generator) unlockableMap.get(generatorName);
                    if (generator == null) {
                        throw new IllegalStateException(
                                String.format("No generator \"%s\" exists for \"%s\".", generatorName,
                                        unlockable.getName()));
                    }
                    garden.addRequirement(unlockable, new GeneratorRequirement(generator, count));
                } else if (unresolvedRequirement instanceof UnresolvedUpgradeRequirement(Unlockable unlockable,
                        String upgradeName)) {
                    Upgrade upgrade = (Upgrade) unlockableMap.get(upgradeName);
                    if (upgrade == null) {
                        throw new IllegalStateException(
                                String.format("No upgrade \"%s\" exists for \"%s\".", upgradeName,
                                        unlockable.getName()));
                    }
                    garden.addRequirement(unlockable, new UpgradeRequirement(upgrade));
                } else {
                    throw new IllegalArgumentException(
                            String.format("Unknown type %s of unresolved requirement \"%s\".",
                                    unresolvedRequirement.getClass().getName(),
                                    unresolvedRequirement.unlockable().getName()));
                }
            }
        }
    }

    private @NonNull Map<String, Unlockable> mapUnlockables() {
        Map<String, Unlockable> unlockableMap = new HashMap<>();
        addUnlockable(unlockableMap, garden.getGenerators());
        addUnlockable(unlockableMap, garden.getUpgrades());

        return unlockableMap;
    }

    private <T extends Unlockable> void addUnlockable(Map<String, Unlockable> unlockableMap, List<T> unlockables) {
        for (Unlockable unlockable : unlockables) {
            Unlockable mappedUnlockable = unlockableMap.putIfAbsent(unlockable.getName(), unlockable);
            if (mappedUnlockable != null) {
                throw new IllegalStateException(String.format("unlockableMap already contains %s \"%s\".",
                        mappedUnlockable.getClass().getName(), mappedUnlockable.getName()));
            }
        }
    }
}

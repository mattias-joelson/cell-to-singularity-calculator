package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
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

    public static final float DEFAULT_COMPOUNDING_COST = 1.15f;

    private final Garden garden;
    private final Map<String, List<UnresolvedRequirement>> unresolvedRequirements;
    private final int costMultiplier;
    private final float productionMultiplier;

    public GardenBuilder(Garden garden, int costMultiplier, float productionMultiplier) {
        this.garden = garden;
        this.unresolvedRequirements = new HashMap<>();
        this.costMultiplier = costMultiplier;
        this.productionMultiplier = productionMultiplier;
    }

    public GardenBuilder(Garden garden) {
        this(garden, 1, 1);
    }

    public GeneratorBuilder createGenerator(String name, Amount baseCost, Amount baseProduction) {
        return createGenerator(name, baseCost, DEFAULT_COMPOUNDING_COST, baseProduction);
    }

    public GeneratorBuilder createGenerator(
            String name, Amount baseCost, float compoundingCost, Amount baseProduction) {
        Generator generator = new Generator(name, baseCost.multiplyBy(costMultiplier), compoundingCost,
                baseProduction.multiplyBy(productionMultiplier));
        garden.addGenerator(generator);
        return new GeneratorBuilder(this, generator);
    }

    public GeneratorBuilder createGenerator(String name, Amount baseCost, Amount baseProduction, int baseChargeTime) {
        return createGenerator(name, baseCost, DEFAULT_COMPOUNDING_COST, baseProduction, baseChargeTime);
    }

    public GeneratorBuilder createGenerator(
            String name, Amount baseCost, float compoundingCost, Amount baseProduction, int baseChargeTime) {
        Generator generator = new Generator(name, baseCost.multiplyBy(costMultiplier), compoundingCost, baseProduction,
                baseChargeTime / productionMultiplier);
        garden.addGenerator(generator);
        return new GeneratorBuilder(this, generator);
    }

    UpgradeBuilder addGeneratorUpgrade(
            Generator generator, String name, Amount cost, float efficiency) {
        return addGeneratorUpgrade(generator, name, cost, efficiency, 1);
    }

    UpgradeBuilder addGeneratorUpgrade(
            Generator generator, String name, Amount cost, float efficiency, int speed) {
        Upgrade upgrade = new Upgrade(name, cost.multiplyBy(costMultiplier));
        upgrade.addEffect(new UpgradeEffect(generator, efficiency, speed));
        garden.addUpgrade(upgrade);
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
                    Unlockable possibleGenerator = unlockableMap.get(generatorName);
                    if (possibleGenerator == null) {
                        throw new IllegalStateException(
                                String.format("No unlockable \"%s\" exists for \"%s\".", generatorName,
                                        unlockable.getName()));
                    }
                    if (!(possibleGenerator instanceof Generator generator)) {
                        throw new ClassCastException(String.format("\"%s\": \"%s\" is not a Generator.",
                                unlockable.getName(), generatorName));
                    }
                    garden.addRequirement(unlockable, new GeneratorRequirement(generator, count));
                } else if (unresolvedRequirement instanceof UnresolvedUpgradeRequirement(Unlockable unlockable,
                        String upgradeName)) {
                    Unlockable possibleUpgrade = unlockableMap.get(upgradeName);
                    if (possibleUpgrade == null) {
                        throw new IllegalStateException(
                                String.format("No unlockable \"%s\" exists for \"%s\".", upgradeName,
                                        unlockable.getName()));
                    }
                    if (!(possibleUpgrade instanceof Upgrade upgrade)) {
                        throw new ClassCastException(String.format("\"%s\": \"%s\" is not an Upgrade.",
                                unlockable.getName(), upgradeName));
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

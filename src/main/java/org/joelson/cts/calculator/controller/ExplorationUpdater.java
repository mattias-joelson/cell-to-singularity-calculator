package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Unlockable;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.util.DurationToolkit;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ExplorationUpdater {

    private final Garden garden;
    private final GardenState state;
    private final String gardenGet;
    private final String gardenGeneratorUpdate;
    private final String gardenGeneratorIncrement;
    private final String gardenGeneratorDecrement;
    private final String gardenUpgrade;
    private final boolean timedGenerators;

    ExplorationUpdater(
            Garden garden, GardenState state, String gardenGet, String gardenGeneratorUpdate,
            String gardenGeneratorIncrement, String gardenGeneratorDecrement, String gardenUpgrade) {
        this.garden = garden;
        this.state = state;
        this.gardenGet = gardenGet;
        this.gardenGeneratorUpdate = gardenGeneratorUpdate;
        this.gardenGeneratorIncrement = gardenGeneratorIncrement;
        this.gardenGeneratorDecrement = gardenGeneratorDecrement;
        this.gardenUpgrade = gardenUpgrade;
        this.timedGenerators = hasTimedGenerators(garden);
    }

    private static boolean hasTimedGenerators(Garden garden) {
        for (Generator generator : garden.getGenerators()) {
            if (generator.isTimed()) {
                return true;
            }
        }
        return false;
    }

    public String garden(Model model) {
        return updateModel(model);
    }

    public String gardenGeneratorUpdate(Model model, String target, String value) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            int count = Integer.parseInt(value);
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    public String gardenGeneratorIncrement(Model model, String target) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count() + 1;
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    public String gardenGeneratorDecrement(Model model, String target) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = Math.max(generatorState.count() - 1, 0);
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    private Generator validateGenerator(Model model, String target) {
        if (target == null) {
            model.addAttribute("msg", "Generator name is null.");
        } else {
            String name = target.trim();
            if (name.isEmpty()) {
                model.addAttribute("msg", "Generator name is empty.");
            } else {
                Generator generator = garden.getGenerator(name);
                if (generator == null) {
                    model.addAttribute("msg", "There exists no generator \"" + name + "\".");
                } else {
                    return generator;
                }
            }
        }
        return null;
    }

    public String gardenUpgrade(Model model, String target, String value) {
        if (target == null) {
            model.addAttribute("msg", "Invalid target null.");
        } else {
            String name = target.trim();
            if (name.isEmpty()) {
                model.addAttribute("msg", "Invalid target \"\".");
            } else {
                Upgrade upgrade = garden.getUpgrade(name);
                if (upgrade != null) {
                    boolean bought = value != null && value.equals(upgrade.getName());
                    state.setUpgradeBought(upgrade, bought);
                } else {
                    model.addAttribute("msg", "There exists no upgrade \"" + name + "\".");
                }
            }
        }

        return updateModel(model);
    }

    private String updateModel(Model model) {
        garden.possibleAlterGarden(state);
        state.updateGeneratorStates(garden);

        boolean multiCurrency = garden.getCurrencies().size() > 1;

        model.addAttribute("gardenName", garden.getName());
        model.addAttribute("gardenHasTimedGenerators", timedGenerators);
        model.addAttribute("gardenGet", gardenGet);
        model.addAttribute("gardenGeneratorUpdate", gardenGeneratorUpdate);
        model.addAttribute("gardenGeneratorIncrement", gardenGeneratorIncrement);
        model.addAttribute("gardenGeneratorDecrement", gardenGeneratorDecrement);
        model.addAttribute("gardenUpgrade", gardenUpgrade);
        model.addAttribute("timedGenerators", timedGenerators);
        model.addAttribute("multiCurrency", multiCurrency);

        List<GeneratorProduction> generatorProductions = calculateGeneratorProduction(garden, state);
        model.addAttribute("generatorProductions", generatorProductions);
        List<String> totalProductions = calculateTotalProductions(garden, state);
        model.addAttribute("totalProductions", totalProductions);
        List<GeneratorCost> generatorCosts;
        if (multiCurrency) {
            generatorCosts = calculateGeneratorCosts(garden, state);
        } else {
            generatorCosts = new ArrayList<>();
        }
        model.addAttribute("generatorCosts", generatorCosts);

        List<GeneratorModel> generatorModels = calculateModels(garden, state);
        model.addAttribute("generatorModels", generatorModels);

        List<MappingIncrementsModel> mappingIncrementsModels = calculateMappingIncrements(garden, state);
        model.addAttribute("mappingIncrementsModels", mappingIncrementsModels);

        List<String> actions = new ArrayList<>();
        if (multiCurrency) {
            ImprovementCalculator.multiCurrencyApproach(garden, state.copy(), actions);
        } else {
            ImprovementCalculator.singleCurrencyApproach(garden, state.copy(), actions);
        }
        model.addAttribute("actions", actions.toArray(new String[0]));

        return "exploration";
    }

    public record GeneratorProduction(String name, int count, String next, String each, String totalPerCycle,
            String total, String portion, String increase) {

    }

    public static List<GeneratorProduction> calculateGeneratorProduction(Garden garden, GardenState state) {
        Map<String, Double> totalProduction = ImprovementCalculator.calculateProduction(garden, state);
        List<GeneratorProduction> generatorProductions = new ArrayList<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count();
            String currencyName = generator.getBaseProduction().currency();
            double baseProduction = generator.getBaseProduction().amount();
            float efficiency = generatorState.efficiency();
            String totalPerCycleString;
            double production;
            String productionString;
            GeneratorImprovement improvement = new GeneratorImprovement(generator, generatorState);
            String increaseString = String.format("%.7f", improvement.getRatio());
            if (generator.isTimed()) {
                double productionPerCycle = baseProduction * efficiency * count;
                float speed = generatorState.speed();
                float cycleTime = generator.getBaseChargeTime() / speed;
                if (cycleTime > 60) {
                    totalPerCycleString = String.format("%s in %s",
                            new Amount(currencyName, productionPerCycle).asString(),
                            DurationToolkit.durationString(cycleTime));
                } else {
                    totalPerCycleString = String.format("%s in %.3f s",
                            new Amount(currencyName, productionPerCycle).asString(), cycleTime);
                }
                production = productionPerCycle / cycleTime;
                String productionFormatString = (generatorState.automated()) ? "%s" : "(%s)";
                productionString = String.format(productionFormatString,
                        new Amount(currencyName, production).asString());
            } else {
                totalPerCycleString = "";
                production = baseProduction * efficiency * count;
                productionString = String.format("%s", new Amount(currencyName, production).asString());
            }
            String portionString = String.format("%.2f %%", 100 * production / totalProduction.get(currencyName));
            generatorProductions.add(
                    new GeneratorProduction(generator.getName(), count, generator.getCost(count).asString(),
                            generator.getBaseProduction().multiplyBy(efficiency).asString(),
                            totalPerCycleString, productionString, portionString, increaseString));
        }
        return generatorProductions;
    }

    private List<String> calculateTotalProductions(Garden garden, GardenState state) {
        List<String> productionAmounts = new ArrayList<>();
        Map<String, Double> production = ImprovementCalculator.calculateProduction(garden, state);
        for (String currency : garden.getCurrencies()) {
            if (production.containsKey(currency)) {
                productionAmounts.add(new Amount(currency, production.get(currency)).asString());
            }
        }
        return productionAmounts;
    }

    public record GeneratorCost(String label, String cost, String ratio, String next) {

    }

    private List<GeneratorCost> calculateGeneratorCosts(Garden garden, GardenState state) {

        Map<String, Amount> generatorCostMap = new HashMap<>();
        Map<String, Amount> totalCostMap = new HashMap<>();

        for (Generator generator : garden.getUnlockedGenerators(state)) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            Amount cost = calculateGeneratorCost(generator, generatorState);
            generatorCostMap.put(generator.getName(), cost);
            String currency = generator.getBaseCost().currency();
            Amount total = totalCostMap.get(currency);
            if (total != null) {
                totalCostMap.put(currency, total.plus(cost));
            } else {
                totalCostMap.put(currency, cost);
            }
        }

        List<GeneratorCost> generatorCosts = new ArrayList<>();
        for (Generator generator : garden.getGenerators().reversed()) {
            if (!generatorCostMap.containsKey(generator.getName())) {
                continue;
            }
            Amount cost = generatorCostMap.get(generator.getName());
            Amount total = totalCostMap.get(generator.getBaseCost().currency());
            generatorCosts.add(new GeneratorCost(generator.getName(), cost.asString(),
                    String.format("%.2f %%", 100 * cost.amount() / total.amount()),
                    generator.getCost(state.getGeneratorState(generator).count()).asString()));
        }

        return generatorCosts;
    }

    private Amount calculateGeneratorCost(Generator generator, GeneratorState generatorState) {
        double compoundingCost = generator.getCompoundingCost();
        int count = generatorState.count();
        Amount cost = generator.getBaseCost();
        Amount sumCost = new Amount(cost.currency(), 0);
        for (int i = 0; i < count; i += 1) {
            sumCost = sumCost.plus(cost);
            cost = cost.multiplyBy(compoundingCost);
        }

        return sumCost;
    }

    public record GeneratorModel(String name, int count, String cost, boolean isUnlocked,
            UpgradeModel[] upgradeModels) {

    }

    public record UpgradeModel(String name, String label, float efficiency, boolean isBought, boolean isUnlocked) {

    }

    private List<GeneratorModel> calculateModels(Garden garden, GardenState state) {
        Set<String> unlockedGenerators = unlockedGenerators(garden, state);
        Set<String> unlockedUpgrades = unlockedUpgrades(garden, state);

        List<GeneratorModel> generatorModels = new ArrayList<>();
        List<Generator> generators = garden.getGenerators().reversed();
        List<Upgrade> upgrades = garden.getUpgrades();
        for (Generator generator : generators) {
            List<UpgradeModel> upgradeModels = new ArrayList<>();
            for (Upgrade upgrade : upgrades) {
                boolean bought = state.isUpgradeBought(upgrade);
                boolean buyable = unlockedUpgrades.contains(upgrade.getName()) && !bought;
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    String effectString = createEffectString(generator, effect);
                    if (effect.generator() == generator) {
                        String label;
                        if (bought) {
                            label = String.format("%s: %s", upgrade.getName(), effectString);
                        } else if (buyable) {
                            Improvement improvement = UpgradeImprovement.create(upgrade, state);
                            label = String.format("%s: %s, cost %s, yields %s, increase %.7f",
                                    upgrade.getName(), effectString, improvement.getCost().asString(),
                                    improvement.getIncrease().asString(), improvement.getRatio());
                        } else {
                            label = String.format("%s: %s, cost %s", upgrade.getName(), effectString,
                                    upgrade.getCost().asString());
                        }
                        UpgradeModel upgradeModel = new UpgradeModel(upgrade.getName(), label, effect.efficiency(),
                                state.isUpgradeBought(upgrade), unlockedUpgrades.contains(upgrade.getName()));
                        upgradeModels.add(upgradeModel);
                    }
                }
            }
            int count = state.getGeneratorState(generator).count();
            GeneratorModel generatorModel = new GeneratorModel(generator.getName(), count,
                    generator.getCost(count).asString(), unlockedGenerators.contains(generator.getName()),
                    upgradeModels.toArray(new UpgradeModel[0]));
            generatorModels.add(generatorModel);
        }
        return generatorModels;
    }

    private static String createEffectString(Generator generator, UpgradeEffect effect) {
        if (generator.isTimed()) {
            return createTimedEffectString(effect);
        } else {
            return efficiencyEffectString(effect.efficiency());
        }
    }

    private static String createTimedEffectString(UpgradeEffect effect) {
        StringBuilder effectStringBuilder = new StringBuilder();
        if (effect.efficiency() != 1) {
            effectStringBuilder.append(efficiencyEffectString(effect.efficiency()));
        }
        if (effect.speed() != 1) {
            if (!effectStringBuilder.isEmpty()) {
                effectStringBuilder.append(", ");
            }
            effectStringBuilder.append(speedEffectString(effect.speed()));
        }
        if (effect.automated()) {
            if (!effectStringBuilder.isEmpty()) {
                effectStringBuilder.append(", ");
            }
            effectStringBuilder.append("automated");
        }
        return effectStringBuilder.toString();
    }

    private static String efficiencyEffectString(float efficiency) {
        if (efficiency >= 1_000_000) {
            return String.format("%.2e more efficient", efficiency);
        } else if (efficiency == Math.round(efficiency)) {
            return String.format("%.0f more efficient", efficiency);
        } else {
            return String.format("%.2f more efficient", efficiency);
        }
    }

    private static String speedEffectString(float speed) {
        if (speed > 1) {
            if (speed == Math.round(speed)) {
                return String.format("x%.0f speed", speed);
            } else {
                return String.format("x%.2f speed", speed);
            }
        } else {
            float speedPercent = (1 - speed) * 100;
            if (speedPercent == Math.round(speedPercent)) {
                return String.format("%.0f%% slower", speedPercent);
            } else {
                return String.format("%.2f%% slower", speedPercent);
            }
        }
    }

    private Set<String> unlockedGenerators(Garden garden, GardenState state) {
        return unlockedNames(garden.getUnlockedGenerators(state));
    }

    private Set<String> unlockedUpgrades(Garden garden, GardenState state) {
        return unlockedNames(garden.getUnlockedUpgrades(state));
    }

    private <T extends Unlockable> Set<String> unlockedNames(List<T> unlockables) {
        Set<String> names = new HashSet<>(unlockables.size());
        for (T unlockable : unlockables) {
            names.add(unlockable.getName());
        }
        return names;
    }

    private record MappingIncrementsModel(String mapping, List<IncrementModel> incrementModels) {

    }

    private record IncrementModel(String type, String name, String cost, String yield, String increase, String time) {

    }

    private List<MappingIncrementsModel> calculateMappingIncrements(Garden garden, GardenState state) {

        List<MappingIncrementsModel> mappingIncrementsModels = new ArrayList<>();
        Map<CurrencyMapping, List<Improvement>> mappingImprovements = ImprovementCalculator.availableImprovements(
                garden, state);
        Map<String, Double> totalProduction = ImprovementCalculator.calculateProduction(garden, state);
        for (String fromCurrency : garden.getCurrencies()) {
            for (String toCurrency : garden.getCurrencies()) {
                CurrencyMapping mapping = new CurrencyMapping(fromCurrency, toCurrency);
                if (!mappingImprovements.containsKey(mapping)) {
                    continue;
                }
                List<Improvement> improvements = mappingImprovements.get(mapping);
                List<IncrementModel> incrementModels = new ArrayList<>();
                improvements.sort(Comparator.comparing(Improvement::getRatio));
                for (Improvement improvement : improvements.reversed()) {
                    Amount cost = improvement.getCost();
                    Double totProd = totalProduction.get(cost.currency());
                    String timeString;
                    if (totProd == null) {
                        timeString = "Inf";
                    } else {
                        double time = cost.amount() / totProd;
                        timeString = DurationToolkit.durationString(time);
                    }
                    String type = (improvement instanceof GeneratorImprovement) ? "(G)" : "(U)";
                    IncrementModel incrementModel = new IncrementModel(type, improvement.getName(),
                            cost.asString(), improvement.getIncrease().asString(),
                            String.format("%.7f", improvement.getRatio()), timeString);
                    incrementModels.add(incrementModel);
                }
                mappingIncrementsModels.add(
                        new MappingIncrementsModel(String.format("From %s to %s", mapping.from(), mapping.to()),
                                incrementModels));
            }
        }

        return mappingIncrementsModels;
    }
}

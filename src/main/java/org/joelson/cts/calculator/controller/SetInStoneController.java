package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.SetInStone;
import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Unlockable;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.util.DurationToolkit;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class SetInStoneController {

    private final Garden garden = SetInStone.createGarden(1, 1);
    private final GardenState state = new GardenState();

    private void initState() {
        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Gem", 0);
        setGeneratorCount("Crystal", 0);
        setGeneratorCount("Metamorphic Rock", 0);
        setGeneratorCount("Sedimentary Rock", 0);
        setGeneratorCount("Igneous Rock", 0);
        setGeneratorCount("Mineral", 1);

        String[] boughtUpdates = {
//                "Olivine",
//                "Quartz",
//                "Feldspars",
//                "Magma",
//                "Tuff", // rocks
//                "Clay",
//                "Rock Cycle",
//                "Sand",
//                "Sandstone", // rocks
//                "Calcite",
//                "Granite", // rocks
//                "Gneiss", // rocks
//                "Fluorite",
//                "Pegmatite", // rocks
//                "Coal", // rocks

//                "Dunite",
//                "Basalt",
//                "Andesite",
//                "Diorite",
//                "Scoria",
//                "Obsidian",

//                "Limestone",
//                "Siltstone",
//                "Coquina",
//                "Shale",
//                "Chalk",
//                "Flint",
//                "Natural Beauty",

//                "Marble",
//                "Slate",
//                "Schist",
//                "Anthracite",
//                "Jade", // crystal

//                "Amethyst",
//                "Jasper",
//                "Fulgurite", // rocks
//                "Topaz",
//                "Lapis Lazuli", // rocks
//                "Diamond",
//                "Pyrite",
//                "Lab-Grown Diamonds",

//                "Emerald",
//                "Aquamarine",
//                "Opal",
//                "Garnet",
//                "Sapphire",
//                "Ruby",
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping("/setinstone")
    public String setInStone(Model model) {
        initState();
        return updateModel(model);
    }

    @PostMapping("/setinstone-generator-update")
    public String setInStoneGeneratorUpdate(Model model, String target, String value) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            int count = Integer.parseInt(value);
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/setinstone-generator-increment")
    public String setInStoneGeneratorIncrement(Model model, String target) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count() + 1;
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/setinstone-generator-decrement")
    public String setInStoneGeneratorDecrement(Model model, String target) {
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

    @PostMapping("/setinstone-upgrade")
    public String updateSetInStone(Model model, String target, String value) {
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

    private @NonNull String updateModel(Model model) {
        state.updateGeneratorStates(garden);
        GardenState initialState = state.copy();

        model.addAttribute("garden", garden);
        model.addAttribute("state", state);
        List<GeneratorProduction> generatorProductions = calculateGeneratorProduction(garden, state);
        model.addAttribute("generatorProductions", generatorProductions);
        List<String> totalProductions = calculateTotalProductions(garden, state);
        model.addAttribute("totalProductions", totalProductions);
        List<GeneratorCost> generatorCosts = calculateGeneratorCosts(garden, state);
        model.addAttribute("generatorCosts", generatorCosts);

        List<GeneratorModel> generatorModels = calculateModels(garden, state);
        model.addAttribute("generatorModels", generatorModels);

        List<String> actions = new ArrayList<>();
        SetInStone.candidateApproach(garden, state.copy(), actions);
        model.addAttribute("actions", actions.toArray(new String[0]));

        return "setinstone";
    }

    public record GeneratorProduction(String name, int count, String next, String each, String total, String increase) {

    }

    public static List<GeneratorProduction> calculateGeneratorProduction(Garden garden, GardenState state) {
        List<GeneratorProduction> generatorProductions = new ArrayList<>();
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count();
            String currencyName = generator.getBaseProduction().currency();
            double baseProduction = generator.getBaseProduction().amount();
            float efficiency = generatorState.efficiency();
            if (generator.isTimed()) {
                double productionPerCycle = baseProduction * efficiency * count;
                float speed = generatorState.speed();
                float cycleTime = generator.getBaseChargeTime() / speed;
                double production = productionPerCycle / cycleTime;
                String productionString = String.format("%s in %s s, %s per second",
                        new Amount(currencyName, productionPerCycle).asString(),
                        DurationToolkit.durationString(cycleTime),
                        new Amount(currencyName, production).asString());
                generatorProductions.add(new GeneratorProduction(generator.getName(), count,
                        generator.getCost(count).asString(),
                        generator.getBaseProduction().multiplyBy(efficiency).asString(), productionString,
                        String.format("%.7f", production / (count * generator.getCost(count).amount()))));
            } else {
                double production = baseProduction * efficiency * count;
                String productionString = String.format("%s", new Amount(currencyName, production).asString());
                generatorProductions.add(new GeneratorProduction(generator.getName(), count,
                        generator.getCost(count).asString(),
                        generator.getBaseProduction().multiplyBy(efficiency).asString(), productionString,
                        String.format("%.7f", production / (count * generator.getCost(count).amount()))));
            }
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

    private static List<GeneratorCost> calculateGeneratorCosts(Garden garden, GardenState state) {
        Generator crystalGenerator = garden.getGenerator("Crystal");
        GeneratorState crystalGeneratorState = state.getGeneratorState(crystalGenerator);
        Amount crystalCost = calculateGeneratorCost(crystalGenerator, crystalGeneratorState);

        Generator metamorphicGenerator = garden.getGenerator("Metamorphic Rock");
        GeneratorState metamorphicGeneratorState = state.getGeneratorState(metamorphicGenerator);
        Amount metamorphicCost = calculateGeneratorCost(metamorphicGenerator, metamorphicGeneratorState);
        Amount nextMetamorphic = metamorphicGenerator.getCost(metamorphicGeneratorState.count());
        Generator sedimentaryGenerator = garden.getGenerator("Sedimentary Rock");
        GeneratorState sedimentaryGeneratorState = state.getGeneratorState(sedimentaryGenerator);
        Amount sedimentaryCost = calculateGeneratorCost(sedimentaryGenerator, sedimentaryGeneratorState);
        Amount nextSedimentary = sedimentaryGenerator.getCost(sedimentaryGeneratorState.count());
        Generator igneousGenerator = garden.getGenerator("Igneous Rock");
        GeneratorState igneousGeneratorState = state.getGeneratorState(igneousGenerator);
        Amount nextIgneous = igneousGenerator.getCost(igneousGeneratorState.count());
        Amount igneousCost = calculateGeneratorCost(igneousGenerator, igneousGeneratorState);
        Amount nextRock = new Amount(nextIgneous.currency(),
                nextIgneous.amount() + nextSedimentary.amount() + nextMetamorphic.amount());

        double rockCost = metamorphicCost.amount() + sedimentaryCost.amount() + igneousCost.amount();

        Generator mineralGenerator = garden.getGenerator("Mineral");
        GeneratorState mineralGeneratorState = state.getGeneratorState(mineralGenerator);
        Amount mineralCost = calculateGeneratorCost(mineralGenerator, mineralGeneratorState);

        double totalCost = crystalCost.amount() + rockCost + mineralCost.amount();

        List<GeneratorCost> generatorCosts = new ArrayList<>();
        generatorCosts.add(createGenaratorCost(crystalGenerator, crystalGeneratorState, crystalCost, totalCost));
        generatorCosts.add(
                createGenaratorCost(metamorphicGenerator, metamorphicGeneratorState, metamorphicCost, totalCost));
        generatorCosts.add(
                createGenaratorCost(sedimentaryGenerator, sedimentaryGeneratorState, sedimentaryCost, totalCost));
        generatorCosts.add(createGenaratorCost(igneousGenerator, igneousGeneratorState, igneousCost, totalCost));
        generatorCosts.add(new GeneratorCost("sum rocks", new Amount(metamorphicCost.currency(), rockCost).asString(),
                String.format("%.3f %%", rockCost / totalCost), nextRock.asString()));
        generatorCosts.add(createGenaratorCost(mineralGenerator, mineralGeneratorState, mineralCost, totalCost));
        return generatorCosts;
    }

    private static Amount calculateGeneratorCost(Generator generator, GeneratorState generatorState) {
        double sum = 0;
        for (int lvl = 0; lvl < generatorState.count(); lvl += 1) {
            sum += generator.getCost(lvl).amount();
        }
        return new Amount(generator.getBaseCost().currency(), sum);
    }

    private static GeneratorCost createGenaratorCost(
            Generator generator, GeneratorState generatorState, Amount cost, double totalCost) {
        return new GeneratorCost(String.format("Crystal (%d)", generatorState.count()), cost.asString(),
                String.format("%.3f %%", cost.amount() / totalCost),
                generator.getCost(generatorState.count()).asString());
    }

    public record GeneratorModel(String name, int count, String cost, boolean isUnlocked,
            UpgradeModel[] upgradeModels) {

    }

    public record UpgradeModel(String name, float efficiency, String cost, boolean isBought, boolean isUnlocked) {

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
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    if (effect.generator() == generator) {
                        UpgradeModel upgradeModel = new UpgradeModel(upgrade.getName(), effect.efficiency(),
                                upgrade.getCost().asString(), state.isUpgradeBought(upgrade),
                                unlockedUpgrades.contains(upgrade.getName()));
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
}

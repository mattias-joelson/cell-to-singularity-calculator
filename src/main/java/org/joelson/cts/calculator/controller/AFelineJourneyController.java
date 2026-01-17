package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.AFelineJourney;
import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Unlockable;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
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
public class AFelineJourneyController {

    private final Garden garden = AFelineJourney.createGarden(1, 1, 0);
    private final GardenState state = new GardenState();

    public Garden getGarden() {
        initState();
        return garden;
    }

    public GardenState getState() {
        return state;
    }

    private void initState() {
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("House Cats", 0);
        setGeneratorCount("Felis", 0);
        setGeneratorCount("Leopard Cat", 0);
        setGeneratorCount("Puma", 0);
        setGeneratorCount("Lynx", 0);
        setGeneratorCount("Ocelot", 0);
        setGeneratorCount("Caracal", 0);
        setGeneratorCount("Bay Cat", 0);
        setGeneratorCount("Panthera", 0);
        setGeneratorCount("Felidae", 1);

        String[] boughtUpdates = {
//                "Pantherinae",
//                "Felinae",
//                "Digitigrade",
//                "Claws",
//                "Acute Senses",
//                "Flexibility",
//                "Patterned Coats",
//                "Rough Tongue",
//                "Short Skull",
//                "Obligate Carnivores",
//                "Whiskers",

//                "Clouded Leopard",
//                "Tiger",
//                "Leopard",
//                "Snow Leopard",
//                "Sunda Clouded Leopard",
//                "Lion",
//                "Jaguar",

//                "Bornean Bay Cat",
//                "Asian Golden Cat",
//                "Marbled Cat",

//                "African Caracal",
//                "Serval",

//                "Ocelots",
//                "Kodkod",
//                "Andean Mountain Cat",
//                "Geoffroy's Cat",
//                "Margay",

//                "Eurasian Lynx",
//                "Iberian Lynx",
//                "Bobcat",

//                "Cougar",
//                "Cheetah",

//                "Leopard Cats",
//                "Sunda Leopard Cat",
//                "Fishing Cat",
//                "Flat-Headed Cat",
//                "Rusty-Spotted Cat",
//                "Pallas' Cat",

//                "Jungle Cat",
//                "Black-Footed Cat",
//                "Sand Cat",
//                "African Wildcat",

//                "Fluffy",
//                "Australian Cats",
//                "Hairless",
//                "Orange",
//                "Hunter at Heart",
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

    @GetMapping("/felinejourney")
    public String felineJourney(Model model) {
        initState();
        return updateModel(model);
    }

    @PostMapping("/felinejourney-generator-update")
    public String felineJourneyGeneratorUpdate(Model model, String target, String value) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            int count = Integer.parseInt(value);
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/felinejourney-generator-increment")
    public String felineJourneyGeneratorIncrement(Model model, String target) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count() + 1;
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/felinejourney-generator-decrement")
    public String felineJourneyGeneratorDecrement(Model model, String target) {
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

    @PostMapping("/felinejourney-upgrade")
    public String felineJourneyUpgrade(Model model, String target, String value) {
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

        model.addAttribute("garden", garden);
        model.addAttribute("state", state);
        List<GeneratorProduction> generatorProductions = calculateGeneratorProduction(garden, state);
        model.addAttribute("generatorProductions", generatorProductions);
        List<String> totalProductions = calculateTotalProductions(garden, state);
        model.addAttribute("totalProductions", totalProductions);

        List<GeneratorModel> generatorModels = calculateModels(garden, state);
        model.addAttribute("generatorModels", generatorModels);

        List<String> actions = new ArrayList<>();
        AFelineJourney.singleCurrencyApproach(garden, state.copy(), actions);
        model.addAttribute("actions", actions.toArray(new String[0]));

        return "felinejourney";
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
                        String.format("%.7f", (baseProduction * efficiency)
                                / (cycleTime * generator.getCost(count).amount()))));
            } else {
                double production = baseProduction * efficiency * count;
                String productionString = String.format("%s", new Amount(currencyName, production).asString());
                generatorProductions.add(new GeneratorProduction(generator.getName(), count,
                        generator.getCost(count).asString(),
                        generator.getBaseProduction().multiplyBy(efficiency).asString(), productionString,
                        String.format("%.7f", (baseProduction * efficiency) / generator.getCost(count).amount())));
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
                boolean buyable = unlockedUpgrades.contains(upgrade.getName()) && !state.isUpgradeBought(upgrade);
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    if (effect.generator() == generator) {
                        String label;
                        if (buyable) {
                            Improvement improvement = UpgradeImprovement.create(upgrade, state);
                            label = String.format("%s: %.2f more efficient, cost %s, yields +%s, increase %.7f",
                                    upgrade.getName(), effect.efficiency(), improvement.getCost().asString(),
                                    improvement.getIncrease().asString(), improvement.getRatio());
                        } else {
                            label = upgrade.getName();
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

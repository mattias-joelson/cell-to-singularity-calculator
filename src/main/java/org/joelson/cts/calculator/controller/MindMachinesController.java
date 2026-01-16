package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.MindMachines;
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
public class MindMachinesController {

    private final Garden garden = MindMachines.createGarden(1, 1);
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

        setGeneratorCount("Artificial Intelligence", 1);
        setGeneratorCount("Evils and Perils", 0);
        setGeneratorCount("AI Tools", 0);
        setGeneratorCount("Generative AI", 0);
        setGeneratorCount("Analytic AI", 0);
        setGeneratorCount("Mechanical Brain", 1);

        String[] boughtUpdates = {
//                "Future AI",
//                "Quantum Computing",
//                "Biocomputers",
//                "Uploaded Mind",
//                "Philosophical Zombies",
//                "Morality AI",
//                "Sentience",
//                "Artificial General Intelligence",

//                "Analytic Engine",
//                "Binary Code",
//                "Logic Switches",
//                "Input, Output",
//                "Algorithms",
//                "Learning Loops",
//                "Love Letters",
//                "Imitation Game",
//                "ELIZA Therapy Bot",
//                "Stochastic Parrot",

//                "Turochamp",
//                "Logical Thinking",
//                "Data Processing",
//                "Brute Force",
//                "Heuristics",
//                "Fuzzy Logic",
//                "Games, Mastered",
//                "Expert Systems",
//                "Context",
//                "Black Box",

//                "Electric Brain",
//                "Neural Network",
//                "Machine Learning",
//                "Training Sets",
//                "Big Data",
//                "Large Language Models",
//                "GPTs",
//                "Deep Learning",
//                "Natural Language",
//                "Hallucinations",
//                "AI Evolved",

//                "Personal Assistants",
//                "Customer Service",
//                "Therapists",
//                "Translators and Interpreters",
//                "AI Avatars",
//                "Self-Driving Vehicles",
//                "AI Drones and Carts",
//                "Space Explorers",
//                "Statistical Forecasting",
//                "Smarter Systems",
//                "Scientific Discoveries",
//                "Mind Reading",

//                "Bad Actors",
//                "Gullibility",
//                "Human Bias",
//                "Resistance",
//                "Weak Laws",
//                "AI Workforce",
//                "Cyberattacks and Warfare",
//                "Machine Unlearning",
//                "Red Teaming",
//                "Tough Decisions",
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

    @GetMapping("/mindmachines")
    public String mindMachines(Model model) {
        initState();
        return updateModel(model);
    }

    @PostMapping("/mindmachines-generator-update")
    public String mindMachinesGeneratorUpdate(Model model, String target, String value) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            int count = Integer.parseInt(value);
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/mindmachines-generator-increment")
    public String mindMachinesGeneratorIncrement(Model model, String target) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count() + 1;
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/mindmachines-generator-decrement")
    public String mindMachinesGeneratorDecrement(Model model, String target) {
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

    @PostMapping("/mindmachines-upgrade")
    public String mindMachinesUpgrade(Model model, String target, String value) {
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
        MindMachines.singleCurrencyApproach(garden, state.copy(), actions);
        model.addAttribute("actions", actions.toArray(new String[0]));

        return "mindmachines";
    }

    public record GeneratorProduction(String name, int count, String next, String each, String totalPerCycle,
            String total, String increase) {

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
                String productionPerCycleString;
                if (cycleTime > 60) {
                    productionPerCycleString = String.format("%s in %s s",
                            new Amount(currencyName, productionPerCycle).asString(),
                            DurationToolkit.durationString(cycleTime));
                } else {
                    productionPerCycleString = String.format("%s in %.3f s",
                            new Amount(currencyName, productionPerCycle).asString(), cycleTime);
                }
                double production = productionPerCycle / cycleTime;
                String productionString = String.format("%s", new Amount(currencyName, production).asString());
                String increaseString;
                if (generatorState.automated()) {
                    increaseString = String.format("%.7f",(baseProduction * efficiency) / (cycleTime * generator.getCost(count).amount()));
                } else {
                    productionString = String.format("(%s)", productionString);
                    increaseString = "0.0000000";
                }
                generatorProductions.add(
                        new GeneratorProduction(generator.getName(), count, generator.getCost(count).asString(),
                                generator.getBaseProduction().multiplyBy(efficiency).asString(),
                                productionPerCycleString, productionString, increaseString));
            } else {
                double production = baseProduction * efficiency * count;
                String productionString = String.format("%s", new Amount(currencyName, production).asString());
                String increaseString = String.format("%.7f",
                        (baseProduction * efficiency) / generator.getCost(count).amount());
                generatorProductions.add(
                        new GeneratorProduction(generator.getName(), count, generator.getCost(count).asString(),
                                generator.getBaseProduction().multiplyBy(efficiency).asString(),
                                "", productionString, increaseString));
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

    private static @NonNull String createEffectString(Generator generator, UpgradeEffect effect) {
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
        if (efficiency == Math.round(efficiency)) {
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
}

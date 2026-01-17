package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.BeautifulComplexity;
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
public class BeautifulComplexityController {

    private final Garden garden = BeautifulComplexity.createGarden(1, 1, 0);
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

        setGeneratorCount("Discrete Math", 0);
        setGeneratorCount("Calculus", 0);
        setGeneratorCount("Marvels and Mysteries", 0);
        setGeneratorCount("Geometry", 0);
        setGeneratorCount("Applied Math", 0);
        setGeneratorCount("Algebra", 0);
        setGeneratorCount("Arithmetic", 0);
        setGeneratorCount("Building Blocks", 1);

        String[] boughtUpdates = {
//                "Zero",
//                "Negatives",
//                "Integers",
//                "Rationals",
//                "Proofs",
//                "Complex Numbers",
//                "Irrationals",
//                "Proof by Contradiction", // imaginary
//                "Proof by Induction",
//                "The Most Beautiful Equation", // imaginary

//                "Addition",
//                "Fibonacci Sequence",
//                "Multiplication",
//                "Factorials",
//                "Exponentiation",
//                "Tetration",

//                "Equations",
//                "Variables",
//                "Quadratic Formula",
//                "Preserving Equality",
//                "Proof that 0.999...=1",
//                "Pascal's Triangle",
//                "i",
//                "Linear Algebra", // imaginary

//                "Compounding Interest",
//                "Math in Cells",
//                "Voronoi Pattern",
//                "Predator-Prey Model",
//                "Integrals",
//                "Statistics",
//                "Normal Distribution",
//                "Cryptography",
//                "Arrow's Impossibility",

//                "Area",
//                "Pythagorean Theorem",
//                "Trigonometry",
//                "Trigonometric Functions",
//                "Fractals",
//                "Pi",
//                "Non-Euclidean Geometry",
//                "Mobius Strip",

//                "Four-Color Theorem",
//                "Irrationalᴵʳʳᵃᵗᶦᵒⁿᵃˡ",
//                "Fermat's Last Theorem",
//                "Birthday Paradox",
//                "Twin Prime Conjecture", // imaginary
//                "Goldbach's Conjecture",
//                "Gödel's Incompleteness", // imaginary

//                "Limits",
//                "Derivatives",
//                "e",
//                "Infinity", // real
//                "Countable Infinity",
//                "Uncountable Infinity", // real
//                "Complex Plane",

//                "Number Theory", // real
//                "Primes",
//                "Probability", // real
//                "Game Theory",
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

    @GetMapping("/beautifulcomplexity")
    public String setInStone(Model model) {
        initState();
        return updateModel(model);
    }

    @PostMapping("/beautifulcomplexity-generator-update")
    public String setInStoneGeneratorUpdate(Model model, String target, String value) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            int count = Integer.parseInt(value);
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/beautifulcomplexity-generator-increment")
    public String setInStoneGeneratorIncrement(Model model, String target) {
        Generator generator = validateGenerator(model, target);
        if (generator != null) {
            GeneratorState generatorState = state.getGeneratorState(generator);
            int count = generatorState.count() + 1;
            state.setGeneratorCount(generator, count);
        }

        return updateModel(model);
    }

    @PostMapping("/beautifulcomplexity-generator-decrement")
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

    @PostMapping("/beautifulcomplexity-upgrade")
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
        BeautifulComplexity.candidateApproach(garden, state.copy(), actions);
        model.addAttribute("actions", actions.toArray(new String[0]));

        return "beautifulcomplexity";
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
        Amount buildingCost = calculateGeneratorCost(garden, state, "Building Blocks");
        Amount arithmeticCost = calculateGeneratorCost(garden, state, "Arithmetic");
        Amount algebraCost = calculateGeneratorCost(garden, state, "Algebra");
        Amount appliedCost = calculateGeneratorCost(garden, state, "Applied Math");
        Amount geometryCost = calculateGeneratorCost(garden, state, "Geometry");

        Amount realCost = buildingCost.plus(arithmeticCost).plus(algebraCost).plus(appliedCost).plus(geometryCost);

        Amount marvelsCost = calculateGeneratorCost(garden, state, "Marvels and Mysteries");

        Amount totalRealCost = realCost.plus(marvelsCost);

        Amount calculusCost = calculateGeneratorCost(garden, state, "Calculus");
        Amount discreteCost = calculateGeneratorCost(garden, state, "Discrete Math");
        Amount totalImaginaryCost = calculusCost.plus(discreteCost);

        List<GeneratorCost> generatorCosts = new ArrayList<>();
        generatorCosts.add(createGeneratorCost(garden, state, "Discrete Math", discreteCost, totalImaginaryCost));
        generatorCosts.add(createGeneratorCost(garden, state, "Calculus", calculusCost, totalImaginaryCost));
        generatorCosts.add(new GeneratorCost("", "", "", ""));
        generatorCosts.add(createGeneratorCost(garden, state, "Marvels and Mysteries", marvelsCost, totalRealCost));
        generatorCosts.add(new GeneratorCost("sum real", realCost.asString(),
                String.format("%.3f %%", 100 * realCost.amount() / totalRealCost.amount()), "<needed?>"));
        generatorCosts.add(createGeneratorCost(garden, state, "Geometry", geometryCost, totalRealCost));
        generatorCosts.add(createGeneratorCost(garden, state, "Applied Math", appliedCost, totalRealCost));
        generatorCosts.add(createGeneratorCost(garden, state, "Algebra", algebraCost, totalRealCost));
        generatorCosts.add(createGeneratorCost(garden, state, "Arithmetic", arithmeticCost, totalRealCost));
        generatorCosts.add(createGeneratorCost(garden, state, "Building Blocks", buildingCost, totalRealCost));

        return generatorCosts;
    }

    private static Amount calculateGeneratorCost(Garden garden, GardenState state, String generatorName) {
        Generator generator = garden.getGenerator(generatorName);
        GeneratorState generatorState = state.getGeneratorState(generator);
        return calculateGeneratorCost(generator, generatorState);
    }

    private static Amount calculateGeneratorCost(Generator generator, GeneratorState generatorState) {
        double sum = 0;
        for (int lvl = 0; lvl < generatorState.count(); lvl += 1) {
            sum += generator.getCost(lvl).amount();
        }
        return new Amount(generator.getBaseCost().currency(), sum);
    }

    private static GeneratorCost createGeneratorCost(
            Garden garden, GardenState state, String generatorName, Amount cost, Amount totalCost) {
        Generator generator = garden.getGenerator(generatorName);
        return createGeneratorCost(generator, state.getGeneratorState(generator), cost, totalCost);
    }

    private static GeneratorCost createGeneratorCost(
            Generator generator, GeneratorState generatorState, Amount cost, Amount totalCost) {
        return new GeneratorCost(String.format("%s by %s (%d)", generator.getBaseCost().currency(), generator.getName(), generatorState.count()),
                cost.asString(), String.format("%.3f %%", 100 * cost.amount() / totalCost.amount()),
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

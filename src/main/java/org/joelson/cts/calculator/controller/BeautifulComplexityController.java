package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.BeautifulComplexity;
import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BeautifulComplexityController {

    private static final String GARDEN_GET = "/beautifulcomplexity";
    private static final String GARDEN_GENERATOR_UPDATE = "/beautifulcomplexity-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/beautifulcomplexity-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/beautifulcomplexity-generator-decrement";
    private static final String GARDEN_UPGRADE = "/beautifulcomplexity-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public BeautifulComplexityController() {
        garden = BeautifulComplexity.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
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

    @GetMapping(GARDEN_GET)
    public String setInStone(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String setInStoneGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String setInStoneGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String setInStoneGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String setInStoneUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
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
        return new GeneratorCost(String.format("%s by %s (%d)", generator.getBaseCost().currency(), generator.getName(),
                generatorState.count()),
                cost.asString(), String.format("%.3f %%", 100 * cost.amount() / totalCost.amount()),
                generator.getCost(generatorState.count()).asString());
    }
}

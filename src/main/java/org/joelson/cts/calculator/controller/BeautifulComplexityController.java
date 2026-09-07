package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.BeautifulComplexity;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

}

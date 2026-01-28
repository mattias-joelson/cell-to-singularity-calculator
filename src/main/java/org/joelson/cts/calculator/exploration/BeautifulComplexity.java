package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class BeautifulComplexity {

    private static final String REAL_CURRENCY = "Real Numbers";
    private static final String IMAGINARY_CURRENCY = "Imaginary Numbers";

    private static final String[] CURRENCIES = { REAL_CURRENCY, IMAGINARY_CURRENCY };

    private static Amount real(double amount) {
        return new Amount(REAL_CURRENCY, amount);
    }

    private static Amount imaginary(double amount) {
        return new Amount(IMAGINARY_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Beautiful Complexity");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Building Blocks", real(200), real(1))
//                .addUpgradeRequirement("Numbers")

                .addEfficiencyUpgrade("Zero", real(750), 2)
                .addGeneratorRequirement("Building Blocks")

                .addEfficiencyUpgrade("Negatives", real(500_000), 81)
                .addUpgradeRequirement("Addition")

                .addEfficiencyUpgrade("Integers", real(5e6), 3)
//                .addUpgradeRequirement("Numbers")
                .addUpgradeRequirement("Negatives")

                .addEfficiencyUpgrade("Rationals", real(6e7), 3)
                .addUpgradeRequirement("Integers")

                .addEfficiencyUpgrade("Proofs", real(2e12), 5001)
                .addGeneratorRequirement("Building Blocks")
                .addUpgradeRequirement("Quadratic Formula")

                .addEfficiencyUpgrade("Complex Numbers", real(1e17), 10_001)
                .addUpgradeRequirement("i")

                .addEfficiencyUpgrade("Irrationals", real(8e17), 6)
                .addUpgradeRequirement("Rationals")
                .addUpgradeRequirement("Complex Numbers")

                .addEfficiencyUpgrade("Proof by Contradiction", imaginary(2e12), 2_500_001)
                .addUpgradeRequirement("Primes")

                .addEfficiencyUpgrade("Proof by Induction", real(8e26), 501)
                .addGeneratorRequirement("Discrete Math")
                .addUpgradeRequirement("Linear Algebra")

                .addEfficiencyUpgrade("The Most Beautiful Equation", imaginary(1e19), 2)
                .addUpgradeRequirement("Complex Plane");

        builder.createGenerator("Arithmetic", real(1_500), real(5))
                .addGeneratorRequirement("Building Blocks")

                .addEfficiencyUpgrade("Addition", real(8_000), 5)
                .addGeneratorRequirement("Arithmetic")

                .addEfficiencyUpgrade("Fibonacci Sequence", real(40_000), 2.12358f)
                .addUpgradeRequirement("Addition")

                .addEfficiencyUpgrade("Multiplication", real(1.5e7), 21)
                .addUpgradeRequirement("Addition")

                .addEfficiencyUpgrade("Factorials", real(1.5e8), 2)
                .addUpgradeRequirement("Multiplication")

                .addEfficiencyUpgrade("Exponentiation", real(8e9), 26)
                .addUpgradeRequirement("Multiplication")
                .addUpgradeRequirement("Equations")

                .addEfficiencyUpgrade("Tetration", real(5e29), 4e19f)
                .addUpgradeRequirement("Exponentiation")
                .addUpgradeRequirement("Game Theory");

        builder.createGenerator("Algebra", real(1e8), real(20_000))
                .addUpgradeRequirement("Multiplication")

                .addEfficiencyUpgrade("Equations", real(2e9), 4)
                .addGeneratorRequirement("Algebra")

                .addEfficiencyUpgrade("Variables", real(3e9), 7)
                .addUpgradeRequirement("Equations")

                .addEfficiencyUpgrade("Quadratic Formula", real(2e11), 5)
                .addUpgradeRequirement("Equations")
                .addUpgradeRequirement("Exponentiation")

                .addEfficiencyUpgrade("Preserving Equality", real(6e12), 11)
                .addUpgradeRequirement("Equations")
                .addUpgradeRequirement("Proofs")

                .addEfficiencyUpgrade("Proof that 0.999...=1", real(1e13), 1.999f)
                .addUpgradeRequirement("Preserving Equality")

                .addEfficiencyUpgrade("Pascal's Triangle", real(3e13), 2)
                .addGeneratorRequirement("Algebra")
                .addUpgradeRequirement("Preserving Equality")

                .addEfficiencyUpgrade("i", real(2e16), 501)
                .addUpgradeRequirement("Rationals")
                .addUpgradeRequirement("Trigonometric Functions")

                .addEfficiencyUpgrade("Linear Algebra", imaginary(1e15), 3e8f)
                .addUpgradeRequirement("Equations")
                .addGeneratorRequirement("Discrete Math")
                .addUpgradeRequirement("Infinity");

        builder.createGenerator("Applied Math", real(5e9), real(2e6))
                .addUpgradeRequirement("Exponentiation")

                .addEfficiencyUpgrade("Compounding Interest", real(2.5e10), 2)
                .addUpgradeRequirement("Equations")

                .addEfficiencyUpgrade("Math in Cells", real(8e11), 4)
                .addUpgradeRequirement("Equations")
                .addUpgradeRequirement("Exponentiation")

                .addEfficiencyUpgrade("Voronoi Pattern", real(1.5e17), 75_001)
                .addUpgradeRequirement("Four-Color Theorem")

                .addEfficiencyUpgrade("Predator-Prey Model", real(5e19), 101)
                .addUpgradeRequirement("Derivatives")

                .addEfficiencyUpgrade("Integrals", real(1e20), 3.5f)
                .addUpgradeRequirement("Derivatives")

                .addEfficiencyUpgrade("Statistics", real(5e20), 2)
                .addGeneratorRequirement("Applied Math")
                .addUpgradeRequirement("Compounding Interest")
                .addUpgradeRequirement("Predator-Prey Model")

                .addEfficiencyUpgrade("Normal Distribution", real(1e21), 2)
                .addUpgradeRequirement("Statistics")

                .addEfficiencyUpgrade("Cryptography", real(5e22), 201)
                .addUpgradeRequirement("Primes")

                .addEfficiencyUpgrade("Arrow's Impossibility", real(1e29), 500_001)
                .addGeneratorRequirement("Discrete Math")
                .addUpgradeRequirement("Proof by Induction");

        builder.createGenerator("Geometry", real(5e12), real(2e9))
                .addUpgradeRequirement("Pascal's Triangle")

                .addEfficiencyUpgrade("Area", real(1e14), 2)
                .addGeneratorRequirement("Geometry")

                .addEfficiencyUpgrade("Pythagorean Theorem", real(2.5e14), 4.45f)
                .addGeneratorRequirement("Geometry")

                .addEfficiencyUpgrade("Trigonometry", real(7.5e14), 2)
                .addGeneratorRequirement("Geometry")

                .addEfficiencyUpgrade("Trigonometric Functions", real(2e15), 2)
                .addUpgradeRequirement("Trigonometry")

                .addEfficiencyUpgrade("Fractals", real(7e15), 2)
                .addUpgradeRequirement("Pascal's Triangle")
                .addGeneratorRequirement("Geometry")
                .addUpgradeRequirement("Trigonometry")

                .addEfficiencyUpgrade("Pi", real(2e18), 315.159f)
                .addGeneratorRequirement("Geometry")
                .addUpgradeRequirement("Irrationals")

                .addEfficiencyUpgrade("Non-Euclidean Geometry", real(8e25), 2_000_001)
                .addGeneratorRequirement("Geometry")
                .addUpgradeRequirement("Countable Infinity")

                .addEfficiencyUpgrade("Mobius Strip", real(2.5e26), 5)
                .addGeneratorRequirement("Geometry")
                .addUpgradeRequirement("Linear Algebra");

        builder.createGenerator("Marvels and Mysteries", real(1e16), imaginary(1))
                .addUpgradeRequirement("Complex Numbers")

                .addEfficiencyUpgrade("Four-Color Theorem", imaginary(1_000), 5)
                .addGeneratorRequirement("Geometry")
                .addGeneratorRequirement("Marvels and Mysteries")

                .addEfficiencyUpgrade("Irrationalᴵʳʳᵃᵗᶦᵒⁿᵃˡ", real(1e18), 501)
                .addUpgradeRequirement("Rationals")
                .addUpgradeRequirement("Irrationals")

                .addEfficiencyUpgrade("Fermat's Last Theorem", real(8e21), 501)
                .addUpgradeRequirement("Pythagorean Theorem")
                .addUpgradeRequirement("Number Theory")

                .addEfficiencyUpgrade("Birthday Paradox", real(8e23), 3.3f)
                .addUpgradeRequirement("Probability")

                .addEfficiencyUpgrade("Twin Prime Conjecture", imaginary(5e13), 358)
                .addUpgradeRequirement("Primes")
                .addUpgradeRequirement("Infinity")

                .addEfficiencyUpgrade("Goldbach's Conjecture", real(8e30), 151)
                .addUpgradeRequirement("Number Theory")
                .addUpgradeRequirement("Primes")
                .addUpgradeRequirement("Arrow's Impossibility")

                .addEfficiencyUpgrade("Gödel's Incompleteness", imaginary(5e17), 4)
                .addUpgradeRequirement("Goldbach's Conjecture");

        builder.createGenerator("Calculus", imaginary(1e7), imaginary(2_000))
                .addUpgradeRequirement("i")
                .addUpgradeRequirement("Irrationals")

                .addEfficiencyUpgrade("Limits", imaginary(3e8), 16)
                .addGeneratorRequirement("Calculus")

                .addEfficiencyUpgrade("Derivatives", imaginary(2e9), 2)
                .addUpgradeRequirement("Limits")

                .addEfficiencyUpgrade("e", imaginary(6e9), 3.71828f)
                .addUpgradeRequirement("Irrationals")
                .addUpgradeRequirement("Limits")

                .addEfficiencyUpgrade("Infinity", real(8e24), 101)
                .addGeneratorRequirement("Calculus")
                .addUpgradeRequirement("Probability")

                .addEfficiencyUpgrade("Countable Infinity", imaginary(1.5e13), 5)
                .addUpgradeRequirement("Infinity")

                .addEfficiencyUpgrade("Uncountable Infinity", real(3e27), 101)
                .addUpgradeRequirement("Countable Infinity")
                .addUpgradeRequirement("Mobius Strip")

                .addEfficiencyUpgrade("Complex Plane", imaginary(1.5e18), 1501)
                .addUpgradeRequirement("Trigonometric Functions")
                .addUpgradeRequirement("Goldbach's Conjecture");

        builder.createGenerator("Discrete Math", imaginary(2e11), real(1e17))
                .addUpgradeRequirement("Variables")
                .addUpgradeRequirement("Statistics")

                .addEfficiencyUpgrade("Number Theory", real(2e21), 16)
                .addGeneratorRequirement("Discrete Math")

                .addEfficiencyUpgrade("Primes", imaginary(5e11), 3.35711f)
                .addUpgradeRequirement("Number Theory")

                .addEfficiencyUpgrade("Probability", real(3e23), 13)
                .addGeneratorRequirement("Discrete Math")
                .addUpgradeRequirement("Primes")

                .addEfficiencyUpgrade("Game Theory", imaginary(2e16), 3_001)
                .addGeneratorRequirement("Discrete Math")
                .addUpgradeRequirement("Proof by Induction");

        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "Discrete Math", 0);
        setGeneratorCount(garden, state, "Calculus", 0);
        setGeneratorCount(garden, state, "Marvels and Mysteries", 0);
        setGeneratorCount(garden, state, "Geometry", 0);
        setGeneratorCount(garden, state, "Applied Math", 0);
        setGeneratorCount(garden, state, "Algebra", 0);
        setGeneratorCount(garden, state, "Arithmetic", 0);
        setGeneratorCount(garden, state, "Building Blocks", 1);

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

//                "Four-Color Theorem", // imaginary
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

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.multiCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}

package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Improvement;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.ImprovementDescription;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class MindMachines {

    private static final String CURRENCY = "Circuits"; // "Nodes"

    private static Amount amount(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Mind Machines");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Artificial Intelligence", amount(10), 2, amount(1e8), 100 * 24 * 3600)

                .addSpeedUpgrade("Future AI", amount(1.5e20), 50)
                .addUpgradeRequirement("Mind Reading")
                .addUpgradeRequirement("Tough Decisions")

                .addSpeedUpgrade("Quantum Computing", amount(2e20), 2)
                .addUpgradeRequirement("Future AI")

                .addSpeedUpgrade("Biocomputers", amount(3.5e20), 2.5f)
                .addUpgradeRequirement("Quantum Computing")

                .addSpeedUpgrade("Uploaded Mind", amount(7e20), 2.5f)
                .addUpgradeRequirement("Biocomputers")

                .addEfficiencyUpgrade("Philosophical Zombies", amount(1.5e21), 3)
                .addUpgradeRequirement("Future AI")
                .addUpgradeRequirement("Uploaded Mind")

                .addEfficiencyUpgrade("Morality AI", amount(4e21), 3)
                .addUpgradeRequirement("Future AI")
                .addUpgradeRequirement("Philosophical Zombies")

                .addEfficiencyUpgrade("Sentience", amount(1e22), 5)
                .addUpgradeRequirement("Future AI")
                .addUpgradeRequirement("Morality AI")

                .addEfficiencyUpgrade("Artificial General Intelligence", amount(1.5e22), 101)
                .addUpgradeRequirement("Sentience");

        builder.createGenerator("Mechanical Brain", amount(500), 1.25f, amount(5))
//                .addUpgradeRequirement("Mechanical Turk")

                .addEfficiencyUpgrade("Analytic Engine", amount(1_500), 1.5f)
                .addGeneratorRequirement("Mechanical Brain")

                .addEfficiencyUpgrade("Binary Code", amount(3_000), 1.5f)
                .addUpgradeRequirement("Analytic Engine")

                .addEfficiencyUpgrade("Logic Switches", amount(10_000), 1.5f)
                .addUpgradeRequirement("Binary Code")

                .addEfficiencyUpgrade("Input, Output", amount(60_000), 1.75f)
                .addGeneratorRequirement("Mechanical Brain")

                .addEfficiencyUpgrade("Algorithms", amount(150_000), 1.5f)
                .addUpgradeRequirement("Input, Output")

                .addEfficiencyUpgrade("Learning Loops", amount(500_000), 1.5f)
                .addUpgradeRequirement("Algorithms")

                .addEfficiencyUpgrade("Love Letters", amount(1.25e6), 1.75f)
                .addUpgradeRequirement("Learning Loops")

                .addEfficiencyUpgrade("Imitation Game", amount(1e7), 4)
                .addGeneratorRequirement("Mechanical Brain")
                .addGeneratorRequirement("Generative AI")

                .addEfficiencyUpgrade("ELIZA Therapy Bot", amount(4e9), 10)
                .addUpgradeRequirement("Imitation Game")

                .addEfficiencyUpgrade("Stochastic Parrot", amount(9e9), 8)
                .addUpgradeRequirement("ELIZA Therapy Bot");


        builder.createGenerator("Analytic AI", amount(3e6), amount(1_300))
                .addUpgradeRequirement("Love Letters")

                .addEfficiencyUpgrade("Turochamp", amount(3e7), 1.5f)
                .addGeneratorRequirement("Analytic AI")

                .addEfficiencyUpgrade("Logical Thinking", amount(5e7), 2)
                .addGeneratorRequirement("Analytic AI")

                .addEfficiencyUpgrade("Data Processing", amount(1.75e8), 2)
                .addGeneratorRequirement("Analytic AI")
                .addUpgradeRequirement("Turochamp")

                .addEfficiencyUpgrade("Brute Force", amount(4e8), 1.5f)
                .addUpgradeRequirement("Logical Thinking")

                .addEfficiencyUpgrade("Heuristics", amount(1e9), 1.75f)
                .addUpgradeRequirement("Brute Force")

                .addEfficiencyUpgrade("Fuzzy Logic", amount(2.5e9), 1.5f)
                .addUpgradeRequirement("Heuristics")

                .addEfficiencyUpgrade("Games, Mastered", amount(1.5e10), 1.5f)
                .addUpgradeRequirement("Turochamp")
                .addUpgradeRequirement("ELIZA Therapy Bot")

                .addEfficiencyUpgrade("Expert Systems", amount(2e10), 1.5f)
                .addUpgradeRequirement("Games, Mastered")

                .addEfficiencyUpgrade("Context", amount(5e10), 1.5f)
                .addUpgradeRequirement("Data Processing")

                .addEfficiencyUpgrade("Black Box", amount(7e10), 1.5f)
                .addUpgradeRequirement("Context");

        builder.createGenerator("Generative AI", amount(6e6), amount(1_330))
                .addGeneratorRequirement("Analytic AI")

                .addEfficiencyUpgrade("Electric Brain", amount(2e7), 1.5f)
                .addGeneratorRequirement("Generative AI")
                .addUpgradeRequirement("Imitation Game")

                .addEfficiencyUpgrade("Neural Network", amount(1e11), 15)
                .addUpgradeRequirement("Electric Brain")
                .addUpgradeRequirement("Stochastic Parrot")

                .addEfficiencyUpgrade("Machine Learning", amount(1.25e11), 10)
                .addGeneratorRequirement("Generative AI")
                .addUpgradeRequirement("Stochastic Parrot")

                .addEfficiencyUpgrade("Training Sets", amount(2e11), 3)
                .addGeneratorRequirement("Generative AI")
                .addUpgradeRequirement("Stochastic Parrot")

                .addEfficiencyUpgrade("Big Data", amount(5e11), 2)
                .addUpgradeRequirement("Neural Network")

                .addEfficiencyUpgrade("Large Language Models", amount(9e11), 3)
                .addUpgradeRequirement("Training Sets")

                .addEfficiencyUpgrade("GPTs", amount(1.5e12), 2)
                .addUpgradeRequirement("Large Language Models")

                .addEfficiencyUpgrade("Deep Learning", amount(3e12), 2)
                .addUpgradeRequirement("Machine Learning")

                .addEfficiencyUpgrade("Natural Language", amount(9e12), 2)
                .addUpgradeRequirement("GPTs")

                .addEfficiencyUpgrade("Hallucinations", amount(1.5e13), 2)
                .addUpgradeRequirement("Natural Language")

                .addEfficiencyUpgrade("AI Evolved", amount(4e13), 3.5f)
                .addUpgradeRequirement("Deep Learning");

        builder.createGenerator("AI Tools", amount(9e13), amount(4e9))
                .addUpgradeRequirement("AI Evolved")

                .addEfficiencyUpgrade("Personal Assistants", amount(3e14), 2)
                .addGeneratorRequirement("AI Tools")

                .addEfficiencyUpgrade("Customer Service", amount(6e14), 3)
                .addUpgradeRequirement("Personal Assistants")

                .addEfficiencyUpgrade("Therapists", amount(9e14), 2)
                .addUpgradeRequirement("Customer Service")

                .addEfficiencyUpgrade("Translators and Interpreters", amount(3e15), 2)
                .addUpgradeRequirement("Therapists")

                .addEfficiencyUpgrade("AI Avatars", amount(6e15), 3)
                .addUpgradeRequirement("Translators and Interpreters")

                .addEfficiencyUpgrade("Self-Driving Vehicles", amount(1.25e17), 2)
                .addGeneratorRequirement("AI Tools")

                .addEfficiencyUpgrade("AI Drones and Carts", amount(2e17), 3)
                .addUpgradeRequirement("Self-Driving Vehicles")

                .addEfficiencyUpgrade("Space Explorers", amount(6e17), 2)
                .addUpgradeRequirement("AI Drones and Carts")

                .addEfficiencyUpgrade("Statistical Forecasting", amount(3e18), 2)
                .addGeneratorRequirement("AI Tools")
                .addUpgradeRequirement("AI Avatars")

                .addEfficiencyUpgrade("Smarter Systems", amount(6e18), 3)
                .addUpgradeRequirement("Statistical Forecasting")

                .addEfficiencyUpgrade("Scientific Discoveries", amount(1e19), 2)
                .addUpgradeRequirement("Smarter Systems")

                .addEfficiencyUpgrade("Mind Reading", amount(1.75e19), 3)
                .addUpgradeRequirement("Scientific Discoveries");

        builder.createGenerator("Evils and Perils", amount(1.5e13), amount(1e10))
                .addGeneratorRequirement("AI Tools")

                .addEfficiencyUpgrade("Bad Actors", amount(1e16), 2)
                .addGeneratorRequirement("Evils and Perils")

                .addEfficiencyUpgrade("Gullibility", amount(2e16), 2)
                .addUpgradeRequirement("Bad Actors")

                .addEfficiencyUpgrade("Human Bias", amount(6e16), 3)
                .addUpgradeRequirement("Gullibility")

                .addEfficiencyUpgrade("Resistance", amount(9e16), 2)
                .addUpgradeRequirement("Human Bias")

                .addEfficiencyUpgrade("Weak Laws", amount(9e17), 3)
                .addGeneratorRequirement("Evils and Perils")

                .addEfficiencyUpgrade("AI Workforce", amount(1e18), 2)
                .addUpgradeRequirement("Weak Laws")

                .addEfficiencyUpgrade("Cyberattacks and Warfare", amount(2e18), 2)
                .addUpgradeRequirement("AI Workforce")

                .addEfficiencyUpgrade("Machine Unlearning", amount(4e19), 2)
                .addGeneratorRequirement("Evils and Perils")
                .addUpgradeRequirement("Resistance")

                .addEfficiencyUpgrade("Red Teaming", amount(8e19), 3)
                .addUpgradeRequirement("Machine Unlearning")

                .addEfficiencyUpgrade("Tough Decisions", amount(1e20), 3)
                .addUpgradeRequirement("Red Teaming");

        builder.resolveRequirements();

        builder.with("Artificial Intelligence")
                .addSpeedEffect("Learning Loops", 4)
                .addSpeedEffect("Brute Force", 0.5f)
                .addSpeedEffect("Stochastic Parrot", 0.75f)
                .addSpeedEffect("Games, Mastered", 9)
                .addEfficiencyEffect("Expert Systems", 51)
                .addSpeedEffect("Context", 0.75f)
                .addSpeedEffect("Black Box", 0.75f)
                .addSpeedEffect("GPTs", 10)
                .addSpeedEffect("Deep Learning", 10)
                .addEfficiencyEffect("AI Evolved", 51)
                .addAutomatedEffect("Personal Assistants")
                .addEfficiencyEffect("Customer Service", 2)
                .addSpeedEffect("Therapists", 2)
                .addEfficiencyEffect("Translators and Interpreters", 2)
                .addEfficiencyEffect("AI Avatars", 2)
                .addSpeedEffect("Bad Actors", 0.75f)
                .addSpeedEffect("Gullibility", 0.75f)
                .addSpeedEffect("Human Bias", 0.75f)
                .addSpeedEffect("Resistance", 0.75f)
                .addSpeedEffect("Self-Driving Vehicles", 2)
                .addSpeedEffect("AI Drones and Carts", 2)
                .addSpeedEffect("Space Explorers", 5)
                .addSpeedEffect("Weak Laws", 0.5f)
                .addSpeedEffect("AI Workforce", 0.5f)
                .addSpeedEffect("Cyberattacks and Warfare", 0.5f)
                .addSpeedEffect("Statistical Forecasting", 10)
                .addEfficiencyEffect("Smarter Systems", 6)
                .addSpeedEffect("Scientific Discoveries", 11)
                .addSpeedEffect("Mind Reading", 10)
                .addSpeedEffect("Machine Unlearning", 0.75f)
                .addSpeedEffect("Red Teaming", 0.75f)
                .addSpeedEffect("Tough Decisions", 2);

        return garden;
    }

    public static void singleCurrencyApproach(Garden garden, GardenState gardenState, List<String> actions) {
        GardenState state = gardenState.copy();
        CurrencyMapping mapping = new CurrencyMapping(CURRENCY, CURRENCY);

        printUnlocked(garden, state, actions);
        for (int i = 0; i < 100; i += 1) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            ImprovementDescription improvementDescription =
                    ImprovementCalculator.calculateImprovement(garden, state).get(mapping);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();
            Improvement improvement = improvementDescription.improvement();
            if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                int count = generatorState.count();
                actions.add(String.format("(%d) Generator %s: %d -> %d : %s",
                        i + 1, generator.getName(), count, count + 1, improvementDescription.description()));
                state.setGeneratorCount(generator, count + 1);
                if (count == 0) {
                    printUnlocked(garden, state, actions);
                    if (i >= 20) {
                        break;
                    }
                }
            } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
                Upgrade upgrade = upgradeImprovement.upgrade();
                UpgradeEffect effect = upgrade.getEffects().getFirst();
                actions.add(String.format("(%d) Upgrade %s (%s) : %s",
                        i + 1, upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                state.setUpgradeBought(upgrade);
                state.updateGeneratorStates(garden);
                printUnlocked(garden, state, actions);
                if (i >= 20) {
                    break;
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "Evils and Perils", 0);
        setGeneratorCount(garden, state, "AI Tools", 0);
        setGeneratorCount(garden, state, "Generative AI", 0);
        setGeneratorCount(garden, state, "Analytic AI", 0);
        setGeneratorCount(garden, state, "Mechanical Brain", 1);
        setGeneratorCount(garden, state, "Artificial Intelligence", 1);

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

        List<String> actions = new ArrayList<>();
        singleCurrencyApproach(garden, state, actions);

        actions.forEach(System.out::println);
    }

    private static void printUnlocked(Garden garden, GardenState state, List<String> actions) {
        for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
            if (state.getGeneratorState(generator).count() == 0) {
                actions.add(String.format(" *** unlocked generator %s: base cost %s, inc %.2f, base production %s",
                        generator.getName(), generator.getBaseCost().asString(), generator.getCompoundingCost(),
                        generator.getBaseProduction().multiplyBy(state.getBoost()).asString()));
            }
        }
        for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
            if (!state.isUpgradeBought(upgrade)) {
                for (UpgradeEffect effect : upgrade.getEffects()) {
                    if (effect.speed() == 1) {
                        actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.efficiency(),
                                upgrade.getCost().asString()));
                    } else if (effect.efficiency() == 1) {
                        actions.add(String.format(" *** unlocked upgrade %s: %s speed %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.speed(),
                                upgrade.getCost().asString()));
                    } else {
                        actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, speed %.2f, cost %s",
                                upgrade.getName(), effect.generator().getName(), effect.efficiency(), effect.speed(),
                                upgrade.getCost().asString()));
                    }
                }
            }
        }
    }
}

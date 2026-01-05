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

public class UnfoldTheUniverse {

    private static final String MIRRORS_CURRENCY = " Honeycomb Mirrors";

    private static Amount mirrors(double amount) {
        return new Amount(MIRRORS_CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier) {
        Garden garden = new Garden("Unfold the Universe");
        garden.addCurrency(MIRRORS_CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier);

        builder.createGenerator("Ground Telescope", mirrors(15), mirrors(0.5))
                //.addUpgradeRequirement("Look to the Stars")

                .addUpgrade("Space Telescope", mirrors(150), 2)
                .addGeneratorRequirement("Ground Telescope")

                .addUpgrade("Origins", mirrors(700), 2)
                .addUpgradeRequirement("Space Telescope");

        builder.createGenerator("Hubble Telescope", mirrors(2_500), mirrors(4))
                .addUpgradeRequirement("Space Telescope")

                .addUpgrade("Repair Mission", mirrors(10_000), 2)
                .addGeneratorRequirement("Hubble Telescope")

                .addUpgrade("Landmark Discoveries", mirrors(20_000), 1.5f)
                .addUpgradeRequirement("Repair Mission")

                .addUpgrade("Hubble's Successor", mirrors(50_000), 1.5f)
                .addUpgradeRequirement("Landmark Discoveries")

                .addUpgrade("Distance from Earth", mirrors(500_000), 3)
                .addUpgradeRequirement("Landmark Discoveries")

                .addUpgrade("Size Comparison", mirrors(7e6), 3)
                .addUpgradeRequirement("Distance from Earth");

        builder.createGenerator("James Webb Telescope", mirrors(50_000), mirrors(40))
                .addUpgradeRequirement("Hubble's Successor")

                .addUpgrade("James E. Webb", mirrors(200_000), 2)
                .addGeneratorRequirement("James Webb Telescope")

                .addUpgrade("Naming", mirrors(3e6), 2)
                .addUpgradeRequirement("James E. Webb")

                .addUpgrade("Mission Objectives", mirrors(3e7), 2)
                .addGeneratorRequirement("James Webb Telescope")

                .addUpgrade("Mission Length", mirrors(7.5e7), 2.5f)
                .addUpgradeRequirement("Mission Objectives")

                .addUpgrade("Cleared Name", mirrors(4e8), 3.5f)
                .addUpgradeRequirement("Naming")

                .addUpgrade("International Collaboration", mirrors(2.5e9), 3)
                .addGeneratorRequirement("James Webb Telescope")

                .addUpgrade("Budget", mirrors(6e9), 2)
                .addGeneratorRequirement("James Webb Telescope")
                .addGeneratorRequirement("Development")

                .addUpgrade("Ground Support", mirrors(4e12), 1_001)
                .addGeneratorRequirement("Launch")
                .addUpgradeRequirement("International Collaboration");

        builder.createGenerator("Development", mirrors(6e7), mirrors(4_000))
                .addUpgradeRequirement("Mission Objectives")

                .addUpgrade("Black Holes", mirrors(2e8), 2)
                .addGeneratorRequirement("Development")

                .addUpgrade("Galactic Birth", mirrors(1.2e9), 2)
                .addUpgradeRequirement("Black Holes")

                .addUpgrade("Funding", mirrors(1.5e10), 3)
                .addGeneratorRequirement("Development")
                .addUpgradeRequirement("Budget")

                .addUpgrade("Seeking Exoplanets", mirrors(2e10), 2)
                .addUpgradeRequirement("Galactic Birth")

                .addUpgrade("Averted Cancellation", mirrors(5e10), 2)
                .addUpgradeRequirement("Funding")

                .addUpgrade("Delayed Launch", mirrors(1.2e11), 2)
                .addUpgradeRequirement("Averted Cancellation");

        builder.createGenerator("Construction", mirrors(2.5e10), mirrors(600_000))
                .addUpgradeRequirement("Funding")

                .addUpgrade("Infrared Visibility", mirrors(3e10), 1.5f)
                .addGeneratorRequirement("Construction")

                .addUpgrade("Micro Shutters", mirrors(9e10), 2)
                .addGeneratorRequirement("Construction")

                .addUpgrade("Dangerous Heat", mirrors(2.5e11), 1.5f)
                .addUpgradeRequirement("Infrared Visibility")

                .addUpgrade("Sunshield", mirrors(5e11), 1.5f)
                .addUpgradeRequirement("Dangerous Heat")

                .addUpgrade("Hexagonal Mirrors", mirrors(1.5e12), 2.5f)
                .addUpgradeRequirement("Micro Shutters");

        builder.createGenerator("Launch", mirrors(1.2e11), mirrors(5e6))
                .addUpgradeRequirement("Delayed Launch")
                .addUpgradeRequirement("Infrared Visibility")

                .addUpgrade("Christmas Launch", mirrors(1e12), 2)
                .addGeneratorRequirement("Launch")

                .addUpgrade("Journey to L2", mirrors(2.5e12), 3)
                .addUpgradeRequirement("Christmas Launch")

                .addUpgrade("Sunshield Unfolding", mirrors(1.2e13), 2)
                .addUpgradeRequirement("Journey to L2")
                .addUpgradeRequirement("Sunshield")

                .addUpgrade("Mirrors Unfolding", mirrors(2e13), 2)
                .addUpgradeRequirement("Sunshield Unfolding")
                .addUpgradeRequirement("Hexagonal Mirrors")

                .addUpgrade("Secondary Mirrors", mirrors(4e13), 2)
                .addUpgradeRequirement("Mirrors Unfolding")

                .addUpgrade("Primary Mirrors", mirrors(9e13), 3)
                .addUpgradeRequirement("Mirrors Unfolding")

                .addUpgrade("Warm-Up Period", mirrors(3e14), 4)
                .addUpgradeRequirement("Primary Mirrors")
                .addUpgradeRequirement("Secondary Mirrors")

                .addUpgrade("First Images", mirrors(1e15), 2)
                .addUpgradeRequirement("Warm-Up Period");

        builder.resolveRequirements();

        return garden;
    }

    public static void singleCurrencyApproach(Garden garden, GardenState gardenState, List<String> actions) {
        GardenState state = gardenState.copy();
        CurrencyMapping mapping = new CurrencyMapping(MIRRORS_CURRENCY, MIRRORS_CURRENCY);

        boolean possibleUnlock = false;
        printUnlocked(garden, state, actions);
        for (int i = 0; i < 100 && !possibleUnlock; i += 1) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            ImprovementDescription improvementDescription =
                    ImprovementCalculator.calculateImprovement(garden, state).get(mapping);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();
            possibleUnlock = false;
            Improvement improvement = improvementDescription.improvement();
            if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                int count = generatorState.count();
                actions.add(String.format("(%d) Generator %s: %d -> %d : %s",
                        i + 1, generator.getName(), count, count + 1, improvementDescription.description()));
                state.setGeneratorCount(generator, count + 1);
                if (count == 0) {
                    if (i >= 20) {
                        possibleUnlock = true;
                    }
                    printUnlocked(garden, state, actions);
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
                    possibleUnlock = true;
                }
            } else {
                throw new NullPointerException();
            }
        }

    }

    void main() {
        Garden garden = createGarden(1, 1);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "Launch", 0);
        setGeneratorCount(garden, state, "Construction", 0);
        setGeneratorCount(garden, state, "Development", 0);
        setGeneratorCount(garden, state, "James Webb Telescope", 0);
        setGeneratorCount(garden, state, "Hubble Telescope", 0);
        setGeneratorCount(garden, state, "Ground Telescope", 1);

        String[] boughtUpdates = {
//                "Space Telescope", // check
//                "Origins", // check

//                "Repair Mission", // check
//                "Landmark Discoveries", // check
//                "Hubble's Successor", // check
//                "Distance from Earth", // check
//                "Size Comparison", // check

//                "James E. Webb", // check
//                "Naming", // check
//                "Mission Objectives", // check
//                "Mission Length", // check
//                "Cleared Name", // check
//                "International Collaboration", // check
//                "Budget", // check
//                "Ground Support", // check

//                "Black Holes", // check
//                "Galactic Birth", // check
//                "Funding", // check
//                "Seeking Exoplanets", // check
//                "Averted Cancellation", // check
//                "Delayed Launch", // check

//                "Infrared Visibility", // check
//                "Micro Shutters", // check
//                "Dangerous Heat", // check
//                "Sunshield", // check
//                "Hexagonal Mirrors", // check

//                "Christmas Launch", // check
//                "Journey to L2", // check
//                "Sunshield Unfolding", // check
//                "Mirrors Unfolding", // check
//                "Secondary Mirrors", // check
//                "Primary Mirrors", // check
//                "Warm-Up Period", // check
//                "First Images", // check
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
                    actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, cost %s",
                            upgrade.getName(), effect.generator().getName(), effect.efficiency(),
                            upgrade.getCost().asString()));
                }
            }
        }
    }
}

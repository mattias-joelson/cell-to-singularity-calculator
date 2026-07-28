package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class UnfoldTheUniverse {

    private static final String MIRRORS_CURRENCY = "Honeycomb Mirrors";

    private static Amount mirrors(double amount) {
        return new Amount(MIRRORS_CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Unfold the Universe");
        garden.addCurrency(MIRRORS_CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Ground Telescope", mirrors(15), mirrors(0.5))
                //.addUpgradeRequirement("Look to the Stars")

                .addEfficiencyUpgrade("Space Telescope", mirrors(150), 2)
                .addGeneratorRequirement("Ground Telescope")

                .addEfficiencyUpgrade("Origins", mirrors(700), 2)
                .addUpgradeRequirement("Space Telescope");

        builder.createGenerator("Hubble Telescope", mirrors(2_500), mirrors(4))
                .addUpgradeRequirement("Space Telescope")

                .addEfficiencyUpgrade("Repair Mission", mirrors(10_000), 2)
                .addGeneratorRequirement("Hubble Telescope")

                .addEfficiencyUpgrade("Landmark Discoveries", mirrors(20_000), 1.5f)
                .addUpgradeRequirement("Repair Mission")

                .addEfficiencyUpgrade("Hubble's Successor", mirrors(50_000), 1.5f)
                .addUpgradeRequirement("Landmark Discoveries")

                .addEfficiencyUpgrade("Distance from Earth", mirrors(500_000), 3)
                .addUpgradeRequirement("Landmark Discoveries")

                .addEfficiencyUpgrade("Size Comparison", mirrors(7e6), 3)
                .addUpgradeRequirement("Distance from Earth");

        builder.createGenerator("James Webb Telescope", mirrors(50_000), mirrors(40))
                .addUpgradeRequirement("Hubble's Successor")

                .addEfficiencyUpgrade("James E. Webb", mirrors(200_000), 2)
                .addGeneratorRequirement("James Webb Telescope")

                .addEfficiencyUpgrade("Naming", mirrors(3e6), 2)
                .addUpgradeRequirement("James E. Webb")

                .addEfficiencyUpgrade("Mission Objectives", mirrors(3e7), 2)
                .addGeneratorRequirement("James Webb Telescope")

                .addEfficiencyUpgrade("Mission Length", mirrors(7.5e7), 2.5f)
                .addUpgradeRequirement("Mission Objectives")

                .addEfficiencyUpgrade("Cleared Name", mirrors(4e8), 3.5f)
                .addUpgradeRequirement("Naming")

                .addEfficiencyUpgrade("International Collaboration", mirrors(2.5e9), 3)
                .addGeneratorRequirement("James Webb Telescope")

                .addEfficiencyUpgrade("Budget", mirrors(6e9), 2)
                .addGeneratorRequirement("James Webb Telescope")
                .addGeneratorRequirement("Development")

                .addEfficiencyUpgrade("Ground Support", mirrors(4e12), 1_001)
                .addGeneratorRequirement("Launch")
                .addUpgradeRequirement("International Collaboration");

        builder.createGenerator("Development", mirrors(6e7), mirrors(4_000))
                .addUpgradeRequirement("Mission Objectives")

                .addEfficiencyUpgrade("Black Holes", mirrors(2e8), 2)
                .addGeneratorRequirement("Development")

                .addEfficiencyUpgrade("Galactic Birth", mirrors(1.2e9), 2)
                .addUpgradeRequirement("Black Holes")

                .addEfficiencyUpgrade("Funding", mirrors(1.5e10), 3)
                .addGeneratorRequirement("Development")
                .addUpgradeRequirement("Budget")

                .addEfficiencyUpgrade("Seeking Exoplanets", mirrors(2e10), 2)
                .addUpgradeRequirement("Galactic Birth")

                .addEfficiencyUpgrade("Averted Cancellation", mirrors(5e10), 2)
                .addUpgradeRequirement("Funding")

                .addEfficiencyUpgrade("Delayed Launch", mirrors(1.2e11), 2)
                .addUpgradeRequirement("Averted Cancellation");

        builder.createGenerator("Construction", mirrors(2.5e10), mirrors(600_000))
                .addUpgradeRequirement("Funding")

                .addEfficiencyUpgrade("Infrared Visibility", mirrors(3e10), 1.5f)
                .addGeneratorRequirement("Construction")

                .addEfficiencyUpgrade("Micro Shutters", mirrors(9e10), 2)
                .addGeneratorRequirement("Construction")

                .addEfficiencyUpgrade("Dangerous Heat", mirrors(2.5e11), 1.5f)
                .addUpgradeRequirement("Infrared Visibility")

                .addEfficiencyUpgrade("Sunshield", mirrors(5e11), 1.5f)
                .addUpgradeRequirement("Dangerous Heat")

                .addEfficiencyUpgrade("Hexagonal Mirrors", mirrors(1.5e12), 2.5f)
                .addUpgradeRequirement("Micro Shutters");

        builder.createGenerator("Launch", mirrors(1.2e11), mirrors(5e6))
                .addUpgradeRequirement("Delayed Launch")
                .addUpgradeRequirement("Infrared Visibility")

                .addEfficiencyUpgrade("Christmas Launch", mirrors(1e12), 2)
                .addGeneratorRequirement("Launch")

                .addEfficiencyUpgrade("Journey to L2", mirrors(2.5e12), 3)
                .addUpgradeRequirement("Christmas Launch")

                .addEfficiencyUpgrade("Sunshield Unfolding", mirrors(1.2e13), 2)
                .addUpgradeRequirement("Journey to L2")
                .addUpgradeRequirement("Sunshield")

                .addEfficiencyUpgrade("Mirrors Unfolding", mirrors(2e13), 2)
                .addUpgradeRequirement("Sunshield Unfolding")
                .addUpgradeRequirement("Hexagonal Mirrors")

                .addEfficiencyUpgrade("Secondary Mirrors", mirrors(4e13), 3)
                .addUpgradeRequirement("Mirrors Unfolding")

                .addEfficiencyUpgrade("Primary Mirrors", mirrors(9e13), 3)
                .addUpgradeRequirement("Mirrors Unfolding")

                .addEfficiencyUpgrade("Warm-Up Period", mirrors(3e14), 4)
                .addUpgradeRequirement("Primary Mirrors")
                .addUpgradeRequirement("Secondary Mirrors")

                .addEfficiencyUpgrade("First Images", mirrors(1e15), 2)
                .addUpgradeRequirement("Warm-Up Period");

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "Launch", 0);
        setGeneratorCount(garden, state, "Construction", 0);
        setGeneratorCount(garden, state, "Development", 0);
        setGeneratorCount(garden, state, "James Webb Telescope", 0);
        setGeneratorCount(garden, state, "Hubble Telescope", 0);
        setGeneratorCount(garden, state, "Ground Telescope", 1);

        String[] boughtUpgrades = {
//                "Space Telescope",
//                "Origins",

//                "Repair Mission",
//                "Landmark Discoveries",
//                "Hubble's Successor",
//                "Distance from Earth",
//                "Size Comparison",

//                "James E. Webb",
//                "Naming",
//                "Mission Objectives",
//                "Mission Length",
//                "Cleared Name",
//                "International Collaboration",
//                "Budget",
//                "Ground Support",

//                "Black Holes",
//                "Galactic Birth",
//                "Funding",
//                "Seeking Exoplanets",
//                "Averted Cancellation",
//                "Delayed Launch",

//                "Infrared Visibility",
//                "Micro Shutters",
//                "Dangerous Heat",
//                "Sunshield",
//                "Hexagonal Mirrors",

//                "Christmas Launch",
//                "Journey to L2",
//                "Sunshield Unfolding",
//                "Mirrors Unfolding",
//                "Secondary Mirrors",
//                "Primary Mirrors",
//                "Warm-Up Period",
//                "First Images",
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.singleCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }

}

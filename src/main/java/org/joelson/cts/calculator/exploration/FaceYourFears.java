package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class FaceYourFears {

    private static final String TORCHES_CURRENCY = "Torches";

    private static Amount torches(double amount) {
        return new Amount(TORCHES_CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Face Your Fears");
        garden.addCurrency(TORCHES_CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("The Insidious", torches(100), torches(1))
//                .addUpgradeRequirement("The Campfire")

                .addEfficiencyUpgrade("Kobold", torches(400), 2)
                .addGeneratorRequirement("The Insidious")

                .addEfficiencyUpgrade("Bannik", torches(1_000), 2)
                .addUpgradeRequirement("Kobold")

                .addEfficiencyUpgrade("Kappa", torches(5_000), 2)
                .addUpgradeRequirement("Bannik")

                .addEfficiencyUpgrade("Bunyip", torches(9e6), 21)
                .addUpgradeRequirement("Mermaid")

                .addEfficiencyUpgrade("Adze", torches(2.5e12), 50_001)
                .addUpgradeRequirement("Capelobo")

                .addEfficiencyUpgrade("Crocotta", torches(4e14), 31)
                .addUpgradeRequirement("Yeti");

        builder.createGenerator("The Uncanny", torches(25_000), torches(50))
//                .addUpgradeRequirement("The Campfire")
                .addUpgradeRequirement("Kappa")

                .addEfficiencyUpgrade("Baba Yaga", torches(200_000), 2)
                .addGeneratorRequirement("The Uncanny")

                .addEfficiencyUpgrade("Mermaid", torches(1.5e6), 2)
                .addGeneratorRequirement("The Uncanny")

                .addEfficiencyUpgrade("Hal", torches(5e7), 2.5f)
                .addUpgradeRequirement("Mermaid")

                .addEfficiencyUpgrade("Changeling", torches(2.5e9), 26)
                .addUpgradeRequirement("Raiju")

                .addEfficiencyUpgrade("Chupacabra", torches(5e10), 11)
                .addUpgradeRequirement("Dingonek")

                .addEfficiencyUpgrade("Capelobo", torches(8e11), 11)
                .addUpgradeRequirement("Chupacabra")

                .addEfficiencyUpgrade("Yeti", torches(1e14), 101)
                .addUpgradeRequirement("Mongolian Death Worm")

                .addEfficiencyUpgrade("Mothman", torches(4e15), 21)
                .addUpgradeRequirement("Jinn")

                .addEfficiencyUpgrade("Jersey Devil", torches(1e16), 6)
                .addUpgradeRequirement("Jinn")

                .addEfficiencyUpgrade("Bigfoot", torches(1e18), 2)
                .addUpgradeRequirement("Loch Ness Monster");

        builder.createGenerator("The Unfathomable", torches(5e7), torches(2_500))
//                .addUpgradeRequirement("The Campfire")
                .addUpgradeRequirement("Hal")

                .addEfficiencyUpgrade("Yacumama", torches(4e8), 4)
                .addGeneratorRequirement("The Unfathomable")

                .addEfficiencyUpgrade("Raiju", torches(1e9), 2)
                .addGeneratorRequirement("The Unfathomable")

                .addEfficiencyUpgrade("Dingonek", torches(1e10), 6)
                .addUpgradeRequirement("Raiju")

                .addEfficiencyUpgrade("Tatzelwurm", torches(2e11), 6)
                .addUpgradeRequirement("Chupacabra")

                .addEfficiencyUpgrade("Mongolian Death Worm", torches(2e13), 101)
                .addUpgradeRequirement("Capelobo")

                .addEfficiencyUpgrade("Jinn", torches(1e15), 26)
                .addUpgradeRequirement("Yeti")

                .addEfficiencyUpgrade("Loch Ness Monster", torches(5e16), 501)
                .addUpgradeRequirement("Jersey Devil");

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "The Unfathomable", 0);
        setGeneratorCount(garden, state, "The Uncanny", 0);
        setGeneratorCount(garden, state, "The Insidious", 1);

        String[] boughtUpgrades = {
//                "Kobold",
//                "Bannik",
//                "Kappa",
//                "Bunyip",
//                "Adze",
//                "Crocotta",

//                "Baba Yaga",
//                "Mermaid",
//                "Hal",
//                "Changeling",
//                "Chupacabra",
//                "Capelobo",
//                "Yeti",
//                "Mothman",
//                "Jersey Devil",
//                "Bigfoot",

//                "Yacumama",
//                "Raiju",
//                "Dingonek",
//                "Tatzelwurm",
//                "Mongolian Death Worm",
//                "Jinn",
//                "Loch Ness Monster",
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.singleCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }

}

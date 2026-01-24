package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class SetInStone {

    private static final String MINERALS_CURRENCY = "Minerals";
    private static final String ROCKS_CURRENCY = "Rocks";
    private static final String CRYSTALS_CURRENCY = "Crystals";

    private static final String[] CURRENCIES = { MINERALS_CURRENCY, ROCKS_CURRENCY, CRYSTALS_CURRENCY };

    private static Amount minerals(double amount) {
        return new Amount(MINERALS_CURRENCY, amount);
    }

    private static Amount rocks(double amount) {
        return new Amount(ROCKS_CURRENCY, amount);
    }

    private static Amount crystals(double amount) {
        return new Amount(CRYSTALS_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Set in Stone");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Mineral", minerals(150), 1.15f, minerals(2))
                //.addUpgradeRequirement("Earthly Origins")

                .addEfficiencyUpgrade("Olivine", minerals(400), 1.5f)
                .addGeneratorRequirement("Mineral")

                .addEfficiencyUpgrade("Quartz", minerals(2_500), 1.75f)
                .addGeneratorRequirement("Mineral")

                .addEfficiencyUpgrade("Feldspars", minerals(7_800), 1.5f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Quartz")

                .addEfficiencyUpgrade("Magma", minerals(32_000), 2)
                .addUpgradeRequirement("Quartz")

                .addEfficiencyUpgrade("Tuff", rocks(18_000), 2.25f)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Basalt")

                .addEfficiencyUpgrade("Clay", minerals(900_000), 1.75f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Basalt")

                .addEfficiencyUpgrade("Rock Cycle", minerals(1.7e6), 1.75f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Basalt")

                .addEfficiencyUpgrade("Sand", minerals(4.5e6), 2.5f)
                .addUpgradeRequirement("Rock Cycle")

                .addEfficiencyUpgrade("Sandstone", rocks(2.5e8), 2.25f)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Andesite")

                .addEfficiencyUpgrade("Calcite", minerals(3e8), 3.5f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Andesite")

                .addEfficiencyUpgrade("Granite", rocks(1e11), 6)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Scoria")

                .addEfficiencyUpgrade("Gneiss", rocks(9e12), 6)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Shale")

                .addEfficiencyUpgrade("Fluorite", minerals(7e10), 11)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Shale")

                .addEfficiencyUpgrade("Pegmatite", rocks(5e14), 101)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Amethyst")

                .addEfficiencyUpgrade("Coal", rocks(9e16), 101)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Topaz");

        builder.createGenerator("Igneous Rock", minerals(50_000), 1.15f, rocks(1))
                .addUpgradeRequirement("Magma")

                .addEfficiencyUpgrade("Dunite", rocks(1_000), 2)
                .addGeneratorRequirement("Igneous Rock")

                .addEfficiencyUpgrade("Basalt", rocks(5_000), 2)
                .addGeneratorRequirement("Igneous Rock")

                .addEfficiencyUpgrade("Andesite", rocks(3e7), 31)
                .addGeneratorRequirement("Igneous Rock")
                .addGeneratorRequirement("Sedimentary Rock")

                .addEfficiencyUpgrade("Diorite", rocks(3e7), 31)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Andesite")

                .addEfficiencyUpgrade("Scoria", rocks(1e10), 301)
                .addGeneratorRequirement("Igneous Rock")
                .addGeneratorRequirement("Metamorphic Rock")

                .addEfficiencyUpgrade("Obsidian", rocks(6e16), 35_001)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Topaz");

        builder.createGenerator("Sedimentary Rock", minerals(1e7), 1.15f, rocks(250))
                .addUpgradeRequirement("Sand")

                .addEfficiencyUpgrade("Limestone", rocks(1e6), 1.75f)
                .addGeneratorRequirement("Sedimentary Rock")

                .addEfficiencyUpgrade("Siltstone", rocks(6e6), 2)
                .addGeneratorRequirement("Sedimentary Rock")

                .addEfficiencyUpgrade("Coquina", rocks(3e11), 101)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Scoria")

                .addEfficiencyUpgrade("Shale", rocks(9.5e11), 201)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Scoria")

                .addEfficiencyUpgrade("Chalk", rocks(1e14), 41)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Amethyst")

                .addEfficiencyUpgrade("Flint", rocks(2e15), 41)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Amethyst")
                .addUpgradeRequirement("Jasper")

                .addEfficiencyUpgrade("Natural Beauty", rocks(2.5e21), 2)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Jade");

        builder.createGenerator("Metamorphic Rock", minerals(3e8), 1.15f, rocks(100_000))
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Rock Cycle")
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Calcite")

                .addEfficiencyUpgrade("Marble", rocks(9e8), 1.75f)
                .addGeneratorRequirement("Metamorphic Rock")

                .addEfficiencyUpgrade("Slate", rocks(4e9), 2)
                .addGeneratorRequirement("Metamorphic Rock")

                .addEfficiencyUpgrade("Schist", rocks(5e12), 201)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Shale")

                .addEfficiencyUpgrade("Anthracite", rocks(1e17), 15_001)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Coal")

                .addEfficiencyUpgrade("Jade", crystals(1.2e17), 889)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Lab-Grown Diamonds");

        builder.createGenerator("Crystal", minerals(4e11), 1.15f, crystals(1))
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Fluorite")

                .addEfficiencyUpgrade("Amethyst", crystals(8_000), 2)
                .addGeneratorRequirement("Crystal")

                .addEfficiencyUpgrade("Jasper", crystals(500_000), 6)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Amethyst")

                .addEfficiencyUpgrade("Fulgurite", rocks(1.2e16), 11)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Jasper")

                .addEfficiencyUpgrade("Topaz", crystals(4.5e7), 21)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Jasper")

                .addEfficiencyUpgrade("Lapis Lazuli", rocks(9e17), 31)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Coal")

                .addEfficiencyUpgrade("Diamond", crystals(1e11), 41)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Coal")

                .addEfficiencyUpgrade("Pyrite", crystals(2.7e13), 21)
                .addGeneratorRequirement("Crystal")
                .addGeneratorRequirement("Gem")

                .addEfficiencyUpgrade("Lab-Grown Diamonds", crystals(1.2e15), 31)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Pyrite");

        builder.createGenerator("Gem", crystals(3e12), 1.15f, crystals(1e8))
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Diamond")

                .addEfficiencyUpgrade("Emerald", crystals(3.3e12), 2.5f)
                .addGeneratorRequirement("Gem")

                .addEfficiencyUpgrade("Aquamarine", crystals(6e12), 2.5f)
                .addGeneratorRequirement("Gem")

                .addEfficiencyUpgrade("Opal", crystals(9e13), 3)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Pyrite")

                .addEfficiencyUpgrade("Garnet", crystals(3e14), 3)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Pyrite")

                .addEfficiencyUpgrade("Sapphire", crystals(3e15), 6)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Lab-Grown Diamonds")

                .addEfficiencyUpgrade("Ruby", crystals(1.5e16), 6)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Lab-Grown Diamonds");

        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "Gem", 0);
        setGeneratorCount(garden, state, "Crystal", 0);
        setGeneratorCount(garden, state, "Metamorphic Rock", 0);
        setGeneratorCount(garden, state, "Sedimentary Rock", 0);
        setGeneratorCount(garden, state, "Igneous Rock", 0);
        setGeneratorCount(garden, state, "Mineral", 1);

        String[] boughtUpdates = {
//                "Olivine",
//                "Quartz",
//                "Feldspars",
//                "Magma",
//                "Tuff", // rocks
//                "Clay",
//                "Rock Cycle",
//                "Sand",
//                "Sandstone", // rocks
//                "Calcite",
//                "Granite", // rocks
//                "Gneiss", // rocks
//                "Fluorite",
//                "Pegmatite", // rocks
//                "Coal", // rocks

//                "Dunite",
//                "Basalt",
//                "Andesite",
//                "Diorite",
//                "Scoria",
//                "Obsidian",

//                "Limestone",
//                "Siltstone",
//                "Coquina",
//                "Shale",
//                "Chalk",
//                "Flint",
//                "Natural Beauty",

//                "Marble",
//                "Slate",
//                "Schist",
//                "Anthracite",
//                "Jade", // crystal

//                "Amethyst",
//                "Jasper",
//                "Fulgurite", // rocks
//                "Topaz",
//                "Lapis Lazuli", // rocks
//                "Diamond",
//                "Pyrite",
//                "Lab-Grown Diamonds",

//                "Emerald",
//                "Aquamarine",
//                "Opal",
//                "Garnet",
//                "Sapphire",
//                "Ruby",
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.candidateApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}

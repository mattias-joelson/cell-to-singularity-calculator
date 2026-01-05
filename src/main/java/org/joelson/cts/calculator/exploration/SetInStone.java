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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static Garden createGarden(int costMultiplier, int productionMultiplier) {
        Garden garden = new Garden("Set in Stone");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier);

        builder.createGenerator("Mineral", minerals(150), 1.15f, minerals(2))
                //.addUpgradeRequirement("Earthly Origins")

                .addUpgrade("Olivine", minerals(400), 1.5f)
                .addGeneratorRequirement("Mineral")

                .addUpgrade("Quartz", minerals(2_500), 1.75f)
                .addGeneratorRequirement("Mineral")

                .addUpgrade("Feldspars", minerals(7_800), 1.5f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Quartz")

                .addUpgrade("Magma", minerals(32_000), 2)
                .addUpgradeRequirement("Quartz")

                .addUpgrade("Tuff", rocks(18_000), 2.25f)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Basalt")

                .addUpgrade("Clay", minerals(900_000), 1.75f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Basalt")

                .addUpgrade("Rock Cycle", minerals(1.7e6), 1.75f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Basalt")

                .addUpgrade("Sand", minerals(4.5e6), 2.5f)
                .addUpgradeRequirement("Rock Cycle")

                .addUpgrade("Sandstone", rocks(2.5e8), 2.25f)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Andesite")

                .addUpgrade("Calcite", minerals(3e8), 3.5f)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Andesite")

                .addUpgrade("Granite", rocks(1e11), 6)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Scoria")

                .addUpgrade("Gneiss", rocks(9e12), 6)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Shale")

                .addUpgrade("Fluorite", minerals(7e10), 11)
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Shale")

                .addUpgrade("Pegmatite", rocks(5e14), 101)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Amethyst")

                .addUpgrade("Coal", rocks(9e16), 101)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Topaz");

        builder.createGenerator("Igneous Rock", minerals(50_000), 1.15f, rocks(1))
                .addUpgradeRequirement("Magma")

                .addUpgrade("Dunite", rocks(1_000), 2)
                .addGeneratorRequirement("Igneous Rock")

                .addUpgrade("Basalt", rocks(5_000), 2)
                .addGeneratorRequirement("Igneous Rock")

                .addUpgrade("Andesite", rocks(3e7), 31)
                .addGeneratorRequirement("Igneous Rock")
                .addGeneratorRequirement("Sedimentary Rock")

                .addUpgrade("Diorite", rocks(3e7), 31)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Andesite")

                .addUpgrade("Scoria", rocks(1e10), 301)
                .addGeneratorRequirement("Igneous Rock")
                .addGeneratorRequirement("Metamorphic Rock")

                .addUpgrade("Obsidian", rocks(6e16), 35_001)
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Topaz");

        builder.createGenerator("Sedimentary Rock", minerals(1e7), 1.15f, rocks(250))
                .addUpgradeRequirement("Sand")

                .addUpgrade("Limestone", rocks(1e6), 1.75f)
                .addGeneratorRequirement("Sedimentary Rock")

                .addUpgrade("Siltstone", rocks(6e6), 2)
                .addGeneratorRequirement("Sedimentary Rock")

                .addUpgrade("Coquina", rocks(3e11), 101)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Scoria")

                .addUpgrade("Shale", rocks(9.5e11), 201)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Scoria")

                .addUpgrade("Chalk", rocks(1e14), 41)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Amethyst")

                .addUpgrade("Flint", rocks(2e15), 41)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Amethyst")
                .addUpgradeRequirement("Jasper")

                .addUpgrade("Natural Beauty", rocks(2.5e21), 2)
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Jade");

        builder.createGenerator("Metamorphic Rock", minerals(3e8), 1.15f, rocks(100_000))
                .addGeneratorRequirement("Igneous Rock")
                .addUpgradeRequirement("Rock Cycle")
                .addGeneratorRequirement("Sedimentary Rock")
                .addUpgradeRequirement("Calcite")

                .addUpgrade("Marble", rocks(9e8), 1.75f)
                .addGeneratorRequirement("Metamorphic Rock")

                .addUpgrade("Slate", rocks(4e9), 2)
                .addGeneratorRequirement("Metamorphic Rock")

                .addUpgrade("Schist", rocks(5e12), 201)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Shale")

                .addUpgrade("Anthracite", rocks(1e17), 15_001)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Coal")

                .addUpgrade("Jade", crystals(1.2e17), 889)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Lab-Grown Diamonds");

        builder.createGenerator("Crystal", minerals(4e11), 1.15f, crystals(1))
                .addGeneratorRequirement("Mineral")
                .addUpgradeRequirement("Fluorite")

                .addUpgrade("Amethyst", crystals(8_000), 2)
                .addGeneratorRequirement("Crystal")

                .addUpgrade("Jasper", crystals(500_000), 6)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Amethyst")

                .addUpgrade("Fulgurite", rocks(1.2e16), 11)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Jasper")

                .addUpgrade("Topaz", crystals(4.5e7), 21)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Jasper")

                .addUpgrade("Lapis Lazuli", rocks(9e17), 31)
                .addGeneratorRequirement("Metamorphic Rock")
                .addUpgradeRequirement("Coal")

                .addUpgrade("Diamond", crystals(1e11), 41)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Coal")

                .addUpgrade("Pyrite", crystals(2.7e13), 21)
                .addGeneratorRequirement("Crystal")
                .addGeneratorRequirement("Gem")

                .addUpgrade("Lab-Grown Diamonds", crystals(1.2e15), 31)
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Pyrite");

        builder.createGenerator("Gem", crystals(3e12), 1.15f, crystals(1e8))
                .addGeneratorRequirement("Crystal")
                .addUpgradeRequirement("Diamond")

                .addUpgrade("Emerald", crystals(3.3e12), 2.5f)
                .addGeneratorRequirement("Gem")

                .addUpgrade("Aquamarine", crystals(6e12), 2.5f)
                .addGeneratorRequirement("Gem")

                .addUpgrade("Opal", crystals(9e13), 3)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Pyrite")

                .addUpgrade("Garnet", crystals(3e14), 3)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Pyrite")

                .addUpgrade("Sapphire", crystals(3e15), 6)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Lab-Grown Diamonds")

                .addUpgrade("Ruby", crystals(1.5e16), 6)
                .addGeneratorRequirement("Gem")
                .addUpgradeRequirement("Lab-Grown Diamonds");

        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1);
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
        printUnlocked(garden, state, actions);
        candidateApproach(garden, state, actions);

        actions.forEach(System.out::println);
    }

    public static void candidateApproach(Garden garden, GardenState state, List<String> actions) {
        boolean possibleUnlock = false;
        for (int i = 0; i < 20 || !possibleUnlock; i += 1) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Map<CurrencyMapping, ImprovementDescription> improvementDescriptions =
                    ImprovementCalculator.calculateImprovement(garden, state);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();

            possibleUnlock = false;
            Map<Improvement, Set<Map.Entry<CurrencyMapping, ImprovementDescription>>> improvementMap = new HashMap<>();
            for (Map.Entry<CurrencyMapping, ImprovementDescription> entry : improvementDescriptions.entrySet()) {
                Improvement improvement = entry.getValue().improvement();
                Set<Map.Entry<CurrencyMapping, ImprovementDescription>> set = improvementMap.get(improvement);
                if (set == null) {
                    set = new HashSet<>();
                    set.add(entry);
                    improvementMap.put(improvement, set);
                } else {
                    set.add(entry);
                }
            }

            for (String fromCurrency : CURRENCIES) {
                for (String toCurrency : CURRENCIES) {
                    CurrencyMapping mapping = new CurrencyMapping(fromCurrency, toCurrency);
                    for (Improvement improvement : improvementMap.keySet()) {
                        if (improvement.getMapping().equals(mapping)) {
                            actions.add(String.format("%s : %s", mapping.asString(), improvement.getName()));
                        }
                    }
                }
            }

            for (Map.Entry<Improvement, Set<Map.Entry<CurrencyMapping, ImprovementDescription>>> entry :
                    improvementMap.entrySet()) {
                Improvement improvement = entry.getKey();
                if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                    int count = generatorState.count();
                    for (Map.Entry<CurrencyMapping, ImprovementDescription> currencyEntry : entry.getValue()) {
                        CurrencyMapping currencyMapping = currencyEntry.getKey();
                        ImprovementDescription improvementDescription = currencyEntry.getValue();
                        actions.add(String.format("(%d - %s) Generator %s: %d -> %d : %s", i + 1,
                                currencyMapping.asString(), generator.getName(), count, count + 1,
                                improvementDescription.description()));

                    }
                    state.setGeneratorCount(generator, count + 1);
                    if (count == 0) {
                        printUnlocked(garden, state, actions);
                        possibleUnlock = true;
                    }
                } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
                    Upgrade upgrade = upgradeImprovement.upgrade();
                    for (Map.Entry<CurrencyMapping, ImprovementDescription> currencyEntry : entry.getValue()) {
                        UpgradeEffect effect = upgrade.getEffects().getFirst();
                        CurrencyMapping currencyMapping = currencyEntry.getKey();
                        ImprovementDescription improvementDescription = currencyEntry.getValue();
                        actions.add(String.format("(%d - %s) Upgrade %s (%s) : %s", i + 1, currencyMapping.asString(),
                                upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                    }
                    state.setUpgradeBought(upgrade);
                    state.updateGeneratorStates(garden);
                    printUnlocked(garden, state, actions);
                    possibleUnlock = true;
                } else {
                    throw new NullPointerException();
                }
                actions.add("");
            }
            if (improvementMap.size() > 1) {
                break;
            }
        }
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

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

public class LurkingInTheDark {

    private static final String CURRENCY = "Nutrients"; // "Nodes"

    private static Amount amount(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Lurking in the Dark");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Sunlight Zone", amount(30), 1.13f, amount(1))

                .addEfficiencyUpgrade("Microscopic Plants", amount(75), 1.10f)
                .addGeneratorRequirement("Sunlight Zone")

                .addEfficiencyUpgrade("Global Drifters", amount(250), 1.50f)
                .addUpgradeRequirement("Microscopic Plants")

                .addEfficiencyUpgrade("Speedy Swimmers", amount(2_000), 1.25f)
                .addUpgradeRequirement("Global Drifters")

                .addEfficiencyUpgrade("Whale Power", amount(12_000), 1.25f)
                .addUpgradeRequirement("Speedy Swimmers")

                .addEfficiencyUpgrade("Sinking Detritus", amount(30_000), 3)
                .addUpgradeRequirement("Whale Power")

                .addEfficiencyUpgrade("Marine Snow", amount(4e9), 10_001)
                .addGeneratorRequirement("Twilight Zone")

                .addEfficiencyUpgrade("Snot Palace", amount(3e11), 51)
                .addUpgradeRequirement("Marine Snow")

                .addEfficiencyUpgrade("Nightly Migrations", amount(2e13), 1_001)
                .addGeneratorRequirement("Midnight Zone")

                .addEfficiencyUpgrade("Slow Living", amount(8e15), 41)
                .addGeneratorRequirement("Midnight Zone")

                .addEfficiencyUpgrade("Whale Fall", amount(1.25e21), 100_001)
                .addUpgradeRequirement("Benthic Desert")

                .addEfficiencyUpgrade("Upwelling", amount(1.75e26), 3)
                .addUpgradeRequirement("Briny Death Traps")

                .addEfficiencyUpgrade("Nutrient Express", amount(4e26), 3)
                .addUpgradeRequirement("Upwelling")

                .addEfficiencyUpgrade("Climate Control", amount(1.25e27), 3)
                .addUpgradeRequirement("Nutrient Express")

                .addEfficiencyUpgrade("Earth's Lifeline", amount(8e27), 1e7f)
                .addUpgradeRequirement("Climate Control");

        builder.createGenerator("Twilight Zone", amount(150_000), 1.11f, amount(150))
                .addUpgradeRequirement("Sinking Detritus")

                .addEfficiencyUpgrade("Fish World", amount(300_000), 2)
                .addGeneratorRequirement("Twilight Zone")

                .addEfficiencyUpgrade("Making Light", amount(2.15e6), 2)
                .addGeneratorRequirement("Twilight Zone")

                .addEfficiencyUpgrade("Seeing Blue", amount(8e6), 2)
                .addGeneratorRequirement("Twilight Zone")

                .addEfficiencyUpgrade("Red is the New Black", amount(3.6e7), 2.5f)
                .addUpgradeRequirement("Seeing Blue")

                .addEfficiencyUpgrade("See-Through Bodies", amount(1.15e8), 2.5f)
                .addUpgradeRequirement("Red is the New Black")

                .addEfficiencyUpgrade("Oxygen Exploit", amount(2e9), 3)
                .addGeneratorRequirement("Twilight Zone")

                .addEfficiencyUpgrade("Light as a Lure", amount(1.75e10), 2)
                .addUpgradeRequirement("Making Light")

                .addEfficiencyUpgrade("Lurking Champion", amount(2.5e10), 2.5f)
                .addUpgradeRequirement("Making Light")

                .addEfficiencyUpgrade("Hiding in Light", amount(7e10), 2)
                .addUpgradeRequirement("Light as a Lure")
                .addUpgradeRequirement("Lurking Champion")

                .addEfficiencyUpgrade("Alarms and Flash Bangs", amount(9e10), 3)
                .addUpgradeRequirement("Light as a Lure")
                .addUpgradeRequirement("Lurking Champion")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Snot Palace", 2)
                .addEfficiencyEffect("Climate Control", 1e16f);

        builder.createGenerator("Midnight Zone", amount(1e12), amount(3e7))
                .addUpgradeRequirement("Snot Palace")

                .addEfficiencyUpgrade("Mammal Limit", amount(3e12), 3)
                .addGeneratorRequirement("Midnight Zone")

                .addEfficiencyUpgrade("Under Pressure", amount(3e12), 2.75f)
                .addUpgradeRequirement("Mammal Limit")

                .addEfficiencyUpgrade("Giant Eyes", amount(6e12), 2.75f)
                .addGeneratorRequirement("Midnight Zone")

                .addEfficiencyUpgrade("The Biggest Gulp", amount(1e15), 2)
                .addUpgradeRequirement("Giant Eyes")

                .addEfficiencyUpgrade("Giant Teeth", amount(6e15), 3)
                .addUpgradeRequirement("The Biggest Gulp")

                .addEfficiencyUpgrade("Extreme Mating", amount(9e16), 3)
                .addGeneratorRequirement("Midnight Zone")

                .addEfficiencyUpgrade("Collective Living", amount(2e17), 3)
                .addUpgradeRequirement("Extreme Mating")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Nutrient Express", 1e10f);

        builder.createGenerator("The Abyss", amount(2e17), 1.12f, amount(5e12))
                .addUpgradeRequirement("Collective Living")

                .addEfficiencyUpgrade("Smell and Touch", amount(3e17), 4)
                .addGeneratorRequirement("The Abyss")

                .addEfficiencyUpgrade("Electrical Sensors", amount(5e17), 4)
                .addUpgradeRequirement("Smell and Touch")

                .addEfficiencyUpgrade("Benthic Desert", amount(2.5e17), 3)
                .addGeneratorRequirement("The Abyss")

                .addEfficiencyUpgrade("A Gathering Herd", amount(6e18), 3.25f)
                .addUpgradeRequirement("Benthic Desert")

                .addEfficiencyUpgrade("Extreme Species", amount(6.5e19), 3.5f)
                .addGeneratorRequirement("The Abyss")

                .addEfficiencyUpgrade("Lonesome Predator", amount(4e20), 2.75f)
                .addUpgradeRequirement("Extreme Species")

                .withUpgradeEffectBuilder()
                .addEfficiencyEffect("Whale Fall", 4)
                .addEfficiencyEffect("Upwelling", 10_001);

        builder.createGenerator("The Trenches", amount(1e22), amount(5e17))
                .addUpgradeRequirement("Whale Fall")

                .addEfficiencyUpgrade("Hadal Extremes", amount(3.5e22), 5)
                .addGeneratorRequirement("The Trenches")

                .addEfficiencyUpgrade("Hydrothermal Vents", amount(5e22), 4)
                .addGeneratorRequirement("The Trenches")

                .addEfficiencyUpgrade("Chemical Ecosystem", amount(2.5e23), 4.75f)
                .addUpgradeRequirement("Hydrothermal Vents")

                .addEfficiencyUpgrade("Cold Seeps", amount(3e24), 4)
                .addGeneratorRequirement("The Trenches")

                .addEfficiencyUpgrade("Briny Death Traps", amount(2.5e25), 3)
                .addGeneratorRequirement("The Trenches");

        builder.resolveRequirements();

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

        setGeneratorCount(garden, state, "The Trenches", 0);
        setGeneratorCount(garden, state, "The Abyss", 0);
        setGeneratorCount(garden, state, "Midnight Zone", 0);
        setGeneratorCount(garden, state, "Twilight Zone", 0);
        setGeneratorCount(garden, state, "Sunlight Zone", 1);

        String[] boughtUpdates = {
//                "Microscopic Plants",
//                "Global Drifters",
//                "Speedy Swimmers",
//                "Whale Power",
//                "Sinking Detritus",
//                "Marine Snow",
//                "Snot Palace",
//                "Nightly Migrations",
//                "Slow Living",
//                "Whale Fall",
//                "Upwelling",
//                "Nutrient Express",
//                "Climate Control",
//                "Earth's Lifeline",

//                "Fish World",
//                "Making Light",
//                "Seeing Blue",
//                "Red is the New Black",
//                "See-Through Bodies",
//                "Oxygen Exploit",
//                "Light as a Lure",
//                "Lurking Champion",
//                "Hiding in Light",
//                "Alarms and Flash Bangs",

//                "Mammal Limit",
//                "Under Pressure",
//                "Giant Eyes",
//                "The Biggest Gulp",
//                "Giant Teeth",
//                "Extreme Mating",
//                "Collective Living",

//                "Smell and Touch",
//                "Electrical Sensors",
//                "Benthic Desert",
//                "A Gathering Herd",
//                "Extreme Species",
//                "Lonesome Predator",

//                "Hadal Extremes",
//                "Hydrothermal Vents",
//                "Chemical Ecosystem",
//                "Cold Seeps",
//                "Briny Death Traps",
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

package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class AFelineJourney {

    private static final String CURRENCY = "Paws";

    private static Amount paws(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("A Feline Journey");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Felidae", paws(30), 1.11f, paws(1))

                .addEfficiencyUpgrade("Pantherinae", paws(100), 1.75f)
                .addGeneratorRequirement("Felidae")

                .addEfficiencyUpgrade("Felinae", paws(750), 1.75f)
                .addUpgradeRequirement("Pantherinae")

                .addEfficiencyUpgrade("Digitigrade", paws(3_000), 1.5f)
                .addUpgradeRequirement("Felinae")

                .addEfficiencyUpgrade("Claws", paws(3e7), 501)
                .addUpgradeRequirement("Digitigrade")

                .addEfficiencyUpgrade("Acute Senses", paws(8e9), 16)
                .addUpgradeRequirement("Claws")

                .addEfficiencyUpgrade("Flexibility", paws(6e12), 251)
                .addUpgradeRequirement("Acute Senses")

                .addEfficiencyUpgrade("Patterned Coats", paws(8e15), 1251)
                .addUpgradeRequirement("Flexibility")

                .addEfficiencyUpgrade("Rough Tongue", paws(8e17), 101)
                .addUpgradeRequirement("Patterned Coats")

                .addEfficiencyUpgrade("Short Skull", paws(2.5e20), 101)
                .addUpgradeRequirement("Rough Tongue")

                .addEfficiencyUpgrade("Obligate Carnivores", paws(3e23), 2501)
                .addUpgradeRequirement("Short Skull")

                .addEfficiencyUpgrade("Whiskers", paws(2.43e26), 501)
                .addUpgradeRequirement("Obligate Carnivores");

        builder.createGenerator("Panthera", paws(30000), paws(200))
                .addUpgradeRequirement("Digitigrade")

                .addEfficiencyUpgrade("Clouded Leopard", paws(80_000), 2)
                .addGeneratorRequirement("Panthera")

                .addEfficiencyUpgrade("Tiger", paws(240_000), 1.75f)
                .addUpgradeRequirement("Clouded Leopard")

                .addEfficiencyUpgrade("Leopard", paws(720_000), 1.5f)
                .addUpgradeRequirement("Tiger")

                .addEfficiencyUpgrade("Snow Leopard", paws(2.16e6), 2)
                .addUpgradeRequirement("Leopard")

                .addEfficiencyUpgrade("Sunda Clouded Leopard", paws(2.7e9), 41)
                .addUpgradeRequirement("Asian Golden Cat")

                .addEfficiencyUpgrade("Lion", paws(1.5e12), 101)
                .addUpgradeRequirement("African Caracal")

                .addEfficiencyUpgrade("Jaguar", paws(9e19), 1e7f + 1)
                .addUpgradeRequirement("Cougar");

        builder.createGenerator("Bay Cat", paws(3e7), paws(40_000))
                .addUpgradeRequirement("Snow Leopard")

                .addEfficiencyUpgrade("Bornean Bay Cat", paws(1e8), 1.5f)
                .addGeneratorRequirement("Bay Cat")

                .addEfficiencyUpgrade("Asian Golden Cat", paws(3e8), 2f)
                .addGeneratorRequirement("Bay Cat")

                .addEfficiencyUpgrade("Marbled Cat", paws(9e8), 1.75f)
                .addUpgradeRequirement("Asian Golden Cat");

        builder.createGenerator("Caracal", paws(3e10), paws(1e7))
                .addUpgradeRequirement("Marbled Cat")

                .addEfficiencyUpgrade("African Caracal", paws(9e10), 1.75f)
                .addGeneratorRequirement("Caracal")

                .addEfficiencyUpgrade("Serval", paws(2.7e11), 2)
                .addUpgradeRequirement("African Caracal");

        builder.createGenerator("Ocelot", paws(1e13), paws(2.5e9))
                .addUpgradeRequirement("Serval")

                .addEfficiencyUpgrade("Ocelots", paws(7e13), 2.25f)
                .addGeneratorRequirement("Ocelot", 1)

                .addEfficiencyUpgrade("Kodkod", paws(2e14), 2)
                .addGeneratorRequirement("Ocelot")

                .addEfficiencyUpgrade("Andean Mountain Cat", paws(6e14), 1.75f)
                .addUpgradeRequirement("Ocelots")

                .addEfficiencyUpgrade("Geoffroy's Cat", paws(2e15), 1.5f)
                .addUpgradeRequirement("Kodkod")

                .addEfficiencyUpgrade("Margay", paws(4e15), 2)
                .addUpgradeRequirement("Kodkod");

        builder.createGenerator("Lynx", paws(1e16), paws(8e11))
                .addUpgradeRequirement("Geoffroy's Cat")

                .addEfficiencyUpgrade("Eurasian Lynx", paws(5e16), 3)
                .addGeneratorRequirement("Lynx")

                .addEfficiencyUpgrade("Iberian Lynx", paws(1.5e17), 2.25f)
                .addGeneratorRequirement("Lynx")

                .addEfficiencyUpgrade("Bobcat", paws(3e17), 2.5f)
                .addUpgradeRequirement("Eurasian Lynx");

        builder.createGenerator("Puma", paws(1e18), paws(1.5e14))
                .addUpgradeRequirement("Iberian Lynx")

                .addEfficiencyUpgrade("Cougar", paws(9e18), 2.75f)
                .addGeneratorRequirement("Puma")

                .addEfficiencyUpgrade("Cheetah", paws(3e19), 2)
                .addGeneratorRequirement("Puma");

        builder.createGenerator("Leopard Cat", paws(5e20), paws(4e16))
                .addUpgradeRequirement("Cheetah")

                .addEfficiencyUpgrade("Leopard Cats", paws(1e21), 2.25f)
                .addGeneratorRequirement("Leopard Cat")

                .addEfficiencyUpgrade("Sunda Leopard Cat", paws(3e21), 2.25f)
                .addGeneratorRequirement("Leopard Cat")

                .addEfficiencyUpgrade("Fishing Cat", paws(9e21), 2)
                .addUpgradeRequirement("Leopard Cats")

                .addEfficiencyUpgrade("Flat-Headed Cat", paws(2.7e22), 2.25f)
                .addUpgradeRequirement("Sunda Leopard Cat")

                .addEfficiencyUpgrade("Rusty-Spotted Cat", paws(8.1e22), 2)
                .addUpgradeRequirement("Sunda Leopard Cat")

                .addEfficiencyUpgrade("Pallas' Cat", paws(2.7e25), 151)
                .addGeneratorRequirement("Leopard Cat")
                .addUpgradeRequirement("Jungle Cat");

        builder.createGenerator("Felis", paws(5e23), paws(2e19))
                .addUpgradeRequirement("Flat-Headed Cat")

                .addEfficiencyUpgrade("Jungle Cat", paws(1e24), 3.5f)
                .addGeneratorRequirement("Felis")

                .addEfficiencyUpgrade("Black-Footed Cat", paws(3e24), 2f)
                .addGeneratorRequirement("Felis")

                .addEfficiencyUpgrade("Sand Cat", paws(9e24), 2)
                .addUpgradeRequirement("Jungle Cat")

                .addEfficiencyUpgrade("African Wildcat", paws(8.1e25), 3.5f)
                .addUpgradeRequirement("Sand Cat");

        builder.createGenerator("House Cats", paws(3e26), paws(5e21))
                .addUpgradeRequirement("Pallas' Cat")
                .addUpgradeRequirement("African Wildcat")

                .addEfficiencyUpgrade("Fluffy", paws(6e26), 3)
                .addGeneratorRequirement("House Cats")

                .addEfficiencyUpgrade("Australian Cats", paws(5e26), 1)
                .addUpgradeRequirement("Fluffy")

                .addEfficiencyUpgrade("Hairless", paws(1.5e27), 5)
                .addUpgradeRequirement("Fluffy")

                .addEfficiencyUpgrade("Orange", paws(7e27), 2)
                .addUpgradeRequirement("Hairless")

                .addEfficiencyUpgrade("Hunter at Heart", paws(2.5e28), 2)
                .addUpgradeRequirement("Orange");

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "House Cats", 0);
        setGeneratorCount(garden, state, "Felis", 0);
        setGeneratorCount(garden, state, "Leopard Cat", 0);
        setGeneratorCount(garden, state, "Puma", 0);
        setGeneratorCount(garden, state, "Lynx", 0);
        setGeneratorCount(garden, state, "Ocelot", 0);
        setGeneratorCount(garden, state, "Caracal", 0);
        setGeneratorCount(garden, state, "Bay Cat", 0);
        setGeneratorCount(garden, state, "Panthera", 0);
        setGeneratorCount(garden, state, "Felidae", 1);

        String[] boughtUpdates = {
//                "Pantherinae",
//                "Felinae",
//                "Digitigrade",
//                "Claws",
//                "Acute Senses",
//                "Flexibility",
//                "Patterned Coats",
//                "Rough Tongue",
//                "Short Skull",
//                "Obligate Carnivores",
//                "Whiskers",

//                "Clouded Leopard",
//                "Tiger",
//                "Leopard",
//                "Snow Leopard",
//                "Sunda Clouded Leopard",
//                "Lion",
//                "Jaguar",

//                "Bornean Bay Cat",
//                "Asian Golden Cat",
//                "Marbled Cat",

//                "African Caracal",
//                "Serval",

//                "Ocelots",
//                "Kodkod",
//                "Andean Mountain Cat",
//                "Geoffroy's Cat",
//                "Margay",

//                "Eurasian Lynx",
//                "Iberian Lynx",
//                "Bobcat",

//                "Cougar",
//                "Cheetah",

//                "Leopard Cats",
//                "Sunda Leopard Cat",
//                "Fishing Cat",
//                "Flat-Headed Cat",
//                "Rusty-Spotted Cat",
//                "Pallas' Cat",

//                "Jungle Cat",
//                "Black-Footed Cat",
//                "Sand Cat",
//                "African Wildcat",

//                "Fluffy",
//                "Australian Cats",
//                "Hairless",
//                "Orange",
//                "Hunter at Heart",
        };

        for (String upgradeName : boughtUpdates) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.singleCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}

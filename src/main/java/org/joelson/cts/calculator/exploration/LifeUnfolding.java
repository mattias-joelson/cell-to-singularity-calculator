package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class LifeUnfolding {

    private static final String NUTRITION_CURRENCY = "Nutrition";
    private static final String PUSH_CURRENCY = "PUSH!";

    private static Amount nutrition(double amount) {
        return new Amount(NUTRITION_CURRENCY, amount);
    }

    private static Amount push(double amount) {
        return new Amount(PUSH_CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Life Unfolding", LifeUnfolding::alterGarden);
        garden.addCurrency(NUTRITION_CURRENCY);
        garden.addCurrency(PUSH_CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Mother", nutrition(100), nutrition(1))

                .addEfficiencyUpgrade("Reproductive System", nutrition(300), 1.25f)
                .addGeneratorRequirement("Mother")

                .addEfficiencyUpgrade("Egg", nutrition(600), 1.25f)
                .addUpgradeRequirement("Reproductive System")

                .addEfficiencyUpgrade("Sperm", nutrition(950), 2)
                .addUpgradeRequirement("Egg")

                .addEfficiencyUpgrade("Fertilization", nutrition(3_000), 2.5f)
                .addUpgradeRequirement("Egg")
                .addUpgradeRequirement("Sperm")

                .addEfficiencyUpgrade("Diet Limits", nutrition(570_000), 5)
                .addGeneratorRequirement("Mother")
                .addUpgradeRequirement("Zygote")

                .addEfficiencyUpgrade("Confirmation", nutrition(8.8e8), 500)
                .addGeneratorRequirement("Mother")
                .addUpgradeRequirement("Placenta")

                .addEfficiencyUpgrade("Morning Sickness", nutrition(1.7e13), 10_000)
                .addGeneratorRequirement("Mother")
                .addUpgradeRequirement("Umbilical Cord")

                .addEfficiencyUpgrade("Cravings", nutrition(4e16), 1_000)
                .addGeneratorRequirement("Mother")
                .addUpgradeRequirement("Amniotic Fluid")

                .addEfficiencyUpgrade("Milk Glands", nutrition(2.5e20), 5_000)
                .addGeneratorRequirement("Mother")
                .addUpgradeRequirement("Rudimentary Senses")

                .addEfficiencyUpgrade("False Labor", nutrition(1e34), 3e13f)
                .addGeneratorRequirement("Mother")
                .addUpgradeRequirement("Independence")

                .addEfficiencyUpgrade("Labor", nutrition(8.7e40), 3_000_000)
                .addUpgradeRequirement("Lightening")

                .addEfficiencyUpgrade("Water Breaking", nutrition(8e41), 10)
                .addUpgradeRequirement("Labor")

                .addEfficiencyUpgrade("Dilation", nutrition(9.4e42), 11)
                .addUpgradeRequirement("Water Breaking")

                .addEfficiencyUpgrade("Afterbirth", push(100), 1.5f)
                .addUpgradeRequirement("Birth");

        builder.createGenerator("Month One", nutrition(10_000), nutrition(10))
                .addUpgradeRequirement("Fertilization")

                .addEfficiencyUpgrade("Zygote", nutrition(38_000), 2)
                .addGeneratorRequirement("Month One")

                .addEfficiencyUpgrade("Multiples", nutrition(145_000), 2.5f)
                .addUpgradeRequirement("Zygote")

                .addEfficiencyUpgrade("Blastocyst", nutrition(2.3e6), 3)
                .addUpgradeRequirement("Zygote")

                .addEfficiencyUpgrade("Embryo", nutrition(9.6e6), 3.5f)
                .addUpgradeRequirement("Blastocyst")

                .addEfficiencyUpgrade("Placenta", nutrition(4.2e7), 4)
                .addUpgradeRequirement("Blastocyst")
                .addUpgradeRequirement("Embryo")

                .addEfficiencyUpgrade("Amniotic Sac", nutrition(1.9e8), 4.5f)
                .addUpgradeRequirement("Embryo")
                .addUpgradeRequirement("Placenta");

        builder.createGenerator("Month Two", nutrition(5e9), nutrition(5.6e6))
                .addUpgradeRequirement("Amniotic Sac")

                .addEfficiencyUpgrade("Neural Tube", nutrition(4.2e10), 1.5f)
                .addGeneratorRequirement("Month Two")

                .addEfficiencyUpgrade("Heart Tube", nutrition(2.1e11), 2)
                .addGeneratorRequirement("Month Two")

                .addEfficiencyUpgrade("The Body Forms", nutrition(1.1e12), 2.5f)
                .addUpgradeRequirement("Neural Tube")
                .addUpgradeRequirement("Heart Tube")

                .addEfficiencyUpgrade("Umbilical Cord", nutrition(5.6e11), 3)
                .addUpgradeRequirement("The Body Forms");

        builder.createGenerator("Month Three", nutrition(1e14), nutrition(1e11))
                .addUpgradeRequirement("Umbilical Cord")

                .addEfficiencyUpgrade("Fetus", nutrition(1e15), 1.5f)
                .addGeneratorRequirement("Month Three")

                .addEfficiencyUpgrade("Amniotic Fluid", nutrition(8e15), 2)
                .addUpgradeRequirement("Fetus");

        builder.createGenerator("Month Four", nutrition(1e17), nutrition(1e14))
                .addUpgradeRequirement("Amniotic Fluid")

                .addEfficiencyUpgrade("Reproductive Organs", nutrition(8.4e17), 1.5f)
                .addGeneratorRequirement("Month Four")

                .addEfficiencyUpgrade("Organ Function", nutrition(5.5e18), 2)
                .addGeneratorRequirement("Month Four")
                .addUpgradeRequirement("Reproductive Organs")

                .addEfficiencyUpgrade("Rudimentary Senses", nutrition(3.7e19), 4)
                .addGeneratorRequirement("Month Four")
                .addUpgradeRequirement("Reproductive Organs");

        builder.createGenerator("Month Five", nutrition(1e21), nutrition(1e18))
                .addUpgradeRequirement("Rudimentary Senses")

                .addEfficiencyUpgrade("Fetal Skin", nutrition(1.3e22), 1.5f)
                .addGeneratorRequirement("Month Five")

                .addEfficiencyUpgrade("Kicks and Punches", nutrition(1e23), 3.5f)
                .addGeneratorRequirement("Month Five")
                .addUpgradeRequirement("Fetal Skin");

        builder.createGenerator("Month Six", nutrition(1e24), nutrition(1e21))
                .addUpgradeRequirement("Kicks and Punches")

                .addEfficiencyUpgrade("Bone Marrow", nutrition(9.5e24), 1.5f)
                .addGeneratorRequirement("Month Six")

                .addEfficiencyUpgrade("Hearing", nutrition(8.3e25), 2.5f)
                .addGeneratorRequirement("Month Six")
                .addUpgradeRequirement("Bone Marrow")

                .addEfficiencyUpgrade("Lungs", nutrition(3.4e26), 3)
                .addGeneratorRequirement("Month Six")
                .addUpgradeRequirement("Hearing")

                .addEfficiencyUpgrade("Pre-Term Birth", nutrition(2.8e27), 3.5f)
                .addGeneratorRequirement("Month Six")
                .addUpgradeRequirement("Lungs");

        builder.createGenerator("Month Seven", nutrition(1e28), nutrition(1e25))
                .addUpgradeRequirement("Pre-Term Birth")

                .addEfficiencyUpgrade("Opaque Skin", nutrition(1.9e29), 1.5f)
                .addGeneratorRequirement("Month Seven")

                .addEfficiencyUpgrade("Blinking", nutrition(1.6e30), 3)
                .addGeneratorRequirement("Month Six")
                .addUpgradeRequirement("Opaque Skin");

        builder.createGenerator("Month Eight", nutrition(1e31), nutrition(1e28))
                .addUpgradeRequirement("Blinking")

                .addEfficiencyUpgrade("Brain", nutrition(1.3e32), 1.5f)
                .addGeneratorRequirement("Month Eight")

                .addEfficiencyUpgrade("Independence", nutrition(1.1e33), 3)
                .addGeneratorRequirement("Month Six")
                .addUpgradeRequirement("Brain");

        builder.createGenerator("Month Nine", nutrition(1e35), nutrition(1e32))
                .addUpgradeRequirement("Independence")

                .addEfficiencyUpgrade("Fluid Balance", nutrition(1.8e36), 1.5f)
                .addGeneratorRequirement("Month Nine")

                .addEfficiencyUpgrade("Cranial Plate", nutrition(1e37), 3.5f)
                .addGeneratorRequirement("Month Nine")
                .addUpgradeRequirement("Fluid Balance")

                .addEfficiencyUpgrade("Hair", nutrition(9e37), 5)
                .addGeneratorRequirement("Month Nine")
                .addUpgradeRequirement("Cranial Plate")

                .addEfficiencyUpgrade("Immune System", nutrition(8e38), 6)
                .addGeneratorRequirement("Month Nine")
                .addUpgradeRequirement("Hair")

                .addEfficiencyUpgrade("Lightening", nutrition(8.5e39), 7)
                .addGeneratorRequirement("Month Nine")
                .addUpgradeRequirement("Immune System")

                .addEfficiencyUpgrade("Expulsion", nutrition(1.1e44), 1)
                .addUpgradeRequirement("Dilation")

                .addEfficiencyUpgrade("Birth", push(500), 1)
                .addUpgradeRequirement("Expulsion");

        builder.resolveRequirements();

        return garden;
    }

    private static void alterGarden(Garden garden, GardenState state) {
        Generator motherGenerator = garden.getGenerator("Mother");
        boolean expulsionBought = isExpulsionBought(garden, state);
        if (expulsionBought && motherGenerator.getBaseProduction().equals(nutrition(1))) {
            for (Generator generator : garden.getGenerators()) {
                Generator newGenerator = new Generator(generator.getName(), generator.getBaseCost(),
                        generator.getCompoundingCost(), nutrition(0));
                garden.swapGenerators(generator, newGenerator);
            }
        }
    }

    private static boolean isExpulsionBought(Garden garden, GardenState state) {
        for (Upgrade upgrade : garden.getUpgrades()) {
            if (upgrade.getName().equals("Expulsion")) {
                return state.isUpgradeBought(upgrade);
            }
        }
        return false;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Month Nine", 0);
        setGeneratorCount(garden, state, "Month Eight", 0);
        setGeneratorCount(garden, state, "Month Seven", 0);
        setGeneratorCount(garden, state, "Month Six", 0);
        setGeneratorCount(garden, state, "Month Five", 0);
        setGeneratorCount(garden, state, "Month Four", 0);
        setGeneratorCount(garden, state, "Month Three", 0);
        setGeneratorCount(garden, state, "Month Two", 0);
        setGeneratorCount(garden, state, "Month One", 0);
        setGeneratorCount(garden, state, "Mother", 1);

        String[] boughtUpgrades = {
//                "Reproductive System", // check
//                "Egg", // check
//                "Sperm", // check
//                "Fertilization", // check
//                "Diet Limits", // check
//                "Confirmation", // check
//                "Morning Sickness", // check
//                "Cravings", // check
//                "Milk Glands", // check
//                "False Labor", // check
//                "Labor", // check
//                "Water Breaking", // check
//                "Dilation", // check
//                "Afterbirth", // push! // check

//                "Zygote", // check
//                "Multiples", // check
//                "Blastocyst", // check
//                "Embryo", // check
//                "Placenta", // check
//                "Amniotic Sac", // check

//                "Neural Tube", // check
//                "Heart Tube", // check
//                "The Body Forms", // check
//                "Umbilical Cord", // check

//                "Fetus", // check
//                "Amniotic Fluid", // check

//                "Reproductive Organs", // check
//                "Organ Function", // check
//                "Rudimentary Senses", // check

//                "Fetal Skin", // check
//                "Kicks and Punches", // check

//                "Bone Marrow", // check
//                "Hearing", // check
//                "Lungs", // check
//                "Pre-Term Birth", // check

//                "Opaque Skin", // check
//                "Blinking", // check

//                "Brain", // check
//                "Independence", // check

//                "Fluid Balance", // check
//                "Cranial Plate", // check
//                "Hair", // check
//                "Immune System", // check
//                "Lightening", // check
//                "Expulsion", // check
//                "Birth", // push! // check
        };

        for (String upgradeName : boughtUpgrades) {
            state.setUpgradeBought(garden.getUpgrade(upgradeName));
        }
        state.updateGeneratorStates(garden);

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.multiCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}

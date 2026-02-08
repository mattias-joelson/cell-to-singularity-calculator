package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class AnatomyOfLife {

    private static final String DEOXYGENATED_BLOOD_CURRENCY = "Deoxygenated Blood";
    private static final String OXYGENATED_BLOOD_CURRENCY = "Oxygenated Blood";

    private static final String[] CURRENCIES = { DEOXYGENATED_BLOOD_CURRENCY, OXYGENATED_BLOOD_CURRENCY };

    private static Amount deoxy(double amount) {
        return new Amount(DEOXYGENATED_BLOOD_CURRENCY, amount);
    }

    private static Amount oxy(double amount) {
        return new Amount(OXYGENATED_BLOOD_CURRENCY, amount);
    }

    private void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("Anatomy of Life");
        for (String currency : CURRENCIES) {
            garden.addCurrency(currency);
        }

        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Heart", deoxy(100), oxy(1))
//                .addUpgradeRequirement("Blood")
//                .addUpgradeRequirement("Veins")

                .addEfficiencyUpgrade("Spinal Cord", oxy(300), 2)
                .addGeneratorRequirement("Heart")

                .addEfficiencyUpgrade("Nerves", oxy(500), 2)
                .addUpgradeRequirement("Spinal Cord")

                .addEfficiencyUpgrade("Brain Stem", oxy(1_000), 2)
                .addUpgradeRequirement("Spinal Cord")

                .addEfficiencyUpgrade("Right Atrium", deoxy(4e6), 126)
                .addUpgradeRequirement("Temporal Lobe")

                .addEfficiencyUpgrade("Right Ventricle", deoxy(1e9), 76)
                .addUpgradeRequirement("Right Atrium")

                .addEfficiencyUpgrade("Digestion", deoxy(1.5e21), 2.5e7f)
                .addUpgradeRequirement("Mouth")

                .addEfficiencyUpgrade("Left Atrium", deoxy(4e23), 501)
                .addGeneratorRequirement("Lungs")
                .addUpgradeRequirement("Smooth Muscles")

                .addEfficiencyUpgrade("Left Ventricle", deoxy(3e25), 2_001)
                .addUpgradeRequirement("Left Atrium");

        builder.createGenerator("Brain", oxy(1_500), deoxy(1))
                .addUpgradeRequirement("Brain Stem")

                .addEfficiencyUpgrade("Frontal Lobe", deoxy(2_000), 2)
                .addGeneratorRequirement("Brain")

                .addEfficiencyUpgrade("Occipital Lobe", deoxy(5_000), 2)
                .addGeneratorRequirement("Brain")

                .addEfficiencyUpgrade("Eyes", deoxy(25_000), 1.75f)
                .addUpgradeRequirement("Occipital Lobe")

                .addEfficiencyUpgrade("Temporal Lobe", deoxy(90_000), 2)
                .addGeneratorRequirement("Brain")

                .addEfficiencyUpgrade("Parietal Lobe", deoxy(750_000), 2.25f)
                .addGeneratorRequirement("Brain")

                .addEfficiencyUpgrade("Nose", deoxy(1e13), 1.5e6f)
                .addUpgradeRequirement("Temporal Lobe")
                .addUpgradeRequirement("Bronchi")

                .addEfficiencyUpgrade("Ears", deoxy(7.5e13), 3.5f)
                .addUpgradeRequirement("Temporal Lobe")
                .addUpgradeRequirement("Bronchi")

                .addEfficiencyUpgrade("Mouth", deoxy(3e20), 5e6f)
                .addUpgradeRequirement("Parietal Lobe")
                .addUpgradeRequirement("Epidermis");

        builder.createGenerator("Endocrine System", oxy(7.5e6), deoxy(120))
                .addUpgradeRequirement("Parietal Lobe")

                .addEfficiencyUpgrade("Hypothalamus", deoxy(1.2e7), 2)
                .addGeneratorRequirement("Endocrine System")

                .addEfficiencyUpgrade("Pituitary", deoxy(2.6e7), 2.5f)
                .addUpgradeRequirement("Hypothalamus")

                .addEfficiencyUpgrade("Adrenal", deoxy(6e7), 2.5f)
                .addGeneratorRequirement("Endocrine System")

                .addEfficiencyUpgrade("Gonads", deoxy(2e8), 4)
                .addUpgradeRequirement("Adrenal")

                .addEfficiencyUpgrade("Pineal", deoxy(5e18), 7.5e9f)
                .addGeneratorRequirement("Endocrine System")
                .addGeneratorRequirement("Skin")

                .addEfficiencyUpgrade("Thyroid", deoxy(7.5e19), 5)
                .addUpgradeRequirement("Pituitary")
                .addGeneratorRequirement("Skin");

        builder.createGenerator("Skeleton", oxy(8e9), deoxy(250_000))
                .addUpgradeRequirement("Hypothalamus")

                .addEfficiencyUpgrade("Bones", deoxy(1.5e10), 6)
                .addGeneratorRequirement("Skeleton")

                .addEfficiencyUpgrade("Bone Marrow", deoxy(3e11), 11)
                .addUpgradeRequirement("Bones")

                .addEfficiencyUpgrade("Cartilage", deoxy(2e14), 41)
                .addUpgradeRequirement("Bones")

                .addEfficiencyUpgrade("Joints", deoxy(4e14), 2.5f)
                .addUpgradeRequirement("Cartilage");

        builder.createGenerator("Muscles", oxy(1e11), deoxy(1e7))
                .addUpgradeRequirement("Bones")

                .addEfficiencyUpgrade("Skeletal Muscles", deoxy(2e12), 26)
                .addGeneratorRequirement("Muscles")

                .addEfficiencyUpgrade("Tendons", deoxy(8e14), 31)
                .addUpgradeRequirement("Skeletal Muscles")

                .addEfficiencyUpgrade("Smooth Muscles", deoxy(4e21), 2.5e6f)
                .addGeneratorRequirement("Muscles")
                .addGeneratorRequirement("Stomach");

        builder.createGenerator("Lungs", deoxy(1e13), oxy(5.06e8))
                .addUpgradeRequirement("Skeletal Muscles")

                .addEfficiencyUpgrade("Bronchi", deoxy(8e12), 3)
                .addGeneratorRequirement("Lungs")

                .addEfficiencyUpgrade("Aveoli", deoxy(4e15), 51)
                .addUpgradeRequirement("Bronchi");

        builder.createGenerator("Skin", oxy(1e16), deoxy(3e12))
                .addGeneratorRequirement("Lungs")

                .addEfficiencyUpgrade("Epidermis", deoxy(5e16), 2)
                .addGeneratorRequirement("Skin")

                .addEfficiencyUpgrade("Dermis", deoxy(3e17), 2)
                .addGeneratorRequirement("Skin")

                .addEfficiencyUpgrade("Hypodermis", deoxy(8e17), 4)
                .addGeneratorRequirement("Skin");

        builder.createGenerator("Stomach", oxy(2e18), deoxy(1.5e17))
                .addUpgradeRequirement("Digestion")

                .addEfficiencyUpgrade("Small Intestine", deoxy(1.5e22), 7)
                .addGeneratorRequirement("Stomach")

                .addEfficiencyUpgrade("Large Intestine", deoxy(2e22), 8)
                .addUpgradeRequirement("Small Intestine");

        builder.createGenerator("Kidneys", oxy(2.5e20), deoxy(2.5e19))
                .addUpgradeRequirement("Digestion")

                .addEfficiencyUpgrade("Ureters", deoxy(7.5e24), 3)
                .addGeneratorRequirement("Kidneys")

                .addEfficiencyUpgrade("Bladder", deoxy(1.5e25), 6)
                .addUpgradeRequirement("Ureters");

        builder.createGenerator("Liver", oxy(6e23), deoxy(2.5e21))
                .addUpgradeRequirement("Digestion")

                .addEfficiencyUpgrade("Gallbladder", deoxy(5e26), 7)
                .addGeneratorRequirement("Liver")

                .addEfficiencyUpgrade("Pancreas", deoxy(3e27), 3)
                .addUpgradeRequirement("Gallbladder")

                .addEfficiencyUpgrade("Aorta", deoxy(1e28), 2)
                .addUpgradeRequirement("Left Ventricle")
                .addGeneratorRequirement("Liver");

        builder.resolveRequirements();

        return garden;
    }

    void main() {

        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

//        Garden.generateGeneratorUpgradesNames(garden);

        setGeneratorCount(garden, state, "Liver", 0);
        setGeneratorCount(garden, state, "Kidneys", 0);
        setGeneratorCount(garden, state, "Stomach", 0);
        setGeneratorCount(garden, state, "Skin", 0);
        setGeneratorCount(garden, state, "Lungs", 0);
        setGeneratorCount(garden, state, "Muscles", 0);
        setGeneratorCount(garden, state, "Skeleton", 0);
        setGeneratorCount(garden, state, "Endocrine System", 0);
        setGeneratorCount(garden, state, "Brain", 0);
        setGeneratorCount(garden, state, "Heart", 1);

        String[] boughtUpgrades = {
//                "Spinal Cord", // check
//                "Nerves", // check
//                "Brain Stem", // check
//                "Right Atrium", // deoxygenated blood // check
//                "Right Ventricle", // deoxygenated blood // check
//                "Digestion", // deoxygenated blood // check
//                "Left Atrium", // deoxygenated blood // check
//                "Left Ventricle", // deoxygenated blood // check

//                "Frontal Lobe", // check
//                "Occipital Lobe", // check
//                "Eyes", // check
//                "Temporal Lobe", // check
//                "Parietal Lobe", // check
//                "Nose", // check
//                "Ears", // check
//                "Mouth", // check

//                "Hypothalamus", // check
//                "Pituitary", // check
//                "Adrenal", // check
//                "Gonads", // check
//                "Pineal", // check
//                "Thyroid", // check

//                "Bones", // check
//                "Bone Marrow", // check
//                "Cartilage", // check
//                "Joints", // check

//                "Skeletal Muscles", // check
//                "Tendons", // check
//                "Smooth Muscles", // check

//                "Bronchi", // deoxygenated blood // check
//                "Aveoli", // deoxygenated blood // check

//                "Epidermis", // check
//                "Dermis", // check
//                "Hypodermis", // check

//                "Small Intestine", // check
//                "Large Intestine", // check

//                "Ureters", // check
//                "Bladder", // check

//                "Gallbladder", // check
//                "Pancreas", // check
//                "Aorta", // check
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

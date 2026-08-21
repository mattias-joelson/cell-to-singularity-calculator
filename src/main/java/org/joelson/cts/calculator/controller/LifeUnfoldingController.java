package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.LifeUnfolding;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LifeUnfoldingController {

    private static final String GARDEN_GET = "/lifeunfolding";
    private static final String GARDEN_GENERATOR_UPDATE = "/lifeunfolding-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/lifeunfolding-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/lifeunfolding-generator-decrement";
    private static final String GARDEN_UPGRADE = "/lifeunfolding-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public LifeUnfoldingController() {
        garden = LifeUnfolding.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Month Nine", 0);
        setGeneratorCount("Month Eight", 0);
        setGeneratorCount("Month Seven", 0);
        setGeneratorCount("Month Six", 0);
        setGeneratorCount("Month Five", 0);
        setGeneratorCount("Month Four", 0);
        setGeneratorCount("Month Three", 0);
        setGeneratorCount("Month Two", 0);
        setGeneratorCount("Month One", 0);
        setGeneratorCount("Mother", 1);

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
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping(GARDEN_GET)
    public String lifeUnfolding(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String lifeUnfoldingGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String lifeUnfoldingGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String lifeUnfoldingGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String lifeUnfoldingUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}

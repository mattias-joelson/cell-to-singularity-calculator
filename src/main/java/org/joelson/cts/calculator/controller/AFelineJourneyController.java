package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.AFelineJourney;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AFelineJourneyController {

    private static final String GARDEN_GET = "/felinejourney";
    private static final String GARDEN_GENERATOR_UPDATE = "/felinejourney-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/felinejourney-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/felinejourney-generator-decrement";
    private static final String GARDEN_UPGRADE = "/felinejourney-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final SingleCurrencyExplorationUpdater helper;

    public AFelineJourneyController() {
        garden = AFelineJourney.createGarden(1, 1, 0);
        state = new GardenState();
        helper = new SingleCurrencyExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE,
                GARDEN_GENERATOR_INCREMENT, GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("House Cats", 0);
        setGeneratorCount("Felis", 0);
        setGeneratorCount("Leopard Cat", 0);
        setGeneratorCount("Puma", 0);
        setGeneratorCount("Lynx", 0);
        setGeneratorCount("Ocelot", 0);
        setGeneratorCount("Caracal", 0);
        setGeneratorCount("Bay Cat", 0);
        setGeneratorCount("Panthera", 0);
        setGeneratorCount("Felidae", 1);

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
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping(GARDEN_GET)
    public String felineJourney(Model model) {
        initState();
        return helper.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String felineJourneyGeneratorUpdate(Model model, String target, String value) {
        return helper.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String felineJourneyGeneratorIncrement(Model model, String target) {
        return helper.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String felineJourneyGeneratorDecrement(Model model, String target) {
        return helper.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String felineJourneyUpgrade(Model model, String target, String value) {
        return helper.gardenUpgrade(model, target, value);
    }
}

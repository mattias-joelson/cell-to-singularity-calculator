package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.SetInStone;
import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SetInStoneController {

    private static final String GARDEN_GET = "/setinstone";
    private static final String GARDEN_GENERATOR_UPDATE = "/setinstone-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/setinstone-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/setinstone-generator-decrement";
    private static final String GARDEN_UPGRADE = "/setinstone-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final SingleCurrencyExplorationUpdater helper;

    public SetInStoneController() {
        garden = SetInStone.createGarden(1, 1, 0);
        state = new GardenState();
        helper = new SingleCurrencyExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE,
                GARDEN_GENERATOR_INCREMENT, GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Gem", 0);
        setGeneratorCount("Crystal", 0);
        setGeneratorCount("Metamorphic Rock", 0);
        setGeneratorCount("Sedimentary Rock", 0);
        setGeneratorCount("Igneous Rock", 0);
        setGeneratorCount("Mineral", 1);

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
    }

    private void setGeneratorCount(String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    @GetMapping(GARDEN_GET)
    public String setInStone(Model model) {
        initState();
        return helper.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String setInStoneGeneratorUpdate(Model model, String target, String value) {
        return helper.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String setInStoneGeneratorIncrement(Model model, String target) {
        return helper.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String setInStoneGeneratorDecrement(Model model, String target) {
        return helper.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String setInStoneUpgrade(Model model, String target, String value) {
        return helper.gardenUpgrade(model, target, value);
    }

    public record GeneratorCost(String label, String cost, String ratio, String next) {

    }

    private static List<GeneratorCost> calculateGeneratorCosts(Garden garden, GardenState state) {
        Generator crystalGenerator = garden.getGenerator("Crystal");
        GeneratorState crystalGeneratorState = state.getGeneratorState(crystalGenerator);
        Amount crystalCost = calculateGeneratorCost(crystalGenerator, crystalGeneratorState);

        Generator metamorphicGenerator = garden.getGenerator("Metamorphic Rock");
        GeneratorState metamorphicGeneratorState = state.getGeneratorState(metamorphicGenerator);
        Amount metamorphicCost = calculateGeneratorCost(metamorphicGenerator, metamorphicGeneratorState);
        Amount nextMetamorphic = metamorphicGenerator.getCost(metamorphicGeneratorState.count());
        Generator sedimentaryGenerator = garden.getGenerator("Sedimentary Rock");
        GeneratorState sedimentaryGeneratorState = state.getGeneratorState(sedimentaryGenerator);
        Amount sedimentaryCost = calculateGeneratorCost(sedimentaryGenerator, sedimentaryGeneratorState);
        Amount nextSedimentary = sedimentaryGenerator.getCost(sedimentaryGeneratorState.count());
        Generator igneousGenerator = garden.getGenerator("Igneous Rock");
        GeneratorState igneousGeneratorState = state.getGeneratorState(igneousGenerator);
        Amount nextIgneous = igneousGenerator.getCost(igneousGeneratorState.count());
        Amount igneousCost = calculateGeneratorCost(igneousGenerator, igneousGeneratorState);
        Amount nextRock = new Amount(nextIgneous.currency(),
                nextIgneous.amount() + nextSedimentary.amount() + nextMetamorphic.amount());

        double rockCost = metamorphicCost.amount() + sedimentaryCost.amount() + igneousCost.amount();

        Generator mineralGenerator = garden.getGenerator("Mineral");
        GeneratorState mineralGeneratorState = state.getGeneratorState(mineralGenerator);
        Amount mineralCost = calculateGeneratorCost(mineralGenerator, mineralGeneratorState);

        double totalCost = crystalCost.amount() + rockCost + mineralCost.amount();

        List<GeneratorCost> generatorCosts = new ArrayList<>();
        generatorCosts.add(createGeneratorCost(crystalGenerator, crystalGeneratorState, crystalCost, totalCost));
        generatorCosts.add(
                createGeneratorCost(metamorphicGenerator, metamorphicGeneratorState, metamorphicCost, totalCost));
        generatorCosts.add(
                createGeneratorCost(sedimentaryGenerator, sedimentaryGeneratorState, sedimentaryCost, totalCost));
        generatorCosts.add(createGeneratorCost(igneousGenerator, igneousGeneratorState, igneousCost, totalCost));
        generatorCosts.add(new GeneratorCost("sum rocks", new Amount(metamorphicCost.currency(), rockCost).asString(),
                String.format("%.3f %%", rockCost / totalCost), nextRock.asString()));
        generatorCosts.add(createGeneratorCost(mineralGenerator, mineralGeneratorState, mineralCost, totalCost));
        return generatorCosts;
    }

    private static Amount calculateGeneratorCost(Generator generator, GeneratorState generatorState) {
        double sum = 0;
        for (int lvl = 0; lvl < generatorState.count(); lvl += 1) {
            sum += generator.getCost(lvl).amount();
        }
        return new Amount(generator.getBaseCost().currency(), sum);
    }

    private static GeneratorCost createGeneratorCost(
            Generator generator, GeneratorState generatorState, Amount cost, double totalCost) {
        return new GeneratorCost(String.format("Crystal (%d)", generatorState.count()), cost.asString(),
                String.format("%.3f %%", cost.amount() / totalCost),
                generator.getCost(generatorState.count()).asString());
    }
}

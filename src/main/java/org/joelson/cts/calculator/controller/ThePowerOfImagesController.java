package org.joelson.cts.calculator.controller;

import org.joelson.cts.calculator.exploration.ThePowerOfImages;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThePowerOfImagesController {

    private static final String GARDEN_GET = "/powerofimages";
    private static final String GARDEN_GENERATOR_UPDATE = "/powerofimages-generator-update";
    private static final String GARDEN_GENERATOR_INCREMENT = "/powerofimages-generator-increment";
    private static final String GARDEN_GENERATOR_DECREMENT = "/powerofimages-generator-decrement";
    private static final String GARDEN_UPGRADE = "/powerofimages-upgrade";

    private final Garden garden;
    private final GardenState state;
    private final ExplorationUpdater updater;

    public ThePowerOfImagesController() {
        garden = ThePowerOfImages.createGarden(1, 1, 0);
        state = new GardenState();
        updater = new ExplorationUpdater(garden, state, GARDEN_GET, GARDEN_GENERATOR_UPDATE, GARDEN_GENERATOR_INCREMENT,
                GARDEN_GENERATOR_DECREMENT, GARDEN_UPGRADE);
        initState();
    }

    private void initState() {
        state.clearUpgradesBought();
//        state.setBoost(4);
        state.updateGeneratorStates(garden);

        setGeneratorCount("Mass Creation", 0);
        setGeneratorCount("Digital Revolution", 0);
        setGeneratorCount("Mass Appeal", 0);
        setGeneratorCount("Moving Images", 0);
        setGeneratorCount("Post Modernism", 0);
        setGeneratorCount("Art Celebrity", 0);
        setGeneratorCount("Modernism", 0);
        setGeneratorCount("Photography", 0);
        setGeneratorCount("Renaissance", 0);
        setGeneratorCount("Composition", 0);
        setGeneratorCount("Symbol", 0);
        setGeneratorCount("Line", 1);

        String[] boughtUpdates = {
                "Imagination",
//                "Drawing",
//                "Body Paint",

//                "Tattoo",
//                "Glyphs",
//                "Brush Strokes",

//                "Ancient Art",
//                "Folk Art",
//                "Greece and Rome",
//                "Church as King",
//                "The First Projection",

//                "Artisans",
//                "Patrons",
//                "Romanticism",
//                "Realism",
//                "Impressionism",
//                "Post Impressionism",

//                "Memory as an Object",
//                "Tricking the Eye",

//                "Expressionism",
//                "Artist Entrepreneur",
//                "Cubism",
//                "Museum",
//                "Abstract Expressionism",

//                "Critic",
//                "Dealer",

//                "Dadaism",
//                "Surrealism",
//                "Pop Art",
//                "Feminist Art",

//                "Hand-Drawn Animation",
//                "Silent to Talkie",
//                "Grayscale to Color",
//                "Video Art",
//                "Computer Animation",

//                "Advertising",
//                "Comics",
//                "Post Pop Art",

//                "New Media Art",
//                "Digital Art",
//                "Internet",
//                "Algorithmic Art",
//                "AI Imagery",
//                "Meaning",

//                "Street Art",
//                "Socially Engaged Art",
//                "Communities",
//                "Meme",
//                "GIF",
//                "Endless Inspiration",
//                "Anti AI Imagery",
//                "Legacy",
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
    public String powerOfImages(Model model) {
        initState();
        return updater.garden(model);
    }

    @PostMapping(GARDEN_GENERATOR_UPDATE)
    public String powerOfImagesGeneratorUpdate(Model model, String target, String value) {
        return updater.gardenGeneratorUpdate(model, target, value);
    }

    @PostMapping(GARDEN_GENERATOR_INCREMENT)
    public String powerOfImagesGeneratorIncrement(Model model, String target) {
        return updater.gardenGeneratorIncrement(model, target);
    }

    @PostMapping(GARDEN_GENERATOR_DECREMENT)
    public String powerOfImagesGeneratorDecrement(Model model, String target) {
        return updater.gardenGeneratorDecrement(model, target);
    }

    @PostMapping(GARDEN_UPGRADE)
    public String powerOfImagesUpgrade(Model model, String target, String value) {
        return updater.gardenUpgrade(model, target, value);
    }
}

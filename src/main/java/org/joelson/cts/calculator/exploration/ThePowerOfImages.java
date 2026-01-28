package org.joelson.cts.calculator.exploration;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

import java.util.ArrayList;
import java.util.List;

public class ThePowerOfImages {

    private static final String CURRENCY = "Creativity"; // "Nodes"

    private static Amount amount(double amount) {
        return new Amount(CURRENCY, amount);
    }

    private static void setGeneratorCount(Garden garden, GardenState state, String generatorName, int count) {
        Generator generator = garden.getGenerator(generatorName);
        state.setGeneratorCount(generator, count);
    }

    public static Garden createGarden(int costMultiplier, int productionMultiplier, float badgeBonus) {
        Garden garden = new Garden("The Power of Images");
        garden.addCurrency(CURRENCY);
        GardenBuilder builder = new GardenBuilder(garden, costMultiplier, productionMultiplier, badgeBonus);

        builder.createGenerator("Line", amount(40), amount(1))
                .addUpgradeRequirement("Imagination")

                .addEfficiencyUpgrade("Imagination", amount(20), 2)

                .addEfficiencyUpgrade("Drawing", amount(100), 3)
                .addGeneratorRequirement("Line")

                .addEfficiencyUpgrade("Body Paint", amount(400), 2.5f)
                .addGeneratorRequirement("Line");

        builder.createGenerator("Symbol", amount(7_500), amount(50))
                .addUpgradeRequirement("Drawing")

                .addEfficiencyUpgrade("Tattoo", amount(30_000), 2.5f)
                .addUpgradeRequirement("Body Paint")
                .addGeneratorRequirement("Symbol")

                .addEfficiencyUpgrade("Glyphs", amount(150_000), 2)
                .addGeneratorRequirement("Symbol")

                .addEfficiencyUpgrade("Brush Strokes", amount(800_000), 2)
                .addUpgradeRequirement("Glyphs");

        builder.createGenerator("Composition", amount(2.5e6), amount(2_000))
                .addUpgradeRequirement("Brush Strokes")

                .addEfficiencyUpgrade("Ancient Art", amount(3.2e6), 2)
                .addGeneratorRequirement("Composition")

                .addEfficiencyUpgrade("Folk Art", amount(1.2e7), 2)
                .addGeneratorRequirement("Composition")

                .addEfficiencyUpgrade("Greece and Rome", amount(5e7), 2)
                .addUpgradeRequirement("Ancient Art")

                .addEfficiencyUpgrade("Church as King", amount(2e8), 2)
                .addUpgradeRequirement("Folk Art")

                .addEfficiencyUpgrade("The First Projection", amount(8e8), 2)
                .addGeneratorRequirement("Renaissance");

        builder.createGenerator("Renaissance", amount(6.25e8), amount(500_000))
                .addUpgradeRequirement("Greece and Rome")
                .addUpgradeRequirement("Church as King")

                .addEfficiencyUpgrade("Artisans", amount(2e9), 2)
                .addUpgradeRequirement("Church as King")
                .addGeneratorRequirement("Renaissance")

                .addEfficiencyUpgrade("Patrons", amount(8e9), 2)
                .addUpgradeRequirement("Church as King")
                .addGeneratorRequirement("Renaissance")

                .addEfficiencyUpgrade("Romanticism", amount(3.25e10), 2)
                .addGeneratorRequirement("Renaissance")

                .addEfficiencyUpgrade("Realism", amount(1.25e11), 2)
                .addUpgradeRequirement("Romanticism")

                .addEfficiencyUpgrade("Impressionism", amount(5e11), 2)
                .addGeneratorRequirement("Renaissance")

                .addEfficiencyUpgrade("Post Impressionism", amount(2e12), 5)
                .addUpgradeRequirement("Impressionism");

        builder.createGenerator("Photography", amount(1.5e11), amount(5e7))
                .addUpgradeRequirement("The First Projection")
                .addUpgradeRequirement("Realism")

                .addEfficiencyUpgrade("Memory as an Object", amount(1e12), 11)
                .addGeneratorRequirement("Photography")

                .addEfficiencyUpgrade("Tricking the Eye", amount(1.5e16), 1_001)
                .addUpgradeRequirement("Memory as an Object");

        builder.createGenerator("Modernism", amount(4e13), amount(5e9))
                .addUpgradeRequirement("Realism")
                .addUpgradeRequirement("Post Impressionism")
                .addGeneratorRequirement("Photography")

                .addEfficiencyUpgrade("Expressionism", amount(5e13), 2)
                .addGeneratorRequirement("Modernism")

                .addEfficiencyUpgrade("Artist Entrepreneur", amount(2e14), 2.25f)
                .addUpgradeRequirement("Patrons")
                .addUpgradeRequirement("Artisans")
                .addGeneratorRequirement("Modernism")

                .addEfficiencyUpgrade("Cubism", amount(8e14), 2)
                .addGeneratorRequirement("Modernism")

                .addEfficiencyUpgrade("Museum", amount(3e15), 2)
                .addUpgradeRequirement("Patrons")
                .addGeneratorRequirement("Modernism")

                .addEfficiencyUpgrade("Abstract Expressionism", amount(6e17), 21)
                .addGeneratorRequirement("Modernism")
                .addUpgradeRequirement("Expressionism");

        builder.createGenerator("Art Celebrity", amount(1e16), amount(3e12))
                .addUpgradeRequirement("Museum")
                .addUpgradeRequirement("Artist Entrepreneur")

                .addEfficiencyUpgrade("Critic", amount(5e16), 3.5f)
                .addGeneratorRequirement("Art Celebrity")

                .addEfficiencyUpgrade("Dealer", amount(8e19), 151)
                .addGeneratorRequirement("Art Celebrity");

        builder.createGenerator("Post Modernism", amount(5e17), amount(9e13))
                .addUpgradeRequirement("Cubism")
                .addGeneratorRequirement("Art Celebrity")
                .addUpgradeRequirement("Abstract Expressionism")

                .addEfficiencyUpgrade("Dadaism", amount(2e18), 3.5f)
                .addGeneratorRequirement("Post Modernism")

                .addEfficiencyUpgrade("Surrealism", amount(1.5e19), 3)
                .addUpgradeRequirement("Dadaism")

                .addEfficiencyUpgrade("Pop Art", amount(5e20), 21)
                .addUpgradeRequirement("Surrealism")

                .addEfficiencyUpgrade("Feminist Art", amount(1.5e23), 201)
                .addUpgradeRequirement("Pop Art");

        builder.createGenerator("Moving Images", amount(1.5e20), amount(8e15))
                .addUpgradeRequirement("Tricking the Eye")

                .addEfficiencyUpgrade("Hand-Drawn Animation", amount(3e21), 4)
                .addGeneratorRequirement("Moving Images")

                .addEfficiencyUpgrade("Silent to Talkie", amount(8e21), 6)
                .addGeneratorRequirement("Moving Images")

                .addEfficiencyUpgrade("Grayscale to Color", amount(4e22), 4)
                .addUpgradeRequirement("Silent to Talkie")

                .addEfficiencyUpgrade("Video Art", amount(1e25), 301)
                .addUpgradeRequirement("Grayscale to Color")

                .addEfficiencyUpgrade("Computer Animation", amount(1e26), 2.5f)
                .addUpgradeRequirement("Hand-Drawn Animation");

        builder.createGenerator("Mass Appeal", amount(7e22), amount(3e18))
                .addUpgradeRequirement("Surrealism")
                .addUpgradeRequirement("Pop Art")
                .addUpgradeRequirement("Feminist Art")

                .addEfficiencyUpgrade("Advertising", amount(1.5e24), 2)
                .addGeneratorRequirement("Mass Appeal")

                .addEfficiencyUpgrade("Comics", amount(2e24), 3)
                .addGeneratorRequirement("Mass Appeal")

                .addEfficiencyUpgrade("Post Pop Art", amount(6e24), 4)
                .addUpgradeRequirement("Advertising");

        builder.createGenerator("Digital Revolution", amount(1e26), amount(7e21))
                .addUpgradeRequirement("Video Art")
                .addUpgradeRequirement("Computer Animation")

                .addEfficiencyUpgrade("New Media Art", amount(4e26), 3)
                .addGeneratorRequirement("Digital Revolution")

                .addEfficiencyUpgrade("Digital Art", amount(1e27), 3)
                .addUpgradeRequirement("New Media Art")

                .addEfficiencyUpgrade("Internet", amount(8e27), 2)
                .addGeneratorRequirement("Digital Revolution")

                .addEfficiencyUpgrade("Algorithmic Art", amount(1e30), 101)
                .addUpgradeRequirement("Digital Art")

                .addEfficiencyUpgrade("AI Imagery", amount(6e32), 121)
                .addUpgradeRequirement("Algorithmic Art")
                .addUpgradeRequirement("Endless Inspiration")

                .addEfficiencyUpgrade("Meaning", amount(6e33), 9)
                .addUpgradeRequirement("AI Imagery");

        builder.createGenerator("Mass Creation", amount(2e28), amount(2e24))
                .addGeneratorRequirement("Mass Appeal")
                .addUpgradeRequirement("Post Pop Art")
                .addUpgradeRequirement("Internet")

                .addEfficiencyUpgrade("Street Art", amount(5e28), 3)
                .addGeneratorRequirement("Mass Creation")

                .addEfficiencyUpgrade("Socially Engaged Art", amount(3e29), 2)
                .addGeneratorRequirement("Mass Creation")

                .addEfficiencyUpgrade("Communities", amount(5e30), 7)
                .addGeneratorRequirement("Mass Creation")

                .addEfficiencyUpgrade("Meme", amount(2e31), 2.337f)
                .addUpgradeRequirement("Internet")
                .addUpgradeRequirement("Communities")

                .addEfficiencyUpgrade("GIF", amount(6e31), 3)
                .addUpgradeRequirement("Meme")

                .addEfficiencyUpgrade("Endless Inspiration", amount(2.5e32), 2.25f)
                .addUpgradeRequirement("Communities")

                .addEfficiencyUpgrade("Anti AI Imagery", amount(2e33), 6)
                .addUpgradeRequirement("Endless Inspiration")
                .addUpgradeRequirement("AI Imagery")

                .addEfficiencyUpgrade("Legacy", amount(2e34), 1)
                .addUpgradeRequirement("Meaning");

        builder.resolveRequirements();

        return garden;
    }

    void main() {
        Garden garden = createGarden(1, 1, 0);
        GardenState state = new GardenState();
        state.updateGeneratorStates(garden);
//        state.setBoost(4);

        setGeneratorCount(garden, state, "Mass Creation", 0);
        setGeneratorCount(garden, state, "Digital Revolution", 0);
        setGeneratorCount(garden, state, "Mass Appeal", 0);
        setGeneratorCount(garden, state, "Moving Images", 0);
        setGeneratorCount(garden, state, "Post Modernism", 0);
        setGeneratorCount(garden, state, "Art Celebrity", 0);
        setGeneratorCount(garden, state, "Modernism", 0);
        setGeneratorCount(garden, state, "Photography", 0);
        setGeneratorCount(garden, state, "Renaissance", 0);
        setGeneratorCount(garden, state, "Composition", 0);
        setGeneratorCount(garden, state, "Symbol", 0);
        setGeneratorCount(garden, state, "Line", 1);

//        Garden.generateGeneratorUpgradesNames(garden);

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

//        state.verifyAllUpgradesBought(garden);

        List<String> actions = new ArrayList<>();
        ImprovementCalculator.singleCurrencyApproach(garden, state, actions);
        actions.forEach(System.out::println);
    }
}

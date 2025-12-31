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

private static final Garden GARDEN = new Garden("A Coevolution Love Story");
private static final GardenState STATE = new GardenState();
private static final String CURRENCY = "Pollen";

private static Amount pollen(double amount) {
    return new Amount(CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {
    GARDEN.addCurrency(CURRENCY);
    GardenBuilder builder = new GardenBuilder(GARDEN, STATE);

    Generator flowers = builder.createGenerator("Flowers", pollen(40), 1.1f, pollen(1))

            .addUpgrade("Naked Seeds", pollen(1_000), 4, false)
            .addGeneratorRequirement("Flowers", 1)
            .addGeneratorRequirement("Bees", 1)

            .addUpgrade("Bloom Boom", pollen(8e7), 501, false)
            .addUpgradeRequirement("The Showy Magnolia")

            .addUpgrade("Size and Structure", pollen(1e16), 5_500_001, false)
            .addGeneratorRequirement("The Art of Attraction", 1)

            .addUpgrade("Pollen Tag", pollen(3e24), 1_000_000_001, false)
            .addUpgradeRequirement("Orchid Flowers")

            .addUpgrade("Pollination Pinnacle", pollen(1e30), 10_001, false)
            .addUpgradeRequirement("Floral Oils")

            .addUpgrade("A New Suitor", pollen(8e30), 26, false)
            .addUpgradeRequirement("Pollination Pinnacle")
            .addUpgradeRequirement("Apex of Evolution")
            .generator();

    Generator primitiveFlowers = builder.createGenerator("Primitive Flowers", pollen(10_000), 1.1f, pollen(150))
            .addUpgradeRequirement("Wasteful Wind")

            .addUpgrade("Self-Marriage", pollen(50_000), 2, false)
            .addGeneratorRequirement("Primitive Flowers", 1)

            .addUpgrade("Self-Control", pollen(400_000), 1.5f, false)
            .addUpgradeRequirement("Self-Marriage")

            .addUpgrade("Stranger Marriage", pollen(900_000), 2, false)
            .addUpgradeRequirement("Self-Control")

            .addUpgrade("The Showy Magnolia", pollen(3e7), 2, false)
            .addUpgradeRequirement("Stranger Marriage")
            .generator();

    Generator artOfAttraction = builder.createGenerator("The Art of Attraction", pollen(4e10), 1.1f, pollen(7e6))
            .addGeneratorRequirement("Primitive Flowers", 1)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addUpgrade("Come One or All?", pollen(3e11), 3, false)
            .addGeneratorRequirement("The Art of Attraction", 1)

            .addUpgrade("Dandelion Welcome Mat", pollen(7e11), 3, false)
            .addUpgradeRequirement("Come One or All?")

            .addUpgrade("Snappy Snapdragons", pollen(4e12), 6, false)
            .addUpgradeRequirement("Come One or All?")

            .addUpgrade("Early Willows", pollen(2e14), 5, false)
            .addUpgradeRequirement("Come One or All?")

            .addUpgrade("Color and Pattern", pollen(6e15), 11, false)
            .addGeneratorRequirement("The Art of Attraction", 1)
            .generator();

    Generator briberyAndDeception = builder.createGenerator("Bribery and Deception", pollen(3e18), 1.1f,
                    pollen(5e14))
            .addGeneratorRequirement("The Art of Attraction", 1)
            .addGeneratorRequirement("Food Banking", 1)

            .addUpgrade("Nectar Bribes", pollen(3e19), 3, false)
            .addGeneratorRequirement("Bribery and Deception", 1)

            .addUpgrade("Nectar Safeguards", pollen(2.5e20), 4, false)
            .addUpgradeRequirement("Nectar Bribes")

            .addUpgrade("Kidnappers!", pollen(1.25e22), 6, false)
            .addUpgradeRequirement("Nectar Bribes")

            .addUpgrade("Murderers!", pollen(2.5e22), 6, false)
            .addUpgradeRequirement("Kidnappers!")

            .addUpgrade("Floral Oils", pollen(8e22), 3, false)
            .addGeneratorRequirement("Bribery and Deception", 1)

            .addUpgrade("Orchid Flowers", pollen(8e23), 5, false)
            .addUpgradeRequirement("Floral Oils") // Orchid Bees?
            .generator();

    Generator bees = builder.createGenerator("Bees", pollen(500), 1.15f, pollen(450), 45)
            .addGeneratorRequirement("Flowers", 1)

            .addUpgrade("Wasteful Wind", pollen(4_000), 3, false)
            .addUpgradeRequirement("Naked Seeds")

            .addUpgrade("Flight", pollen(6_000), 2, false)
            .addUpgradeRequirement("Wasteful Wind")

            .addUpgrade("The Hungry Beetle", pollen(7e6), 21, false)
            .addUpgradeRequirement("Flight")

            .addUpgrade("The Vegetarian Wasp", pollen(3e8), 5, false)
            .addUpgradeRequirement("Flight")

            .addUpgrade("Solitary Nests", pollen(3e9), 1, 10, false)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addUpgrade("UV Vision", pollen(8e14), 100_001, false)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addUpgrade("Happy Ending", pollen(6e24), 10_000_000_001f, false)
            .addUpgradeRequirement("Pollen Tag")//.addUpgradeRequirement("Orchid Bees")

            .addUpgrade("Apex of Evolution", pollen(7e28), 10_001, false)
            .addGeneratorRequirement("The Hive Life", 1)
            .addUpgradeRequirement("Waggle Dance")

            .generator();

    GARDEN.getUpgrade("A New Suitor").addEffect(new UpgradeEffect(bees, 16));

    Generator primitiveBees = builder.createGenerator("Primitive Bees", pollen(4e8), 1.15f, pollen(1.5e8), 500)
            .addUpgradeRequirement("The Vegetarian Wasp")

            .addUpgrade("Johnston's Organ", pollen(5e9), 4, false)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addUpgrade("The Competition", pollen(7e9), 1, false)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addUpgrade("Birds Not Bees", pollen(3e10), 1, 2, false)
            .addUpgradeRequirement("The Competition")

            .addUpgrade("Bumblebee Exclusive", pollen(5e13), 1, 150, false)
            .addUpgradeRequirement("Birds Not Bees") // ???
            .addUpgradeRequirement("Snappy Snapdragons") // ???
            .generator();

    Generator foodBanking = builder.createGenerator("Food Banking", pollen(1.5e16), 1.15f, pollen(1.8e15), 1_800)
            .addGeneratorRequirement("Primitive Bees", 1)
            .addUpgradeRequirement("UV Vision")

            .addUpgrade("Very Hairy Body", pollen(3e16), 3, false)
            .addGeneratorRequirement("Food Banking", 1)

            .addUpgrade("Pollen Brushes", pollen(5e16), 1, 2, false)
            .addUpgradeRequirement("Very Hairy Body")

            .addUpgrade("Bristled Baskets", pollen(2e17), 6, false)
            .addUpgradeRequirement("Pollen Brushes")

            .addUpgrade("Buzz Pollination", pollen(7e17), 1, 6, false)
            .addGeneratorRequirement("Food Banking", 1)

            .addUpgrade("Mechanical Mouthparts", pollen(1.4e21), 11, false)
            .addGeneratorRequirement("Food Banking", 1) // ???
            .addUpgradeRequirement("Nectar Safeguards") // ???

            .addUpgrade("Bandit Bees", pollen(2.5e21), 1, 20, false)
            .addUpgradeRequirement("Mechanical Mouthparts")

            .addUpgrade("All-Purpose Oils", pollen(2.5e23), 1, 4, false)
            .addUpgradeRequirement("Floral Oils")

            .addUpgrade("Orchid Bees", pollen(5e23), 9, false)
            .addUpgradeRequirement("Solitary Nests")
            .generator();

    Generator hiveLife = builder.createGenerator("The Hive Life", pollen(8e25), 1.15f, pollen(3.6e26), 3_600)
            .addGeneratorRequirement("Food Banking", 1)

            .addUpgrade("Baby Bees", pollen(8e25), 1, 2, false)
            .addGeneratorRequirement("The Hive Life", 1)

            .addUpgrade("Old Foragers", pollen(3.5e26), 1, 5, false)
            .addUpgradeRequirement("Baby Bees")

            .addUpgrade("Waggle Dance", pollen(2e27), 11, 2, false)
            .addUpgradeRequirement("Old Foragers")
            .generator();

    Generator human = builder.createGenerator("Human", pollen(2.8e31), 1.15f, pollen(2.88e32), 7_200)
            .addUpgradeRequirement("A New Suitor")

            .addUpgrade("The Crops We Crave", pollen(8e31), 1, 2, false)
            .addGeneratorRequirement("Human", 1)

            .addUpgrade("Our Favorite Bee", pollen(4e32), 1, 10, false)
            .addUpgradeRequirement("The Crops We Crave")

            .addUpgrade("Africanized Bees", pollen(7e33), 1, 10, false)
            .addUpgradeRequirement("Our Favorite Bee")

            .addUpgrade("Wild Decline", pollen(1e35), 11, false)
            .addGeneratorRequirement("Human", 1)

            .addUpgrade("Habitat Destruction", pollen(3e36), 1, 3, false)
            .addUpgradeRequirement("Wild Decline")

            .addUpgrade("Varroa Destructor", pollen(4e37), 3, false)
            .addGeneratorRequirement("Human", 1)

            .addUpgrade("Colony Collapse Disorder", pollen(5e38), 6, false)
            .addUpgradeRequirement("Varroa Destructor")

            .addUpgrade("Till Death Do Us Part?", pollen(2e39), 6, false)
            .addUpgradeRequirement("Africanized Bees")
            .addUpgradeRequirement("Habitat Destruction")
            .addUpgradeRequirement("Colony Collapse Disorder")
            .generator();

    builder.resolveRequirements();
    STATE.updateGeneratorStates(GARDEN);
    //STATE.setBoost(2);

    setGeneratorCount(human, 0);
    setGeneratorCount(hiveLife, 0);
    setGeneratorCount(foodBanking, 0);
    setGeneratorCount(primitiveBees, 0);
    setGeneratorCount(bees, 0);
    setGeneratorCount(briberyAndDeception, 0);
    setGeneratorCount(artOfAttraction, 0);
    setGeneratorCount(primitiveFlowers, 0);
    setGeneratorCount(flowers, 1);

    GardenState state = STATE.copy();
    CurrencyMapping mapping = new CurrencyMapping(CURRENCY, CURRENCY);
    List<String> actions = new ArrayList<>();
    printUnlocked(GARDEN, state, actions);
    boolean possibleUnlock = false;
    for (int i = 0; i < 20 || !possibleUnlock; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ImprovementDescription improvementDescription = ImprovementCalculator.calculateImprovement(GARDEN, state)
                .get(mapping);
        Improvement improvement = improvementDescription.improvement();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        possibleUnlock = false;
        if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
            int count = generatorState.count();
            actions.add(String.format("(%d) Generator %s: %d -> %d : %s", i + 1, generator.getName(), count, count + 1,
                    improvementDescription.description()));
            state.setGeneratorCount(generator, count + 1);
            if (count == 0) {
                printUnlocked(GARDEN, state, actions);
                possibleUnlock = true;
            }
        } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
            Upgrade upgrade = upgradeImprovement.upgrade();
            StringBuilder improvedGenerators = new StringBuilder();
            for (UpgradeEffect effect : upgrade.getEffects()) {
                if (!improvedGenerators.isEmpty()) {
                    improvedGenerators.append(", ");
                }
                improvedGenerators.append(effect.generator().getName());
            }
            actions.add(String.format("(%d) Upgrade %s (%s) : %s", i + 1, upgrade.getName(), improvedGenerators,
                    improvementDescription.description()));
            state.setUpgradeBought(upgrade);
            state.updateGeneratorStates(GARDEN);
            printUnlocked(GARDEN, state, actions);
            possibleUnlock = true;
        } else {
            throw new NullPointerException();
        }
    }

    actions.forEach(System.out::println);
}

private static void printUnlocked(Garden garden, GardenState state, List<String> actions) {
    for (Generator generator : garden.getUnlockedGenerators(state).reversed()) {
        if (state.getGeneratorState(generator).count() == 0) {
            if (generator.isTimed()) {
                actions.add(String.format(" *** unlocked generator %s: base cost %s, inc %.2f, base production %s, "
                                + "base charge time %,.2f",
                        generator.getName(), generator.getBaseCost().asString(), generator.getCompoundingCost(),
                        generator.getBaseProduction().asString(), generator.getBaseChargeTime() / STATE.getBoost()));
            } else {
                actions.add(String.format(" *** unlocked generator %s: base cost %s, inc %.2f, base production %s",
                        generator.getName(), generator.getBaseCost().asString(), generator.getCompoundingCost(),
                        generator.getBaseProduction().multiplyBy(STATE.getBoost()).asString()));
            }
        }
    }
    for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
        if (!state.isUpgradeBought(upgrade)) {
            for (UpgradeEffect effect : upgrade.getEffects()) {
                actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, speed %,d, cost %s",
                        upgrade.getName(), effect.generator().getName(), effect.efficiency(), effect.speed(),
                        upgrade.getCost().asString()));
            }
        }
    }
}

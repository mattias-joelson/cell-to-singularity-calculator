import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.UpgradeEffect;
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
    GardenBuilder builder = new GardenBuilder(GARDEN);

    Generator flowers = builder.createGenerator("Flowers", pollen(40), 1.1f, pollen(1))

            .addEfficiencyUpgrade("Naked Seeds", pollen(1_000), 4)
            .addGeneratorRequirement("Flowers", 1)
            .addGeneratorRequirement("Bees", 1)

            .addEfficiencyUpgrade("Bloom Boom", pollen(8e7), 501)
            .addUpgradeRequirement("The Showy Magnolia")

            .addEfficiencyUpgrade("Size and Structure", pollen(1e16), 5_500_001)
            .addGeneratorRequirement("The Art of Attraction", 1)

            .addEfficiencyUpgrade("Pollen Tag", pollen(3e24), 1_000_000_001)
            .addUpgradeRequirement("Orchid Flowers")

            .addEfficiencyUpgrade("Pollination Pinnacle", pollen(1e30), 10_001)
            .addUpgradeRequirement("Floral Oils")

            .addEfficiencyUpgrade("A New Suitor", pollen(8e30), 26)
            .addUpgradeRequirement("Pollination Pinnacle")
            .addUpgradeRequirement("Apex of Evolution")
            .generator();

    Generator primitiveFlowers = builder.createGenerator("Primitive Flowers", pollen(10_000), 1.1f, pollen(150))
            .addUpgradeRequirement("Wasteful Wind")

            .addEfficiencyUpgrade("Self-Marriage", pollen(50_000), 2)
            .addGeneratorRequirement("Primitive Flowers", 1)

            .addEfficiencyUpgrade("Self-Control", pollen(400_000), 1.5f)
            .addUpgradeRequirement("Self-Marriage")

            .addEfficiencyUpgrade("Stranger Marriage", pollen(900_000), 2)
            .addUpgradeRequirement("Self-Control")

            .addEfficiencyUpgrade("The Showy Magnolia", pollen(3e7), 2)
            .addUpgradeRequirement("Stranger Marriage")
            .generator();

    Generator artOfAttraction = builder.createGenerator("The Art of Attraction", pollen(4e10), 1.1f, pollen(7e6))
            .addGeneratorRequirement("Primitive Flowers", 1)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addEfficiencyUpgrade("Come One or All?", pollen(3e11), 3)
            .addGeneratorRequirement("The Art of Attraction", 1)

            .addEfficiencyUpgrade("Dandelion Welcome Mat", pollen(7e11), 3)
            .addUpgradeRequirement("Come One or All?")

            .addEfficiencyUpgrade("Snappy Snapdragons", pollen(4e12), 6)
            .addUpgradeRequirement("Come One or All?")

            .addEfficiencyUpgrade("Early Willows", pollen(2e14), 5)
            .addUpgradeRequirement("Come One or All?")

            .addEfficiencyUpgrade("Color and Pattern", pollen(6e15), 11)
            .addGeneratorRequirement("The Art of Attraction", 1)
            .generator();

    Generator briberyAndDeception = builder.createGenerator("Bribery and Deception", pollen(3e18), 1.1f,
                    pollen(5e14))
            .addGeneratorRequirement("The Art of Attraction", 1)
            .addGeneratorRequirement("Food Banking", 1)

            .addEfficiencyUpgrade("Nectar Bribes", pollen(3e19), 3)
            .addGeneratorRequirement("Bribery and Deception", 1)

            .addEfficiencyUpgrade("Nectar Safeguards", pollen(2.5e20), 4)
            .addUpgradeRequirement("Nectar Bribes")

            .addEfficiencyUpgrade("Kidnappers!", pollen(1.25e22), 6)
            .addUpgradeRequirement("Nectar Bribes")

            .addEfficiencyUpgrade("Murderers!", pollen(2.5e22), 6)
            .addUpgradeRequirement("Kidnappers!")

            .addEfficiencyUpgrade("Floral Oils", pollen(8e22), 3)
            .addGeneratorRequirement("Bribery and Deception", 1)

            .addEfficiencyUpgrade("Orchid Flowers", pollen(8e23), 5)
            .addUpgradeRequirement("Floral Oils") // Orchid Bees?
            .generator();

    Generator bees = builder.createGenerator("Bees", pollen(500), 1.15f, pollen(450), 45)
            .addGeneratorRequirement("Flowers", 1)

            .addEfficiencyUpgrade("Wasteful Wind", pollen(4_000), 3)
            .addUpgradeRequirement("Naked Seeds")

            .addEfficiencyUpgrade("Flight", pollen(6_000), 2)
            .addUpgradeRequirement("Wasteful Wind")

            .addEfficiencyAutomatedUpgrade("The Hungry Beetle", pollen(7e6), 21)
            .addUpgradeRequirement("Flight")

            .addEfficiencyUpgrade("The Vegetarian Wasp", pollen(3e8), 5)
            .addUpgradeRequirement("Flight")

            .addSpeedUpgrade("Solitary Nests", pollen(3e9), 10)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addEfficiencyUpgrade("UV Vision", pollen(8e14), 100_001)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addEfficiencyUpgrade("Happy Ending", pollen(6e24), 10_000_000_001f)
            .addUpgradeRequirement("Pollen Tag")//.addUpgradeRequirement("Orchid Bees")

            .addEfficiencyUpgrade("Apex of Evolution", pollen(7e28), 10_001)
            .addGeneratorRequirement("The Hive Life", 1)
            .addUpgradeRequirement("Waggle Dance")

            .generator();

    GARDEN.getUpgrade("A New Suitor").addEffect(UpgradeEffect.withEfficiency(bees, 16));

    Generator primitiveBees = builder.createGenerator("Primitive Bees", pollen(4e8), 1.15f, pollen(1.5e8), 500)
            .addUpgradeRequirement("The Vegetarian Wasp")

            .addEfficiencyUpgrade("Johnston's Organ", pollen(5e9), 4)
            .addGeneratorRequirement("Primitive Bees", 1)

            .addAutomatedUpgrade("The Competition", pollen(7e9))
            .addGeneratorRequirement("Primitive Bees", 1)

            .addSpeedUpgrade("Birds Not Bees", pollen(3e10), 2)
            .addUpgradeRequirement("The Competition")

            .addSpeedUpgrade("Bumblebee Exclusive", pollen(5e13), 150)
            .addUpgradeRequirement("Birds Not Bees") // ???
            .addUpgradeRequirement("Snappy Snapdragons") // ???
            .generator();

    Generator foodBanking = builder.createGenerator("Food Banking", pollen(1.5e16), 1.15f, pollen(1.8e15), 1_800)
            .addGeneratorRequirement("Primitive Bees", 1)
            .addUpgradeRequirement("UV Vision")

            .addEfficiencyAutomatedUpgrade("Very Hairy Body", pollen(3e16), 3)
            .addGeneratorRequirement("Food Banking", 1)

            .addSpeedUpgrade("Pollen Brushes", pollen(5e16), 2)
            .addUpgradeRequirement("Very Hairy Body")

            .addEfficiencyUpgrade("Bristled Baskets", pollen(2e17), 6)
            .addUpgradeRequirement("Pollen Brushes")

            .addSpeedUpgrade("Buzz Pollination", pollen(7e17), 6)
            .addGeneratorRequirement("Food Banking", 1)

            .addEfficiencyUpgrade("Mechanical Mouthparts", pollen(1.4e21), 11)
            .addGeneratorRequirement("Food Banking", 1) // ???
            .addUpgradeRequirement("Nectar Safeguards") // ???

            .addSpeedUpgrade("Bandit Bees", pollen(2.5e21), 20)
            .addUpgradeRequirement("Mechanical Mouthparts")

            .addSpeedUpgrade("All-Purpose Oils", pollen(2.5e23), 4)
            .addUpgradeRequirement("Floral Oils")

            .addEfficiencyUpgrade("Orchid Bees", pollen(5e23), 9)
            .addUpgradeRequirement("Solitary Nests")
            .generator();

    Generator hiveLife = builder.createGenerator("The Hive Life", pollen(8e25), 1.15f, pollen(3.6e26), 3_600)
            .addGeneratorRequirement("Food Banking", 1)

            .addSpeedAutomatedUpgrade("Baby Bees", pollen(8e25), 2)
            .addGeneratorRequirement("The Hive Life", 1)

            .addSpeedUpgrade("Old Foragers", pollen(3.5e26), 5)
            .addUpgradeRequirement("Baby Bees")

            .addEfficiencySpeedUpgrade("Waggle Dance", pollen(2e27), 11, 2)
            .addUpgradeRequirement("Old Foragers")
            .generator();

    Generator human = builder.createGenerator("Human", pollen(2.8e31), 1.15f, pollen(2.88e32), 7_200)
            .addUpgradeRequirement("A New Suitor")

            .addSpeedAutomatedUpgrade("The Crops We Crave", pollen(8e31), 2)
            .addGeneratorRequirement("Human", 1)

            .addSpeedUpgrade("Our Favorite Bee", pollen(4e32), 10)
            .addUpgradeRequirement("The Crops We Crave")

            .addSpeedUpgrade("Africanized Bees", pollen(7e33), 10)
            .addUpgradeRequirement("Our Favorite Bee")

            .addEfficiencyUpgrade("Wild Decline", pollen(1e35), 11)
            .addGeneratorRequirement("Human", 1)

            .addSpeedUpgrade("Habitat Destruction", pollen(3e36), 3)
            .addUpgradeRequirement("Wild Decline")

            .addEfficiencyUpgrade("Varroa Destructor", pollen(4e37), 3)
            .addGeneratorRequirement("Human", 1)

            .addEfficiencyUpgrade("Colony Collapse Disorder", pollen(5e38), 6)
            .addUpgradeRequirement("Varroa Destructor")

            .addEfficiencyUpgrade("Till Death Do Us Part?", pollen(2e39), 6)
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

    List<String> actions = new ArrayList<>();
    ImprovementCalculator.singleCurrencyApproach(GARDEN, STATE, actions);
    actions.forEach(System.out::println);
}

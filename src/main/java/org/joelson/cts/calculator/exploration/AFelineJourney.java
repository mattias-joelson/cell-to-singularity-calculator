import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.CurrencyMapping;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorImprovement;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.ImprovementCalculator;
import org.joelson.cts.calculator.model.ImprovementDescription;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.joelson.cts.calculator.model.UpgradeImprovement;
import org.joelson.cts.calculator.model.builder.GardenBuilder;

private static final Garden GARDEN = new Garden("A Feline Journey");
private static final GardenState STATE = new GardenState();
private static final String CURRENCY = "Paws";

private static Amount paws(double amount) {
    return new Amount(CURRENCY, amount);
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    GARDEN.addCurrency(CURRENCY);
    GardenBuilder builder = new GardenBuilder(GARDEN, STATE);

    Generator felidae = builder.createGenerator("Felidae", paws(30), 1.11f, paws(1))

            .addUpgrade("Pantherinae", paws(100), 1.75f, false)
            .addGeneratorRequirement("Felidae", 1)

            .addUpgrade("Felinae", paws(750), 1.75f, false)
            .addUpgradeRequirement("Pantherinae")

            .addUpgrade("Digitigrade", paws(3_000), 1.5f, false)
            .addUpgradeRequirement("Felinae")

            .addUpgrade("Claws", paws(3e7), 501, false)
            .addUpgradeRequirement("Digitigrade")

            .addUpgrade("Acute Senses", paws(8e9), 16, false)
            .addUpgradeRequirement("Claws")

            .addUpgrade("Flexibility", paws(6e12), 251, false)
            .addUpgradeRequirement("Acute Senses")

            .addUpgrade("Patterned Coats", paws(8e15), 1251, false)
            .addUpgradeRequirement("Flexibility")

            .addUpgrade("Rough Tongue", paws(8e17), 101, false)
            .addUpgradeRequirement("Patterned Coats")

            .addUpgrade("Short Skull", paws(2.5e20), 101, false)
            .addUpgradeRequirement("Rough Tongue")

            .addUpgrade("Obligate Carnivores", paws(3e23), 2501, false)
            .addUpgradeRequirement("Short Skull")

            .addUpgrade("Whiskers", paws(2.43e26), 501, false)
            .addUpgradeRequirement("Obligate Carnivores")
            .generator();

    Generator panthera = builder.createGenerator("Panthera", paws(30000), 1.15f, paws(200))
            .addUpgradeRequirement("Digitigrade")

            .addUpgrade("Clouded Leopard", paws(80_000), 2, false)
            .addGeneratorRequirement("Panthera", 1)

            .addUpgrade("Tiger", paws(240_000), 1.75f, false)
            .addUpgradeRequirement("Clouded Leopard")

            .addUpgrade("Leopard", paws(720_000), 1.5f, false)
            .addUpgradeRequirement("Tiger")

            .addUpgrade("Snow Leopard", paws(2.16e6), 2, false)
            .addUpgradeRequirement("Leopard")

            .addUpgrade("Sunda Clouded Leopard", paws(2.7e9), 41, false)
            .addUpgradeRequirement("Asian Golden Cat")

            .addUpgrade("Lion", paws(1.5e12), 101, false)
            .addUpgradeRequirement("African Caracal")

            .addUpgrade("Jaguar", paws(9e19), 1e7f + 1, false)
            .addUpgradeRequirement("Cougar")
            .generator();

    Generator bayCat = builder.createGenerator("Bay Cat", paws(3e7), 1.15f, paws(40_000))
            .addUpgradeRequirement("Snow Leopard")

            .addUpgrade("Bornean Bay Cat", paws(1e8), 1.5f, false)
            .addGeneratorRequirement("Bay Cat", 1)

            .addUpgrade("Asian Golden Cat", paws(3e8), 2f, false)
            .addGeneratorRequirement("Bay Cat", 1)

            .addUpgrade("Marbled Cat", paws(9e8), 1.75f, false)
            .addUpgradeRequirement("Asian Golden Cat")
            .generator();

    Generator caracal = builder.createGenerator("Caracal", paws(3e10), 1.15f, paws(1e7))
            .addUpgradeRequirement("Marbled Cat")

            .addUpgrade("African Caracal", paws(9e10), 1.75f, false)
            .addGeneratorRequirement("Caracal", 1)

            .addUpgrade("Serval", paws(2.7e11), 2, false)
            .addUpgradeRequirement("African Caracal")
            .generator();

    Generator ocelot = builder.createGenerator("Ocelot", paws(1e13), 1.15f, paws(2.5e9))
            .addUpgradeRequirement("Serval")

            .addUpgrade("Ocelots", paws(7e13), 2.25f, false)
            .addGeneratorRequirement("Ocelot", 1)

            .addUpgrade("Kodkod", paws(2e14), 2, false)
            .addGeneratorRequirement("Ocelot", 1)

            .addUpgrade("Andean Mountain Cat", paws(6e14), 1.75f, false)
            .addUpgradeRequirement("Ocelots")

            .addUpgrade("Geoffroy's Cat", paws(2e15), 1.5f, false)
            .addUpgradeRequirement("Kodkod")

            .addUpgrade("Margay", paws(4e15), 2, false)
            .addUpgradeRequirement("Kodkod")
            .generator();

    Generator lynx = builder.createGenerator("Lynx", paws(1e16), 1.15f, paws(8e11))
            .addUpgradeRequirement("Geoffroy's Cat")

            .addUpgrade("Eurasian Lynx", paws(5e16), 3, false)
            .addGeneratorRequirement("Lynx", 1)

            .addUpgrade("Iberian Lynx", paws(1.5e17), 2.25f, false)
            .addGeneratorRequirement("Lynx", 1)

            .addUpgrade("Bobcat", paws(3e17), 2.5f, false)
            .addUpgradeRequirement("Eurasian Lynx")
            .generator();

    Generator puma = builder.createGenerator("Puma", paws(1e18), 1.15f, paws(1.5e14))
            .addUpgradeRequirement("Iberian Lynx")

            .addUpgrade("Cougar", paws(9e18), 2.75f, false)
            .addGeneratorRequirement("Puma", 1)

            .addUpgrade("Cheetah", paws(3e19), 2, false)
            .addGeneratorRequirement("Puma", 1)
            .generator();

    Generator leopardCat = builder.createGenerator("Leopard Cat", paws(5e20), 1.15f, paws(4e16))
            .addUpgradeRequirement("Cheetah")

            .addUpgrade("Leopard Cats", paws(1e21), 2.25f, false)
            .addGeneratorRequirement("Leopard Cat", 1)

            .addUpgrade("Sunda Leopard Cat", paws(3e21), 2.25f, false)
            .addGeneratorRequirement("Leopard Cat", 1)

            .addUpgrade("Fishing Cat", paws(9e21), 2, false)
            .addUpgradeRequirement("Leopard Cats")

            .addUpgrade("Flat-Headed Cat", paws(2.7e22), 2.25f, false)
            .addUpgradeRequirement("Sunda Leopard Cat")

            .addUpgrade("Rusty-Spotted Cat", paws(8.1e22), 2, false)
            .addUpgradeRequirement("Sunda Leopard Cat")

            .addUpgrade("Pallas' Cat", paws(2.7e25), 151, false)
            .addGeneratorRequirement("Leopard Cat", 1)
            .addUpgradeRequirement("Jungle Cat")
            .generator();

    Generator felis = builder.createGenerator("Felis", paws(5e23), 1.15f, paws(2e19))
            .addUpgradeRequirement("Flat-Headed Cat")

            .addUpgrade("Jungle Cat", paws(1e24), 3.5f, false)
            .addGeneratorRequirement("Felis", 1)

            .addUpgrade("Black-Footed Cat", paws(3e24), 2f, false)
            .addGeneratorRequirement("Felis", 1)

            .addUpgrade("Sand Cat", paws(9e24), 2, false)
            .addUpgradeRequirement("Jungle Cat")

            .addUpgrade("African Wildcat", paws(8.1e25), 3.5f, false)
            .addUpgradeRequirement("Sand Cat")
            .generator();

    Generator houseCats = builder.createGenerator("House Cats", paws(3e26), 1.15f, paws(5e21))
            .addUpgradeRequirement("Pallas' Cat")
            .addUpgradeRequirement("African Wildcat")

            .addUpgrade("Fluffy", paws(6e26), 3, false)
            .addGeneratorRequirement("House Cats", 1)

            .addUpgrade("Australian Cats", paws(5e26), 1, false)
            .addUpgradeRequirement("Fluffy")

            .addUpgrade("Hairless", paws(1.5e27), 5, false)
            .addUpgradeRequirement("Fluffy")

            .addUpgrade("Orange", paws(7e27), 2, false)
            .addUpgradeRequirement("Hairless")

            .addUpgrade("Hunter at Heart", paws(2.5e28), 2, false)
            .addUpgradeRequirement("Orange")
            .generator();

    builder.resolveRequirements();
    STATE.updateGeneratorStates(GARDEN);
    STATE.setBoost(1);

    setGeneratorCount(houseCats, 0);
    setGeneratorCount(felis, 0);
    setGeneratorCount(leopardCat, 0);
    setGeneratorCount(puma, 0);
    setGeneratorCount(lynx, 0);
    setGeneratorCount(ocelot, 0);
    setGeneratorCount(caracal, 0);
    setGeneratorCount(bayCat, 0);
    setGeneratorCount(panthera, 0);
    setGeneratorCount(felidae, 1);

    GardenState state = STATE.copy();
    CurrencyMapping mapping = new CurrencyMapping(CURRENCY, CURRENCY);
    List<String> actions = new ArrayList<>();
    printUnlocked(GARDEN, state, actions);
    boolean possibleUnlock = false;
    for (int i = 0; i < 20 || !possibleUnlock; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ImprovementDescription improvementDescription
                = ImprovementCalculator.calculateImprovement(GARDEN, state).get(mapping);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        possibleUnlock = false;
        if (improvementDescription.improvement() instanceof GeneratorImprovement(Generator generator,
                GeneratorState generatorState)) {
            int count = generatorState.count();
            actions.add(String.format("(%d) Generator %s: %d -> %d : %s", i + 1, generator.getName(), count, count + 1,
                    improvementDescription.description()));
            state.setGeneratorCount(generator, count + 1);
            if (count == 0) {
                printUnlocked(GARDEN, state, actions);
                possibleUnlock = true;
            }
        } else if (improvementDescription.improvement() instanceof UpgradeImprovement upgradeImprovement) {
            Upgrade upgrade = upgradeImprovement.upgrade();
            UpgradeEffect effect = upgrade.getEffects().getFirst();
            actions.add(String.format("(%d) Upgrade %s (%s) : %s", i + 1, upgrade.getName(), effect.generator().getName(),
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
            actions.add(String.format(" *** unlocked generator %s: base cost %s, inc %.2f, base production %s",
                    generator.getName(), generator.getBaseCost().asString(), generator.getCompoundingCost(),
                    generator.getBaseProduction().multiplyBy(STATE.getBoost()).asString()));
        }
    }
    for (Upgrade upgrade : garden.getUnlockedUpgrades(state)) {
        if (!state.isUpgradeBought(upgrade)) {
            for (UpgradeEffect effect : upgrade.getEffects()) {
                actions.add(String.format(" *** unlocked upgrade %s: %s efficiency %.2f, cost %s",
                        upgrade.getName(), effect.generator().getName(), effect.efficiency(),
                        upgrade.getCost().asString()));
            }
        }
    }
}

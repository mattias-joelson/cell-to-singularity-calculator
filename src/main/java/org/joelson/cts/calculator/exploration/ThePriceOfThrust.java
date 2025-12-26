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

private static final Garden GARDEN = new Garden("The Price of Thrust");
private static final GardenState STATE = new GardenState();

private static final String COMMODITY_CURRENCY = "Shells";
private static final String COINS_CURRENCY = "Coins";
private static final String PAPER_CURRENCY = "Notes";
private static final String ELECTRONIC_CURRENCY = "Electronic Money";
private static final String FUTURE_CURRENCY = "Future Money";

private static final String[] CURRENCIES =
        { COMMODITY_CURRENCY, COINS_CURRENCY, PAPER_CURRENCY, ELECTRONIC_CURRENCY, FUTURE_CURRENCY };

private static Amount commodity(double amount) {
    return new Amount(COMMODITY_CURRENCY, amount);
}

private static Amount coins(double amount) {
    return new Amount(COINS_CURRENCY, amount);
}

private static Amount paper(double amount) {
    return new Amount(PAPER_CURRENCY, amount);
}

private static Amount electronic(double amount) {
    return new Amount(ELECTRONIC_CURRENCY, amount);
}

private static Amount future(double amount) {
    return new Amount(FUTURE_CURRENCY, amount);
}

private static void miscellaneousUpgrade(String name, double commodityAmount, boolean bought) {
    Upgrade upgrade = new Upgrade(name, commodity(commodityAmount));
    GARDEN.addUpgrade(upgrade);
    if (bought) {
        STATE.setUpgradeBought(upgrade);
    }
}

private void setGeneratorCount(Generator generator, int count) {
    STATE.setGeneratorCount(generator, count);
}

void main() {

    for (String currency : CURRENCIES) {
        GARDEN.addCurrency(currency);
    }

    miscellaneousUpgrade("Hunting and Gathering", 0.0004, true);
    miscellaneousUpgrade("Gifting", 0.0005, true);
    miscellaneousUpgrade("Settling", 0.006, true);
    miscellaneousUpgrade("Specializing", 1, true);

    GardenBuilder builder = new GardenBuilder(GARDEN, STATE);

    Generator commodity = builder.createGenerator("Commodity Currency", commodity(25), 1.07f, commodity(1))
            .addUpgradeRequirement("Specializing")

            .addUpgrade("Debt", commodity(125), 2.5f, false)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Gifting")

            .addUpgrade("Tally Sticks", commodity(5000), 2, false)
            .addUpgradeRequirement("Debt")

            .addUpgrade("Bartering", commodity(20_000), 2, false)
            .addUpgradeRequirement("Settling")
            .addUpgradeRequirement("Tally Sticks")

            .addUpgrade("Wampum", commodity(250_000), 2.5f, false)
            .addUpgradeRequirement("Gifting")
            .addUpgradeRequirement("Bartering")

            .addUpgrade("Recordkeeping", commodity(800_000), 2.5f, false)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Tally Sticks")

            .addUpgrade("Intrinsic Value", commodity(3e6), 2, false)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Bartering")

            .addUpgrade("Precious Metals", commodity(9e6), 2.5f, false)
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Intrinsic Value")

            .addUpgrade("Metallurgy", commodity(4e7), 2.5f, false)
            .addUpgradeRequirement("Precious Metals")

            .addUpgrade("Cowrie Shells", commodity(2.5e8), 3, false)
            .addUpgradeRequirement("Intrinsic Value")
            //.addUpgradeRequirement("Metallurgy")

            .addUpgrade("Bronze Shells", commodity(7.5e8), 5, false)
            .addUpgradeRequirement("Cowrie Shells")

            .addUpgrade("Government Mints", commodity(4e10), 3, false)
            .addUpgradeRequirement("Metallurgy")

            .addUpgrade("Shekels", coins(600), 4, false)
            .addUpgradeRequirement("Precious Metals")
            .addGeneratorRequirement("Coins")

            .addUpgrade("Rai Stones", coins(1e7), 101, false)
            .addUpgradeRequirement("Recordkeeping")
            .addUpgradeRequirement("Government Mints")
            .addUpgradeRequirement("Interest")

            .addUpgrade("Quipu", paper(750), 1_001, false)
            .addUpgradeRequirement("Recordkeeping")
            .addGeneratorRequirement("Paper Money")
            .generator();

    Generator coins = builder.createGenerator("Coins", commodity(1e9), 1.09f, coins(1))
            .addGeneratorRequirement("Commodity Currency")
            .addUpgradeRequirement("Metallurgy")

            .addUpgrade("Lydian Coinage", coins(5_000), 1.75f, false)
            .addUpgradeRequirement("Bronze Shells")
            .addGeneratorRequirement("Coins")

            .addUpgrade("Chinese Coins", coins(150_000), 2, false)
            .addUpgradeRequirement("Lydian Coinage")

            .addUpgrade("Interest", coins(400_000), 1.75f, false)
            .addUpgradeRequirement("Recordkeeping")
            .addGeneratorRequirement("Coins")

            .addUpgrade("Usury", coins(2e6), 2, false)
            .addUpgradeRequirement("Interest")

            .addUpgrade("Roman Coinage", coins(5e6), 2, false)
            .addUpgradeRequirement("Lydian Coinage")
            .addUpgradeRequirement("Chinese Coins")

            .addUpgrade("Carolingian Coinage", coins(1e7), 2, false)
            .addUpgradeRequirement("Roman Coinage")

            .addUpgrade("Negotiable Instruments", coins(2e7), 2.5f, false)
            .addGeneratorRequirement("Coins")
            .addUpgradeRequirement("Usury")

            .addUpgrade("Banking", coins(8e7), 3, false)
            .addUpgradeRequirement("Usury")

            .addUpgrade("Iconography", coins(3e8), 4, false)
            .addGeneratorRequirement("Coins")
            .addUpgradeRequirement("Negotiable Instruments")

            .addUpgrade("Deposit Lending", coins(1e12), 2.5f, false)
            .addUpgradeRequirement("Banking")
            .addGeneratorRequirement("Paper Money")

            .addUpgrade("Counterfeiting", coins(5e12), 8, false)
            .addUpgradeRequirement("Iconography")

            .addUpgrade("Credit", paper(300_000), 11, false)
            .addUpgradeRequirement("Interest")
            .addGeneratorRequirement("Paper Money")

            .addUpgrade("Piece of Eight", paper(7e6), 101, false)
            .addUpgradeRequirement("Carolingian Coinage")
            .addGeneratorRequirement("Paper Money")

            .addUpgrade("Taxation", electronic(900), 5001, false)
            .addUpgradeRequirement("Government Mints")
            .addGeneratorRequirement("Electronic Money")
            .generator();

    Generator paper = builder.createGenerator("Paper Money", coins(1e9), 1.11f, paper(1))
            .addGeneratorRequirement("Coins")
            .addUpgradeRequirement("Negotiable Instruments")

            .addUpgrade("Flying Money", paper(1_500), 2, false)
            .addUpgradeRequirement("Negotiable Instruments")
            .addGeneratorRequirement("Paper Money")

            .addUpgrade("Central Bank", paper(20_000), 3, false)
            .addUpgradeRequirement("Banking")
            .addGeneratorRequirement("Paper Money")

            .addUpgrade("Gold Standard", paper(750_000), 4, false)
            .addUpgradeRequirement("Central Bank")

            .addUpgrade("Fiat Money", paper(3e6), 4, false)
            .addUpgradeRequirement("Credit")
            .addUpgradeRequirement("Gold Standard")

            .addUpgrade("Stock Market", paper(1e8), 3, false)
            .addGeneratorRequirement("Paper Money")
            .addUpgradeRequirement("Fiat Money")

            .addUpgrade("Speculative Bubbles", paper(4e8), 6, false)
            .addUpgradeRequirement("Stock Market")

            .addUpgrade("Inflation", paper(3e11), 21, false)
            .addUpgradeRequirement("Deposit Lending")
            .addUpgradeRequirement("Fiat Money")

            .addUpgrade("ATM", electronic(500_000), 51, false)
            .addUpgradeRequirement("Payment Cards")

            .addUpgrade("Euro", future(2.5e9), 1_001, false)
            .addUpgradeRequirement("Fiat Money")
            .generator();

    Generator electronic = builder.createGenerator("Electronic Money", paper(1e9), 1.13f, electronic(1))
            .addGeneratorRequirement("Paper Money")
            .addUpgradeRequirement("Fiat Money")

            .addUpgrade("Information Security", electronic(10_000), 2, false)
            .addGeneratorRequirement("Electronic Money")

            .addUpgrade("Payment Cards", electronic(50_000), 2, false)
            .addGeneratorRequirement("Electronic Money")

            .addUpgrade("Online Banking", electronic(200_000), 5, false)
            .addGeneratorRequirement("Electronic Money")

            .addUpgrade("e-commerce", electronic(6e6), 6, false)
            .addUpgradeRequirement("Online Banking")

            .addUpgrade("Wire Transfer", electronic(5e7), 2.5f, false)
            .addUpgradeRequirement("Information Security")
            .addUpgradeRequirement("Online Banking")

            .addUpgrade("Mobile Payment", electronic(1.5e8), 4, false)
            .addUpgradeRequirement("Payment Cards")

            .addUpgrade("ETF", electronic(4e8), 3, false)
            .addUpgradeRequirement("Stock Market")

            .addUpgrade("Bank Run", future(2e10), 26, false)
            .addUpgradeRequirement("Inflation")

            .addUpgrade("Algorithmic Trading", future(3e11), 6, false)
            .addUpgradeRequirement("ETF")

            .generator();

    Generator future = builder.createGenerator("Future Money", electronic(1e9), 1.15f, future(400_000))
            .addGeneratorRequirement("Electronic Money")
            .addUpgradeRequirement("Mobile Payment")

            .addUpgrade("Darwinium Cube", future(1), 3, false)
            .addUpgradeRequirement("Virtual Currencies")

            .addUpgrade("Blockchain", future(2e9), 3, false)
            .addGeneratorRequirement("Future Money")

            .addUpgrade("Cryptocurrency", future(1e11), 3, false)
            .addUpgradeRequirement("Blockchain")

            .addUpgrade("Virtual Currencies", future(7.5e11), 2.5f, false)
            .addUpgradeRequirement("Mobile Payment")
            .addUpgradeRequirement("Algorithmic Trading")

            .addUpgrade("What's next?", future(9e11), 3, false)
            .addUpgradeRequirement("Cryptocurrency")
            .addUpgradeRequirement("Virtual Currencies")

            .addUpgrade("Unregulated", future(2e12), 3.5f, false)
            .addUpgradeRequirement("Cryptocurrency")

            .addUpgrade("One world, One Currency?", future(1e13), 3, false)
            .addUpgradeRequirement("What's next?")

            .addUpgrade("Decentralized Utopia?", future(4e13), 3, false)
            .addUpgradeRequirement("What's next?")

            .addUpgrade("Post-scarcity Society?", future(1e14), 2, false)
            .addUpgradeRequirement("What's next?")
            .generator();

    builder.resolveRequirements();
    STATE.updateEfficiency(GARDEN);
    STATE.setBoost(4);

    setGeneratorCount(future, 0);
    setGeneratorCount(electronic, 0);
    setGeneratorCount(paper, 0);
    setGeneratorCount(coins, 87);
    setGeneratorCount(commodity, 370);

    String[] boughtUpdates = {
            "Debt",
            "Tally Sticks",
            "Bartering",
            "Wampum",
            "Recordkeeping",
            "Intrinsic Value",
            "Precious Metals",
            "Metallurgy",
            "Cowrie Shells",
            "Bronze Shells",
            "Government Mints",
            "Shekels",
            "Rai Stones",
//            "Quipu",

            "Lydian Coinage",
            "Chinese Coins",
            "Interest",
            "Usury",
            "Roman Coinage",
            "Carolingian Coinage"
    };
    for (String upgradeName : boughtUpdates) {
        STATE.setUpgradeBought(GARDEN.getUpgrade(upgradeName));
    }
    STATE.updateEfficiency(GARDEN);

    GardenState state = STATE.copy();
    List<String> actions = new ArrayList<>();
    printUnlocked(GARDEN, state, actions);
    candidateApproach(state, actions);

    actions.forEach(System.out::println);
}

private static void candidateApproach(GardenState state, List<String> actions) {
    boolean possibleUnlock = false;
    for (int i = 0; i < 20 || !possibleUnlock; i += 1) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map<CurrencyMapping, ImprovementDescription> improvementDescriptions =
                ImprovementCalculator.calculateImprovement(GARDEN, state);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println();

        possibleUnlock = false;
        Map<Improvement, Set<Map.Entry<CurrencyMapping, ImprovementDescription>>> improvementMap = new HashMap<>();
        for (Map.Entry<CurrencyMapping, ImprovementDescription> entry : improvementDescriptions.entrySet()) {
            CurrencyMapping currencyMapping = entry.getKey();
            Improvement improvement = entry.getValue().improvement();
            Set<Map.Entry<CurrencyMapping, ImprovementDescription>> set = improvementMap.get(improvement);
            if (set == null) {
                set = new HashSet<>();
                set.add(entry);
                improvementMap.put(improvement, set);
            } else {
                set.add(entry);
            }
        }

        for (String fromCurrency : CURRENCIES) {
            for (String toCurrency : CURRENCIES) {
                CurrencyMapping mapping = new CurrencyMapping(fromCurrency, toCurrency);
                for (Improvement improvement : improvementMap.keySet()) {
                    if (improvement.getMapping().equals(mapping)) {
                        actions.add(String.format("%s : %s", mapping.asString(), improvement.getName()));
                    }
                }
            }
        }

        for (Map.Entry<Improvement, Set<Map.Entry<CurrencyMapping, ImprovementDescription>>> entry :
                improvementMap.entrySet()) {
            Improvement improvement = entry.getKey();
            if (improvement instanceof GeneratorImprovement(Generator generator, GeneratorState generatorState)) {
                int count = generatorState.count();
                for (Map.Entry<CurrencyMapping, ImprovementDescription> currencyEntry : entry.getValue()) {
                    CurrencyMapping currencyMapping = currencyEntry.getKey();
                    ImprovementDescription improvementDescription = currencyEntry.getValue();
                    actions.add(String.format("(%d - %s) Generator %s: %d -> %d : %s", i + 1,
                            currencyMapping.asString(), generator.getName(), count, count + 1,
                            improvementDescription.description()));

                }
                state.setGeneratorCount(generator, count + 1);
                if (count == 0) {
                    printUnlocked(GARDEN, state, actions);
                    possibleUnlock = true;
                }
            } else if (improvement instanceof UpgradeImprovement upgradeImprovement) {
                Upgrade upgrade = upgradeImprovement.upgrade();
                for (Map.Entry<CurrencyMapping, ImprovementDescription> currencyEntry : entry.getValue()) {
                    UpgradeEffect effect = upgrade.getEffects().getFirst();
                    CurrencyMapping currencyMapping = currencyEntry.getKey();
                    ImprovementDescription improvementDescription = currencyEntry.getValue();
                    actions.add(String.format("(%d - %s) Upgrade %s (%s) : %s", i + 1, currencyMapping.asString(),
                            upgrade.getName(), effect.generator().getName(), improvementDescription.description()));
                }
                state.setUpgradeBought(upgrade);
                state.updateEfficiency(GARDEN);
                printUnlocked(GARDEN, state, actions);
                possibleUnlock = true;
            } else {
                throw new NullPointerException();
            }
            actions.add("");
        }
        if (improvementMap.size() > 1) {
            break;
        }
    }
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

package org.joelson.cts.calculator.service;

import org.joelson.cts.calculator.model.Amount;
import org.joelson.cts.calculator.model.Calculator;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.GardenState;
import org.joelson.cts.calculator.model.GardenWithState;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.GeneratorState;
import org.joelson.cts.calculator.model.Simulation;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final Calculator calculator;

    public CalculatorService() {
        this.calculator = buildCalculator();
    }

    public Calculator getCalculator() {
        return calculator;
    }

    private static Calculator buildCalculator() {
        Calculator calculator = new Calculator();

        Simulation primary = new Simulation("Primary Simulation");
        calculator.addSimulation(primary);
        String ideaCurrency = "Idea";
        Garden ancient = new Garden("Ancient Garden");
        ancient.addCurrency(ideaCurrency);
        primary.addGardenWithState(withState(ancient));
        Garden modern = new Garden("Modern Garden");
        modern.addCurrency(ideaCurrency);
        primary.addGardenWithState(withState(modern));

        Simulation beyond = new Simulation("Beyond");
        calculator.addSimulation(beyond);
        Simulation mesozoicValley = new Simulation("Mezosoic Valley");
        calculator.addSimulation(mesozoicValley);

        Simulation lifeAfterApocalypse = new Simulation("Life after Apocalypse");
        calculator.addSimulation(lifeAfterApocalypse);
        String laaCurrency = "L.A.A.";
        Garden extinction = new Garden("Extinction");
        GardenState extinctionState = new GardenState();
        extinction.addCurrency(laaCurrency);
        lifeAfterApocalypse.addGardenWithState(new GardenWithState(extinction, extinctionState));
        Generator luca = new Generator("L.U.C.A.", new Amount(laaCurrency, 40), 1.05f, new Amount(laaCurrency, 1));
        extinction.addGenerator(luca);
        extinctionState.setGeneratorState(luca, new GeneratorState(753, 1));

        createUpgrade(extinction, extinctionState, luca, "Trilobites", 500, 1.5f);
        createUpgrade(extinction, extinctionState, luca, "Cambrian Explosion", 50, 1.5f);
        createUpgrade(extinction, extinctionState, luca, "Ordovician Extinction", 1e8f, 2);
//        createUpgrade(extinction, extinctionState, luca, "Trilobite World", 10000, 2.5f);
//        createUpgrade(extinction, extinctionState, luca, "Asteroid Bombardment", 70000, 2);
//        createUpgrade(extinction, extinctionState, luca, "Continental Collision", 140000, 7);
//        createUpgrade(extinction, extinctionState, luca, "Carbon Tipping Point", 2000000, 2.25f);
//        createUpgrade(extinction, extinctionState, luca, "Ice Age!", 10000000, 2);
//        createUpgrade(extinction, extinctionState, luca, "Anoxic Oceans", 30000000, 9);

        Upgrade placoderms = new Upgrade("Placoderms", new Amount(laaCurrency, 3000000));
        placoderms.addEffect(new UpgradeEffect(luca, 16));
        extinction.addUpgrade(placoderms);
        extinctionState.setUpgradeBought(placoderms);

        createUpgrade(extinction, extinctionState, luca, "Devonian Extinction", 4.5e10f, 6);

        Upgrade tetrapods = new Upgrade("Tetrapods", new Amount(laaCurrency, 6e8f));
        tetrapods.addEffect(new UpgradeEffect(luca, 11));
        extinction.addUpgrade(tetrapods);
        extinctionState.setUpgradeBought(tetrapods);

        createUpgrade(extinction, extinctionState, luca, "Permian Extinction", 1e14f, 12);
        createUpgrade(extinction, extinctionState, luca, "Archosaurs", 1.5e11f, 11);
        createUpgrade(extinction, extinctionState, luca, "Triassic Extinction", 3e15f, 16);

        Upgrade tyrannosaurusRex = new Upgrade("Tyrannosaurus Rex", new Amount(laaCurrency, 3.5e13f));
        tyrannosaurusRex.addEffect(new UpgradeEffect(luca, 11));
        extinction.addUpgrade(tyrannosaurusRex);
        extinctionState.setUpgradeBought(tyrannosaurusRex);

        Generator dinosaurs = new Generator("Reign of Dinosaurs", new Amount(laaCurrency, 8e12f), 1.12f,
                new Amount(laaCurrency, 5e9f));
        extinction.addGenerator(dinosaurs);
        extinctionState.setGeneratorState(dinosaurs, new GeneratorState(103, 1));
        Upgrade deccanDeathTraps = new Upgrade("Deccan Death Traps", new Amount(laaCurrency, 3.75e14f));
        deccanDeathTraps.addEffect(new UpgradeEffect(dinosaurs, 6));
        extinction.addUpgrade(deccanDeathTraps);
        extinctionState.setUpgradeBought(deccanDeathTraps);
        Upgrade killerSpaceRock = new Upgrade("Killer Space Rock!", new Amount(laaCurrency, 4.5e15f));
        killerSpaceRock.addEffect(new UpgradeEffect(dinosaurs, 4));
        extinction.addUpgrade(killerSpaceRock);
        extinctionState.setUpgradeBought(killerSpaceRock);
        Upgrade shookAndBoom = new Upgrade("Shook and Boom", new Amount(laaCurrency, 3.75e16f));
        shookAndBoom.addEffect(new UpgradeEffect(dinosaurs, 6));
        extinction.addUpgrade(shookAndBoom);
        extinctionState.setUpgradeBought(shookAndBoom);
        Upgrade quakeAndSlide = new Upgrade("Quake and Slide", new Amount(laaCurrency, 3.25e17f));
        quakeAndSlide.addEffect(new UpgradeEffect(dinosaurs, 4.5f));
        extinction.addUpgrade(quakeAndSlide);
        extinctionState.setUpgradeBought(quakeAndSlide);
        Upgrade metoriteBombs = new Upgrade("Meteorite Bombs", new Amount(laaCurrency, 1.75e18f));
        metoriteBombs.addEffect(new UpgradeEffect(dinosaurs, 2.25f));
        extinction.addUpgrade(metoriteBombs);
        extinctionState.setUpgradeBought(metoriteBombs);
        Upgrade broiledEarth = new Upgrade("Broiled Earth", new Amount(laaCurrency, 4.25e18f));
        broiledEarth.addEffect(new UpgradeEffect(dinosaurs, 3));
        extinction.addUpgrade(broiledEarth);
        extinctionState.setUpgradeBought(broiledEarth);
        Upgrade dayIntoNight = new Upgrade("Day into Night", new Amount(laaCurrency, 6.71e19f));
        dayIntoNight.addEffect(new UpgradeEffect(dinosaurs, 6));
        extinction.addUpgrade(dayIntoNight);
        extinctionState.setUpgradeBought(dayIntoNight);
        Upgrade cretaceousExtinction = new Upgrade("Cretaceous Extinction", new Amount(laaCurrency, 1.05e20f));
        cretaceousExtinction.addEffect(new UpgradeEffect(luca, 41));
        cretaceousExtinction.addEffect(new UpgradeEffect(dinosaurs, 0));
        extinction.addUpgrade(cretaceousExtinction);
        extinctionState.setUpgradeBought(cretaceousExtinction, false);

        extinctionState.updateEfficiency(extinction);

        return calculator;
    }

    private static void createUpgrade(
            Garden garden, GardenState state, Generator generator, String name, float cost, float efficiency) {
        Upgrade upgrade = new Upgrade(name, new Amount("L.A.A.", cost));
        upgrade.addEffect(new UpgradeEffect(generator, efficiency));
        garden.addUpgrade(upgrade);
        state.setUpgradeBought(upgrade);
    }

    private static GardenWithState withState(Garden garden) {
        return new GardenWithState(garden, new GardenState());
    }
}

package org.joelson.cts.calculator.service;

import org.joelson.cts.calculator.model.Calculator;
import org.joelson.cts.calculator.model.Currency;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
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
        Currency ideaCurrency = new Currency("Idea");
        Garden ancient = new Garden("Ancient Garden", ideaCurrency);
        primary.addGarden(ancient);
        Garden modern = new Garden("Modern Garden", ideaCurrency);
        primary.addGarden(modern);

        Simulation beyond = new Simulation("Beyond");
        calculator.addSimulation(beyond);
        Simulation mesozoicValley = new Simulation("Mezosoic Valley");
        calculator.addSimulation(mesozoicValley);

        Simulation lifeAfterApocalypse = new Simulation("Life after Apocalypse");
        calculator.addSimulation(lifeAfterApocalypse);
        Currency laaCurrency = new Currency("L.A.A.");
        Garden extinction = new Garden("Extinction", laaCurrency);
        lifeAfterApocalypse.addGarden(extinction);
        Generator luca = new Generator("L.U.C.A.", 40, 1.05f);
        luca.setCount(700);
        extinction.addGenerator(luca);
        Generator dinosaurs = new Generator("Reign of Dinosaurs", 8e12f, 1.12f);
        dinosaurs.setCount(103);
        extinction.addGenerator(dinosaurs);
        Upgrade shookAndBoom = new Upgrade("Shook and Boom", 3.75e16f, true);
        shookAndBoom.addEffect(new UpgradeEffect(dinosaurs, 6));
        extinction.addUpgrade(shookAndBoom);
        Upgrade quakeAndSlide = new Upgrade("Quake and Slide", 3.25e17f, true);
        quakeAndSlide.addEffect(new UpgradeEffect(dinosaurs, 4.5f));
        extinction.addUpgrade(quakeAndSlide);
        Upgrade metoriteBombs = new Upgrade("Meteorite Bombs", 1.75e18f, true);
        metoriteBombs.addEffect(new UpgradeEffect(dinosaurs, 2.25f));
        extinction.addUpgrade(metoriteBombs);
        Upgrade broiledEarth = new Upgrade("Broiled Earth", 4.25e18f, true);
        broiledEarth.addEffect(new UpgradeEffect(dinosaurs, 3));
        extinction.addUpgrade(broiledEarth);
        Upgrade dayIntoNight = new Upgrade("Day into Night", 6.71e19f, true);
        dayIntoNight.addEffect(new UpgradeEffect(dinosaurs, 6));
        extinction.addUpgrade(dayIntoNight);
        Upgrade cretaceousExtinction = new Upgrade("Cretaceous Extinction", 1.05e20f, true);
        cretaceousExtinction.addEffect(new UpgradeEffect(luca, 41));
        cretaceousExtinction.addEffect(new UpgradeEffect(dinosaurs, 0));
        extinction.addUpgrade(cretaceousExtinction);

        return calculator;
    }
}

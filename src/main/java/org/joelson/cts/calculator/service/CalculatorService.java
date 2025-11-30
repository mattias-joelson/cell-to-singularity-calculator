package org.joelson.cts.calculator.service;

import org.joelson.cts.calculator.model.Calculator;
import org.joelson.cts.calculator.model.Currency;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Simulation;
import org.joelson.cts.calculator.model.Upgrade;
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
        luca.setCount(601);
        extinction.addGenerator(luca);
        Generator dinosaurs = new Generator("Reign of Dinosaurs", 8e12f, 1.12f);
        dinosaurs.setCount(40);
        extinction.addGenerator(dinosaurs);
        Upgrade shookAndBoom = new Upgrade(dinosaurs, "Shook and Boom");
        extinction.addUpgrade(shookAndBoom);
        Upgrade quakeAndSlide = new Upgrade(dinosaurs, "Quake and Slide");
        extinction.addUpgrade(quakeAndSlide);
        Upgrade metoriteBombs = new Upgrade(dinosaurs, "Meteorite Bombs");
        extinction.addUpgrade(metoriteBombs);

        return calculator;
    }
}

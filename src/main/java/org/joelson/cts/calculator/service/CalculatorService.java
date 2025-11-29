package org.joelson.cts.calculator.service;

import org.joelson.cts.calculator.model.Calculator;
import org.joelson.cts.calculator.model.Currency;
import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Simulation;
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
        Simulation extinction = new Simulation("Extinction");
        calculator.addSimulation(extinction);

        return calculator;
    }
}

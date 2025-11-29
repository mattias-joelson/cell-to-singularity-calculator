package org.joelson.cts.calculator.service;

import org.joelson.cts.calculator.model.Calculator;
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
        Garden ancient = new Garden("Ancient Garden");
        primary.addGarden(ancient);

        Simulation beyond = new Simulation("Beyond");
        calculator.addSimulation(beyond);
        Simulation mesozoicValley = new Simulation("Mezosoic Valley");
        calculator.addSimulation(mesozoicValley);
        Simulation extinction = new Simulation("Extinction");
        calculator.addSimulation(extinction);

        return calculator;
    }
}

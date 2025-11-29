package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final List<Simulation> simulations;

    public Calculator() {
        simulations = new ArrayList<>();
    }

    public void addSimulation(Simulation simulation) {
        simulations.add(simulation);
    }

    public List<Simulation> getSimulations() {
        return simulations;
    }
}

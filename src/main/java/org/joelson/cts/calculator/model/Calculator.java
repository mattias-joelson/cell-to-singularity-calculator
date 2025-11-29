package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.Collection;

public class Calculator {

    private final Collection<Simulation> simulations;

    public Calculator() {
        simulations = new ArrayList<>();
    }

    public void addSimulation(Simulation simulation) {
        simulations.add(simulation);
    }

    public Collection<Simulation> getSimulations() {
        return simulations;
    }
}

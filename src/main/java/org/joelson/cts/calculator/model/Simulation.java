package org.joelson.cts.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final String name;
    private final List<GardenWithState> gardenWithStates;

    public Simulation(String name) {
        this.name = name;
        this.gardenWithStates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGardenWithState(GardenWithState garden) {
        gardenWithStates.add(garden);
    }

    public List<GardenWithState> getGardenWithStates() {
        return gardenWithStates;
    }
}

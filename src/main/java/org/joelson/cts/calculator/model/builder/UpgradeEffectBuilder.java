package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Garden;
import org.joelson.cts.calculator.model.Generator;
import org.joelson.cts.calculator.model.Upgrade;
import org.joelson.cts.calculator.model.UpgradeEffect;

public class UpgradeEffectBuilder {

    private final Garden garden;
    private final Generator generator;

    public UpgradeEffectBuilder(Garden garden, Generator generator) {
        this.garden = garden;
        this.generator = generator;
    }

    public UpgradeEffectBuilder addEfficiencyEffect(String upgradeName, float efficiency) {
        return addEffect(upgradeName, efficiency, 1, false);
    }

    public UpgradeEffectBuilder addSpeedEffect(String upgradeName, float speed) {
        return addEffect(upgradeName, 1, speed, false);
    }

    public UpgradeEffectBuilder addAutomatedEffect(String upgradeName) {
        return addEffect(upgradeName, 1, 1, true);
    }

    private UpgradeEffectBuilder addEffect(String upgradeName, float efficiency, float speed, boolean automated) {
        Upgrade upgrade = garden.getUpgrade(upgradeName);
        if (upgrade == null) {
            throw new IllegalStateException("No upgraded named " + upgradeName + " found.");
        }
        UpgradeEffect effect = new UpgradeEffect(generator, efficiency, speed, automated);
        upgrade.addEffect(effect);
        return this;
    }
}

package org.joelson.cts.calculator.model.builder;

import org.joelson.cts.calculator.model.Unlockable;

record UnresolvedGeneratorRequirement(Unlockable unlockable, String generatorName, int count)
        implements UnresolvedRequirement {

}

package org.joelson.cts.calculator.util;

import java.time.Duration;

public class DurationToolkit {

    private DurationToolkit() throws InstantiationException {
        throw new InstantiationException("Should not be instantiated.");
    }

    public static String durationString(double seconds) {
        Duration duration = Duration.ofSeconds(Math.round(seconds));
        if (duration.toDays() > 1) {
            return String.format("%d days %d:%02d:%02d", duration.toDays(), duration.toHoursPart(),
                    duration.toMinutesPart(), duration.toSecondsPart());
        } else if (duration.toDays() > 0) {
            return String.format("1 day %d:%02d:%02d", duration.toHoursPart(), duration.toMinutesPart(),
                    duration.toSecondsPart());
        } else {
            return String.format("%d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        }
    }
}

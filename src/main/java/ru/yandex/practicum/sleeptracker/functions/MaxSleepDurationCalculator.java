package ru.yandex.practicum.sleeptracker.functions;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESKRIPTION = "Максимальная продолжительность сна в минутах";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {

        long maxDuration = sleepEntries.stream().map(entry -> Duration.between(entry.getStartTime(), entry.getEndTime())).max(Duration::compareTo).orElse(Duration.ofMinutes(0)).toMinutes();

        return new SleepAnalysisResult(DESKRIPTION, maxDuration);
    }
}

package ru.yandex.practicum.sleeptracker.functions;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinSleepDurationCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {
        if (sleepEntries.isEmpty()) {
            return new SleepAnalysisResult("Минимальная продолжительность сессии сна", "Нет данных");

        }
        Duration minDuration = sleepEntries.stream()
                .map(entry -> Duration.between(entry.getStartTime(), entry.getEndTime()))
                .min(Duration::compareTo)
                .orElse(Duration.ZERO);

        long minutes = minDuration.toMinutes();

        return new SleepAnalysisResult("Минимальная продолжительность сессии сна в минутах", minutes);
    }
}

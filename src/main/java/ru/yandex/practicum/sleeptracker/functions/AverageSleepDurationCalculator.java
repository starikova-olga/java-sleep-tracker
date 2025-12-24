package ru.yandex.practicum.sleeptracker.functions;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSleepDurationCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {
        if (sleepEntries.isEmpty()) {
            return new SleepAnalysisResult("Средняя продолжительность сессии сна", "Нет данных");
        }
        Duration duration = sleepEntries.stream()
                .map(entry -> Duration.between(entry.getStartTime(), entry.getEndTime()))
                .reduce(Duration.ZERO, Duration::plus);
        long averageMinutes = duration.toMinutes() / sleepEntries.size();
        return new SleepAnalysisResult("Средняя продолжительность сессии сна в минутах", averageMinutes);
    }
}

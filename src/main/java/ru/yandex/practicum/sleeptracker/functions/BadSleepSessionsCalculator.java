package ru.yandex.practicum.sleeptracker.functions;

import java.util.List;
import java.util.function.Function;

public class BadSleepSessionsCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {
        long badSleepCount = sleepEntries.stream()
                .filter(entry -> "bad".equalsIgnoreCase(entry.getQuality()))
                .count();
        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", badSleepCount);
    }
}

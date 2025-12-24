package ru.yandex.practicum.sleeptracker.functions;

import java.util.List;
import java.util.function.Function;

public class SleepSessionCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {
        if (sleepEntries.isEmpty()) {
            return new SleepAnalysisResult("Количество сессий сна", 0);
        }
        int sessionCount = sleepEntries.size();

        return new SleepAnalysisResult("Количество сессий сна", sessionCount);
    }
}

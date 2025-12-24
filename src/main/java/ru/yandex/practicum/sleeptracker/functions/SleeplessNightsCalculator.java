package ru.yandex.practicum.sleeptracker.functions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {
        long sleeplessNightsCount = sleepEntries.stream()
                .filter(this::isSleeplessNight)
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessNightsCount);
    }

    private boolean isSleeplessNight(SleepingSession entry) {
        LocalDateTime startTime = entry.getStartTime();
        LocalDateTime endTime = entry.getEndTime();

        // Проверка на переход через полночь
        if (startTime.toLocalDate().isBefore(endTime.toLocalDate())) {
            return true;
        }

        // Проверка на сон после 12  и пробуждение до 6 утра
        if (startTime.getHour() >= 12 && endTime.getHour() < 6) {
            return true;
        }

        return false;
    }
}


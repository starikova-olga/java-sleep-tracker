package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.AverageSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.MaxSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageSleepDurationCalculatorTest {
    // Проверка нахождения средней продолжительности сессии
    @Test
    public void averageDurationWithDifferentSessionsTest() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15), LocalDateTime.of(2025, 10, 2, 7, 30), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50), LocalDateTime.of(2025, 10, 3, 6, 40), "NORMAL"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 12, 0), LocalDateTime.of(2025, 10, 4, 13, 30), "BAD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 22, 0), LocalDateTime.of(2025, 10, 6, 0, 0), "GOOD"));

        AverageSleepDurationCalculator calculator = new AverageSleepDurationCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Средняя продолжительность сессии сна в минутах", result.getDescription());
        assertEquals((long) 278, result.getResult());
    }
    // Проверка при одинаковых сессиях сна
    @Test
    public void averageDurationWithEqualSessionsTest() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 0, 0), LocalDateTime.of(2025, 10, 4, 8, 0), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 0, 0), LocalDateTime.of(2025, 10, 5, 8, 0), "BAD"));

        AverageSleepDurationCalculator calculator = new AverageSleepDurationCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Средняя продолжительность сессии сна в минутах", result.getDescription());
        assertEquals((long) 480, result.getResult());
    }
}

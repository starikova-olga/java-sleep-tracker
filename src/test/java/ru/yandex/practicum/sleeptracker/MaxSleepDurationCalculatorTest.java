package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.MaxSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSleepDurationCalculatorTest {
    @Test
    public void maxDurationWithRealDataTest() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15), LocalDateTime.of(2025, 10, 2, 7, 30), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50), LocalDateTime.of(2025, 10, 3, 6, 40), "NORMAL"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 10, 13, 00), LocalDateTime.of(2025, 10, 10, 14, 30), "NORMAL"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 30, 23, 50), LocalDateTime.of(2025, 10, 30, 6, 30), "GOOD"));

        MaxSleepDurationCalculator calculator = new MaxSleepDurationCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Максимальная продолжительность сна в минутах", result.getDescription());
        assertEquals((long) 495, result.getResult());
    }

    @Test
    public void maxDurationWithEqualSessionsTest() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 0, 0), LocalDateTime.of(2025, 10, 4, 8, 0), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 0, 0), LocalDateTime.of(2025, 10, 5, 8, 0), "BAD"));

        MaxSleepDurationCalculator calculator = new MaxSleepDurationCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Максимальная продолжительность сна в минутах", result.getDescription());
        assertEquals((long) 480, result.getResult());
    }
}

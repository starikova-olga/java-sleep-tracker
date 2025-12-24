package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.MinSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSleepDurationCalculatorTest {
    @Test
    public void minDurationWithRealDataTest() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15), LocalDateTime.of(2025, 10, 2, 7, 30), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50), LocalDateTime.of(2025, 10, 3, 6, 40), "NORMAL"));

        SleepAnalysisResult result = new MinSleepDurationCalculator().apply(sessions);

        assertEquals("Минимальная продолжительность сессии сна в минутах", result.getDescription());
        assertEquals((long) 410, result.getResult());
    }

    @Test
    public void minDurationWithEqualSessionsTest() {
        List<SleepingSession> sessions = new ArrayList<>();

        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 0, 0), LocalDateTime.of(2025, 10, 4, 8, 0), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 0, 0), LocalDateTime.of(2025, 10, 5, 8, 0), "BAD"));

        MinSleepDurationCalculator calculator = new MinSleepDurationCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Минимальная продолжительность сессии сна в минутах", result.getDescription());
        assertEquals((long) 480, result.getResult());
    }
}


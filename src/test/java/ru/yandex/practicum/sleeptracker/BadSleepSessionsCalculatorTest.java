package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.BadSleepSessionsCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadSleepSessionsCalculatorTest {
    @Test
    public void badSleepCountWithMixedQualityTest() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15), LocalDateTime.of(2025, 10, 2, 7, 30), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50), LocalDateTime.of(2025, 10, 3, 6, 40), "NORMAL"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 0, 0), LocalDateTime.of(2025, 10, 4, 8, 0), "BAD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 0, 0), LocalDateTime.of(2025, 10, 5, 8, 0), "bad"));

        BadSleepSessionsCalculator calculator = new BadSleepSessionsCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Количество сессий с плохим качеством сна", result.getDescription());
        assertEquals((long) 2, result.getResult());
    }

    // Если плохих сессий нет
    @Test
    public void badSleepCountWithNoBadSessionsTest() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 15), LocalDateTime.of(2025, 10, 2, 7, 30), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 50), LocalDateTime.of(2025, 10, 3, 6, 40), "NORMAL"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 0, 0), LocalDateTime.of(2025, 10, 4, 8, 0), "NORMAL"));

        BadSleepSessionsCalculator calculator = new BadSleepSessionsCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Количество сессий с плохим качеством сна", result.getDescription());
        assertEquals((long) 0, result.getResult());
    }
}



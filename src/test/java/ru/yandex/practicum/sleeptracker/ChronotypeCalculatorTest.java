package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.ChronotypeCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChronotypeCalculatorTest {
    @Test
    public void chronotypeWithOwlEntriesTest() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 30), LocalDateTime.of(2025, 10, 2, 9, 30), "GOOD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 2, 0, 0), LocalDateTime.of(2025, 10, 2, 8, 0), "NORMAL"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 30), LocalDateTime.of(2025, 10, 5, 14, 15), "BAD"));
        sessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 20, 45), LocalDateTime.of(2025, 10, 4, 5, 0), "BAD"));

        ChronotypeCalculator calculator = new ChronotypeCalculator();
        SleepAnalysisResult result = calculator.apply(sessions);

        assertEquals("Хронотип пользователя", result.getDescription());
        assertEquals("Голубь", result.getResult());
    }
}













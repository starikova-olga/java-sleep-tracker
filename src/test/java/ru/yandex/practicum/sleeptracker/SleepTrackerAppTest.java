package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepSessionCounter;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {

    // Тест на проверку пустого списка сессий
    @Test
    public void emptyListTest() {
        List<SleepingSession> emptyList = new ArrayList<>();
        SleepAnalysisResult result = new SleepSessionCounter().apply(emptyList);
        assertEquals("Количество сессий сна", result.getDescription());
        assertEquals(0, result.getResult());

    }

    // Тест с одной сессией
    @Test
    public void singleSessionTest() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(8);
        String quality = "GOOD";
        List<SleepingSession> singleSessionList = new ArrayList<>();
        singleSessionList.add(new SleepingSession(startTime, endTime, quality));
        SleepAnalysisResult result = new SleepSessionCounter().apply(singleSessionList);
        assertEquals("Количество сессий сна", result.getDescription());
        assertEquals(1, (int) result.getResult());
    }
}




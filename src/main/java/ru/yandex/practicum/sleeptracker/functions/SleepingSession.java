package ru.yandex.practicum.sleeptracker.functions;

import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String quality;

    public SleepingSession(LocalDateTime startTime, LocalDateTime endTime, String quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quality = quality;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getQuality() {
        return quality;
    }

}

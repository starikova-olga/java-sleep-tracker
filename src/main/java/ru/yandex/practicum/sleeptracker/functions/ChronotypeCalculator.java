package ru.yandex.practicum.sleeptracker.functions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ChronotypeCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String OWL = "Сова";
    private static final String LARK = "Жаворонок";
    private static final String PIGEON = "Голубь";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepEntries) {
        Map<String, Long> chronotypeCounts = sleepEntries.stream()
                .filter(this::isValidEntry)
                .map(this::determineChronotype)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String mostFrequentChronotype = getMostFrequentChronotype(chronotypeCounts);
        return new SleepAnalysisResult("Хронотип пользователя", mostFrequentChronotype);
    }

    private boolean isValidEntry(SleepingSession entry) {
        LocalDateTime startTime = entry.getStartTime();
        LocalDateTime endTime = entry.getEndTime();
        if (startTime.getHour() >= 12 && endTime.getHour() < 22) {
            return false;
        }
        if (endTime.toLocalDate().isAfter(startTime.toLocalDate())) {
            return true;
        }  return endTime.toLocalDate().isAfter(startTime.toLocalDate());

    }

    private String determineChronotype(SleepingSession entry) {
        int startHour = entry.getStartTime().getHour();
        int endHour = entry.getEndTime().getHour();

        if (startHour > 23 && endHour > 9) {
            return OWL;
        } else if (startHour < 22 && endHour < 7) {
            return LARK;
        } else {
            return PIGEON;
        }
    }

    private String getMostFrequentChronotype(Map<String, Long> chronotypeCounts) {
        return chronotypeCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(PIGEON);
    }
}


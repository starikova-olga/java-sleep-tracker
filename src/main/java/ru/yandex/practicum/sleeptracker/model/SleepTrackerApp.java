package ru.yandex.practicum.sleeptracker.model;

import ru.yandex.practicum.sleeptracker.functions.AverageSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.BadSleepSessionsCalculator;
import ru.yandex.practicum.sleeptracker.functions.ChronotypeCalculator;
import ru.yandex.practicum.sleeptracker.functions.MaxSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.MinSleepDurationCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleepSessionCounter;
import ru.yandex.practicum.sleeptracker.functions.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.SleeplessNightsCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class SleepTrackerApp {
    private static final DateTimeFormatter LOG_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> ANALYTIC_FUNCTIONS = List.of
            (new SleepSessionCounter(), new MinSleepDurationCalculator(), new MaxSleepDurationCalculator(), new AverageSleepDurationCalculator(), new BadSleepSessionsCalculator(), new ChronotypeCalculator(), new SleeplessNightsCalculator());


    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if (args.length == 0) {
            System.out.println("Пожалуйста, укажите путь к файлу с логом сна.");
            return;
        }

        String logFilePath = "src/main/resources/sleep_log.txt";

        try {
            List<SleepAnalysisResult> results = analyzeData(logFilePath);
            for (SleepAnalysisResult result : results) {
                System.out.println("Description " + result.getDescription());
                System.out.println("Result " + result.getResult());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }

    public static List<SleepAnalysisResult> analyzeData(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<SleepingSession> records = reader.lines().map(SleepTrackerApp::parseLine).flatMap(Optional::stream).toList();

            if (records.isEmpty()) {
                return List.of(new SleepAnalysisResult("Ошибка", "Файл пуст или не содержит корректных записей."));
            }
            return ANALYTIC_FUNCTIONS.stream().map(function -> function.apply(records)).toList();
        }
    }


    public static Optional<SleepingSession> parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length == 3) {
            LocalDateTime startTime = LocalDateTime.parse(parts[0], LOG_TIME_FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(parts[1], LOG_TIME_FORMATTER);
            String quality = parts[2];
            return Optional.of(new SleepingSession(startTime, endTime, quality));
        } else {
            System.out.println("Ошибка формата строки: " + line);
            return Optional.empty();
        }
    }
}









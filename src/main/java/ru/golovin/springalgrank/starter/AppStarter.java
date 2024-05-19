package ru.golovin.springalgrank.starter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.algorithm.RankGenerator;
import ru.golovin.springalgrank.spam.database.simple.DatabaseSimpleSpammer;
import ru.golovin.springalgrank.spam.database.upgrade.DatabaseUpgradeSpammer;
import ru.golovin.springalgrank.spam.log.simple.LogSimpleSpammer;
import ru.golovin.springalgrank.spam.log.upgrade.LogUpgradeSpammer;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class AppStarter {

    private final RankGenerator rankGenerator;
    private final LogSimpleSpammer logSimpleSpammer;
    private final LogUpgradeSpammer logUpgradeSpammer;
    private final DatabaseSimpleSpammer databaseSimpleSpammer;
    private final DatabaseUpgradeSpammer databaseUpgradeSpammer;

    @PostConstruct
    public void init() {

        measureExecutionTime(() -> {
            databaseUpgradeSpammer.spam(10000);
            return 0;
        });

//        measureExecutionTime(() -> {
//            databaseUpgradeSpammer.spam(5_000_000);
//            return 0;
//        });


//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\10000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\10000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\10000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\10000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\100000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\100000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\100000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\100000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\200000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\200000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\200000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\200000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\300000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\300000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\300000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\300000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\400000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\400000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\400000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\400000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\500000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\500000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\500000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\500000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\700000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\700000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\700000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\700000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1000000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1000000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1000000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1000000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1500000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1500000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1500000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\1500000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\2000000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\2000000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\2000000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\2000000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\3000000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\3000000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\3000000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\3000000-ur.log");
//            return 0;
//        });
//
//        //======
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\5000000.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\5000000-r.log");
//            return 0;
//        });
//
//        measureExecutionTime(() -> {
//            logUpgradeSpammer.parseFileUnRank("F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\5000000-r.log",
//                    "F:\\system\\desktop\\Combinatorial-Generation-Engine\\logs\\5000000-ur.log");
//            return 0;
//        });

        //======
    }

    public static <T> void measureExecutionTime(Supplier<T> task) {
        long startTime = System.currentTimeMillis();
        task.get();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + " miliseconds");
    }
}

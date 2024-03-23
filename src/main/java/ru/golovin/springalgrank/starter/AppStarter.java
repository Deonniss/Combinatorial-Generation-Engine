package ru.golovin.springalgrank.starter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.algorithm.RankGenerator;
import ru.golovin.springalgrank.spam.database.simple.DatabaseSimpleSpammer;
import ru.golovin.springalgrank.spam.database.upgrade.DatabaseUpgradeSpammer;
import ru.golovin.springalgrank.spam.log.simple.LogSimpleSpammer;
import ru.golovin.springalgrank.spam.log.upgrade.LogUpgradeSpammer;

@Component
@RequiredArgsConstructor
public class AppStarter {

    private final RankGenerator rankGenerator;
    private final LogSimpleSpammer logSimpleSpammer;
    private final LogUpgradeSpammer logUpgradeSpammer;
    private final DatabaseSimpleSpammer databaseSimpleSpammer;
    private final DatabaseUpgradeSpammer databaseUpgradeSpammer;

    //4437053125

    @PostConstruct
    public void init() throws InterruptedException {

        long start = System.currentTimeMillis();
//        databaseSimpleSpammer.spam(1_000_000);
//        databaseSimpleSpammer.spam(10_000_000);
//        databaseUpgradeSpammer.spam(10_000_000);

        logUpgradeSpammer.parseFile(
                "Z:\\projects\\springAlgRank\\temp\\log_upgrade\\up-1_000_000_out-2024-03-23.0.log",
                "Z:\\projects\\springAlgRank\\temp\\log_upgrade\\up-parse-1_000_000_out-2024-03-23.0.log");
        logUpgradeSpammer.parseFile(
                "Z:\\projects\\springAlgRank\\temp\\log_upgrade\\up-100_000_out-2024-03-23.0.log",
                "Z:\\projects\\springAlgRank\\temp\\log_upgrade\\up-parse-100_000_out-2024-03-23.0.log");

//        logUpgradeSpammer.parse(BigInteger.valueOf(113140246));
//        logUpgradeSpammer.parse(BigInteger.valueOf(2622215));
//        logUpgradeSpammer.parse(BigInteger.valueOf(12695258));
//        logUpgradeSpammer.parse(BigInteger.valueOf(6257051));


//        logUpgradeSpammer.spam(100_000);
//        logSimpleSpammer.spam(10_000_000);

//        System.out.println(randomProvider.getRandomUser());
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//        }

        System.out.println(System.currentTimeMillis() - start);
    }
}

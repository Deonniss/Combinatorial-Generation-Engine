package ru.golovin.springalgrank.starter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.spam.database.simple.DatabaseSimpleSpammer;
import ru.golovin.springalgrank.spam.log.simple.LogSimpleSpammer;
import ru.golovin.springalgrank.spam.log.upgrade.LogUpgradeSpammer;

@Component
@RequiredArgsConstructor
public class AppStarter {

    private final LogSimpleSpammer logSimpleSpammer;
    private final LogUpgradeSpammer logUpgradeSpammer;
    private final DatabaseSimpleSpammer databaseSimpleSpammer;

    @PostConstruct
    public void init() throws InterruptedException {

        long start = System.currentTimeMillis();
//        databaseSimpleSpammer.spam(1_000_000);


//        logUpgradeSpammer.parseFile(
//                "Z:\\projects\\springAlgRank\\temp\\up-10_000_000_out-2024-03-22.0.log",
//                "Z:\\projects\\springAlgRank\\temp\\up-parse-out.log");
//        logUpgradeSpammer.parse(BigInteger.valueOf(113140246));
//        logUpgradeSpammer.parse(BigInteger.valueOf(2622215));
//        logUpgradeSpammer.parse(BigInteger.valueOf(12695258));
//        logUpgradeSpammer.parse(BigInteger.valueOf(6257051));


//        logUpgradeSpammer.spam(10_000);
//        logSimpleSpammer.spam(10_000_000);

//        System.out.println(randomProvider.getRandomUser());
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//        }

        System.out.println(System.currentTimeMillis() - start);
    }

    /*
22.03.24 15:14:48.789 - 1424086
22.03.24 15:14:48.789 - 2622215
22.03.24 15:14:48.789 - 12695258
22.03.24 15:14:48.789 - 6257051
22.03.24 15:14:48.789 - 14812360
22.03.24 15:14:48.789 - 16272307
22.03.24 15:14:48.789 - 8471679
22.03.24 15:14:48.789 - 13055129
 */
}

package ru.golovin.springalgrang.starter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrang.spam.log.simple.LogSimpleSpammer;
import ru.golovin.springalgrang.spam.log.upgrade.LogUpgradeSpammer;

import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class AppStarter {

    private final LogSimpleSpammer logSimpleSpammer;
    private final LogUpgradeSpammer logUpgradeSpammer;

    @PostConstruct
    public void init() throws InterruptedException {


        logUpgradeSpammer.parseFile(
                "Z:\\projects\\springAlgRang\\temp\\up-10_000_000_out-2024-03-22.0.log",
                "Z:\\projects\\springAlgRang\\temp\\up-parse-out.log");
//        logUpgradeSpammer.parse(BigInteger.valueOf(15148220));
//        logUpgradeSpammer.parse(BigInteger.valueOf(2622215));
//        logUpgradeSpammer.parse(BigInteger.valueOf(12695258));
//        logUpgradeSpammer.parse(BigInteger.valueOf(6257051));


//        logUpgradeSpammer.spam(10_000_000);
//        logSimpleSpammer.spam(10_000_000);

//        System.out.println(randomProvider.getRandomUser());
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//        }
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

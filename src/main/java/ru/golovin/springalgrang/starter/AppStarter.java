package ru.golovin.springalgrang.starter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrang.spam.log.simple.LogSimpleSpammer;

@Component
@RequiredArgsConstructor
public class AppStarter {

    private final LogSimpleSpammer logSimpleSpammer;

    @PostConstruct
    public void init() throws InterruptedException {

//        logSimpleSpammer.spam(10_000_000);

//        System.out.println(randomProvider.getRandomUser());
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//        }
    }
}

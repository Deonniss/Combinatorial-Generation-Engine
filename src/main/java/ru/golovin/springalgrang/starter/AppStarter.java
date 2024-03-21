package ru.golovin.springalgrang.starter;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppStarter {

    @PostConstruct
    public void init() throws InterruptedException {
        while (true) {
            System.out.println(System.currentTimeMillis());
            Thread.sleep(1000);
        }
    }
}

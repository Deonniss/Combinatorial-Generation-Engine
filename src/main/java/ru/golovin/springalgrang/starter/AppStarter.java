package ru.golovin.springalgrang.starter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrang.domain.repository.UserRepository;
import ru.golovin.springalgrang.spam.RandomEntityProvider;

@Component
@RequiredArgsConstructor
public class AppStarter {

    private final RandomEntityProvider randomProvider;

    @PostConstruct
    public void init() throws InterruptedException {
        System.out.println(randomProvider.getRandomUser());
//        while (true) {
//            System.out.println(System.currentTimeMillis());
//            Thread.sleep(1000);
//        }
    }
}

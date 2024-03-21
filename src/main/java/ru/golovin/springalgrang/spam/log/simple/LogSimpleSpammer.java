package ru.golovin.springalgrang.spam.log.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrang.spam.RandomEntityProvider;
import ru.golovin.springalgrang.spam.Spammer;
import ru.golovin.springalgrang.spam.log.Log;
import ru.golovin.springalgrang.spam.log.LogFactory;

@Component
@RequiredArgsConstructor
public class LogSimpleSpammer implements Spammer {

    private static final Log log = LogFactory.out.get(LogSimpleSpammer.class);

    private final RandomEntityProvider random;

    @Override
    public void spam(int count) {
        for (int i = 0; i < count; i++) {
            log.info("user - {}, {}, from - {}, status - {}, priority - {}",
                    random.getRandomUser(),
                    random.getRandomEvent(),
                    random.getRandomIp(),
                    random.getRandomStatus(),
                    random.getRandomPriority());
        }
    }
}

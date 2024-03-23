package ru.golovin.springalgrank.spam.database.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.spam.RandomEntityProvider;
import ru.golovin.springalgrank.spam.Spammer;
import ru.golovin.springalgrank.spam.log.Log;
import ru.golovin.springalgrank.spam.log.LogFactory;

@Component
@RequiredArgsConstructor
public class DatabaseSimpleSpammer implements Spammer {

    private static final Log log = LogFactory.out.get(DatabaseSimpleSpammer.class);

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

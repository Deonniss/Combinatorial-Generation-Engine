package ru.golovin.springalgrank.spam.database.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.domain.entity.SimpleSpam;
import ru.golovin.springalgrank.domain.repository.SimpleSpamRepository;
import ru.golovin.springalgrank.spam.RandomEntityProvider;
import ru.golovin.springalgrank.spam.Spammer;
import ru.golovin.springalgrank.spam.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSimpleSpammer implements Spammer {

    private final ThreadExecutor threadExecutor;
    private final RandomEntityProvider random;
    private final SimpleSpamRepository repository;
    private final int batchSize = 5_000;

    @Override
    public void spam(int count) {
        List<Runnable> tasks = new ArrayList<>();
        List<SimpleSpam> spams = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SimpleSpam spam = new SimpleSpam();
            spam.setUser(random.getRandomUser());
            spam.setEvent(random.getRandomEvent());
            spam.setIp(random.getRandomIp());
            spam.setStatus(random.getRandomStatus());
            spam.setPriority(random.getRandomPriority());
            spams.add(spam);
            if (i % batchSize == 0 && i > 0) {
                tasks.add(() -> repository.saveAll(new ArrayList<>(spams)));
                spams.clear();
            }
        }
        threadExecutor.execute(tasks);
        if (!spams.isEmpty()) {
            repository.saveAll(spams);
            spams.clear();
        }
    }
}

package ru.golovin.springalgrank.spam.database.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.domain.entity.SimpleDeNormSpam;
import ru.golovin.springalgrank.domain.entity.SimpleSpam;
import ru.golovin.springalgrank.domain.repository.SimpleDeSpamRepository;
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
    private final SimpleDeSpamRepository simpleDeSpamRepository;
    private final int batchSize = 5_000;

    public void spamDeNorm(int count) {
        List<Runnable> tasks = new ArrayList<>();
        List<SimpleDeNormSpam> spams = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SimpleDeNormSpam spam = new SimpleDeNormSpam();
            spam.setId((long) i + 10_000_000);
            spam.setUser(random.getRandomUser().getUsername());
            spam.setEvent(random.getRandomEvent().getType());
            spam.setIp(random.getRandomIp().getAddress());
            spam.setStatus(random.getRandomStatus().getName());
            spam.setPriority(random.getRandomPriority().getName());
            spams.add(spam);
            if (i % batchSize == 0 && i > 0) {
                simpleDeSpamRepository.saveAll(new ArrayList<>(spams));
                tasks.add(() -> simpleDeSpamRepository.saveAll(new ArrayList<>(spams)));
                spams.clear();
            }
        }
        threadExecutor.execute(tasks);
        if (!spams.isEmpty()) {
            simpleDeSpamRepository.saveAll(spams);
            spams.clear();
        }
    }

    @Override
    public void spam(int count) {
        List<Runnable> tasks = new ArrayList<>();
        List<SimpleSpam> spams = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SimpleSpam spam = new SimpleSpam();
            spam.setId((long) i + 1);
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

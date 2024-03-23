package ru.golovin.springalgrank.spam.database.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.domain.entity.SimpleSpam;
import ru.golovin.springalgrank.domain.repository.SimpleSpamRepository;
import ru.golovin.springalgrank.spam.RandomEntityProvider;
import ru.golovin.springalgrank.spam.Spammer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DatabaseSimpleSpammer implements Spammer {

    private final RandomEntityProvider random;
    private final SimpleSpamRepository repository;
    private final int batchSize = 5_000;

    @Override
    public void spam(int count) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
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
                tasks.add(new SaveTask(new ArrayList<>(spams)));
                spams.clear();
            }
        }
        for (Runnable task : tasks) {
            executor.execute(task);
        }
        if (!spams.isEmpty()) {
            repository.saveAll(spams);
            spams.clear();
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class SaveTask implements Runnable {
        private final List<SimpleSpam> spams;

        SaveTask(List<SimpleSpam> spams) {
            this.spams = spams;
        }

        @Override
        public void run() {
            repository.saveAll(spams);
        }
    }
}

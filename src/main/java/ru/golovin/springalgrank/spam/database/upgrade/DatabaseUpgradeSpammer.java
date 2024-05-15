package ru.golovin.springalgrank.spam.database.upgrade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.algorithm.RankGenerator;
import ru.golovin.springalgrank.domain.entity.UpgradeSpam;
import ru.golovin.springalgrank.domain.repository.UpgradeSpamRepository;
import ru.golovin.springalgrank.spam.Spammer;
import ru.golovin.springalgrank.spam.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseUpgradeSpammer implements Spammer {

    private final ThreadExecutor threadExecutor;
    private final UpgradeSpamRepository repository;
    private final RankGenerator rankGenerator;
    private final int batchSize = 5_000;

    @Override
    public void spam(int count) {
        List<Runnable> tasks = new ArrayList<>();
        List<UpgradeSpam> spams = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            UpgradeSpam spam = new UpgradeSpam();
//            spam.setId(1L + i);
            spam.setRank(rankGenerator.getRandomRank());
            spams.add(spam);
            if (i % batchSize == 0 && i > 0) {
                repository.saveAll(new ArrayList<>(spams));
//                tasks.add(() -> repository.saveAll(new ArrayList<>(spams)));
                spams.clear();
            }
        }
//        threadExecutor.execute(tasks);
        if (!spams.isEmpty()) {
            repository.saveAll(spams);
            spams.clear();
        }
    }
}

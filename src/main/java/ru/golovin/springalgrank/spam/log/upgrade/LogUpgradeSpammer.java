package ru.golovin.springalgrank.spam.log.upgrade;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.algorithm.AndOrTree;
import ru.golovin.springalgrank.algorithm.NodeType;
import ru.golovin.springalgrank.algorithm.RankingAlgorithm;
import ru.golovin.springalgrank.algorithm.Root;
import ru.golovin.springalgrank.spam.RandomEntityProvider;
import ru.golovin.springalgrank.spam.Spammer;
import ru.golovin.springalgrank.spam.log.Log;
import ru.golovin.springalgrank.spam.log.LogFactory;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.golovin.springalgrank.domain.entity.EntityType.*;

@Component
@RequiredArgsConstructor
public class LogUpgradeSpammer implements Spammer {

    private static final Log log = LogFactory.out.get(LogUpgradeSpammer.class);

    private final RandomEntityProvider random;
    private final Random randomUtil = new Random();
    private final AndOrTree tree = new AndOrTree();

    @PostConstruct
    private void initTree() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(random.getEntities(USER));
        lists.add(random.getEntities(EVENT));
        lists.add(random.getEntities(IP));
        lists.add(random.getEntities(STATUS));
        lists.add(random.getEntities(PRIORITY));
        tree.create(lists);
    }

    @Override
    public void spam(int count) {
        final Root root = new Root(NodeType.AND, 0, 0);
        for (int i = 0; i < count; i++) {
            List<Integer> point = new ArrayList<>();
            point.add(randomUtil.nextInt(random.getCountEntities(USER)));
            point.add(randomUtil.nextInt(random.getCountEntities(EVENT)));
            point.add(randomUtil.nextInt(random.getCountEntities(IP)));
            point.add(randomUtil.nextInt(random.getCountEntities(STATUS)));
            point.add(randomUtil.nextInt(random.getCountEntities(PRIORITY)));
            BigInteger rank = RankingAlgorithm.rank(root, point, tree);
            log.info(rank.toString());
        }
    }

    public void parseFile(String inputFilePath, String outputFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - "); // Разделение строки по символу "-"
                if (parts.length == 2) {
                    String timestamp = parts[0];
                    BigInteger value = new BigInteger(parts[1].trim());
                    String newLine = timestamp + " - " + RankingAlgorithm.unRank(value, tree);
                    pw.println(newLine);
                }
            }
            System.out.println("Преобразование завершено.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(BigInteger rank) {
        System.out.println(RankingAlgorithm.unRank(rank, tree));
    }
}

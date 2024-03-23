package ru.golovin.springalgrank.spam.log.upgrade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.algorithm.AndOrTree;
import ru.golovin.springalgrank.algorithm.RankGenerator;
import ru.golovin.springalgrank.algorithm.RankingAlgorithm;
import ru.golovin.springalgrank.spam.Spammer;
import ru.golovin.springalgrank.spam.ThreadExecutor;
import ru.golovin.springalgrank.spam.log.Log;
import ru.golovin.springalgrank.spam.log.LogFactory;

import java.io.*;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class LogUpgradeSpammer implements Spammer {

    private static final Log log = LogFactory.out.get(LogUpgradeSpammer.class);

    private final ThreadExecutor threadExecutor;
    private final RankGenerator rankGenerator;
    private final AndOrTree tree = new AndOrTree();

    @Override
    public void spam(int count) {
        for (int i = 0; i < count; i++) {
            log.info(rankGenerator.getRandomRank().toString());
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
}

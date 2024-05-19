package ru.golovin.springalgrank.spam.log.upgrade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.algorithm.*;
import ru.golovin.springalgrank.spam.RandomEntityProvider;
import ru.golovin.springalgrank.spam.Spammer;
import ru.golovin.springalgrank.spam.ThreadExecutor;
import ru.golovin.springalgrank.spam.log.Log;
import ru.golovin.springalgrank.spam.log.LogFactory;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class LogUpgradeSpammer implements Spammer {

    private static final Log log = LogFactory.out.get(LogUpgradeSpammer.class);

    private final RankGenerator rankGenerator;
    private final RandomEntityProvider randomEntityProvider;

    @Override
    public void spam(int count) {
        for (int i = 0; i < count; i++) {
            log.info(rankGenerator.getRandomRank().toString());
        }
    }

    public void parseFileRank(String inputFilePath, String outputFilePath) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)))) {

            List<Future<String>> futureResults = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String finalLine = line;
                Future<String> future = executorService.submit(() -> processLine(finalLine));
                futureResults.add(future);
            }

            for (Future<String> future : futureResults) {
                try {
                    String result = future.get();
                    if (result != null) {
                        pw.println(result);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Преобразование завершено.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private String processLine(String line) {
        String[] parts = line.split(" - ");
        if (parts.length == 2) {
            String timestamp = parts[0];

            parts[1] = parts[1].replaceAll("[\\[\\]]", "");
            String[] words = parts[1].split(", ");

            StringBuilder newLine = new StringBuilder();
            newLine.append(timestamp);
            newLine.append(" - ");
            List<Integer> v = new ArrayList<>();
            v.add(randomEntityProvider.getUser(words[0]));
            v.add(randomEntityProvider.getEvent(words[1]));
            v.add(randomEntityProvider.getIp(words[2]));
            v.add(randomEntityProvider.getStatus(words[3]));
            v.add(randomEntityProvider.getPriority(words[4]));
            return newLine.append(RankingAlgorithm.rank(new Root(NodeType.AND, 0, 0), v, rankGenerator.getTree())).toString();
        }
        return null;
    }

    public void parseFileUnRank(String inputFilePath, String outputFilePath) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)))) {
            List<Future<String>> futureResults = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String finalLine = line;
                Future<String> future = executorService.submit(() -> processLineForUnRank(finalLine));
                futureResults.add(future);
            }
            for (Future<String> future : futureResults) {
                try {
                    String result = future.get();
                    if (result != null) {
                        pw.println(result);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Преобразование завершено.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private String processLineForUnRank(String line) {
        String[] parts = line.split(" - ");
        if (parts.length == 2) {
            String timestamp = parts[0];
            BigInteger value = new BigInteger(parts[1].trim());
            StringBuilder newLine = new StringBuilder();
            newLine.append(timestamp);
            newLine.append(" - ");
            newLine.append(RankingAlgorithm.unRank(value, rankGenerator.getTree()));
            return newLine.toString();
        }
        return null;
    }


//    public void parseFileRankOneThread(String inputFilePath, String outputFilePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
//             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(" - ");
//                if (parts.length == 2) {
//                    String timestamp = parts[0];
//
//                    parts[1] = parts[1].replaceAll("[\\[\\]]", "");
//                    String[] words = parts[1].split(", ");
//
//                    StringBuilder newLine = new StringBuilder();
//                    newLine.append(timestamp);
//                    newLine.append( " - ");
//                    List<Integer> v = new ArrayList<>();
//                    v.add(randomEntityProvider.getUser(words[0]));
//                    v.add(randomEntityProvider.getEvent(words[1]));
//                    v.add(randomEntityProvider.getIp(words[2]));
//                    v.add(randomEntityProvider.getStatus(words[3]));
//                    v.add(randomEntityProvider.getPriority(words[4]));
//                    newLine.append(RankingAlgorithm.rank(new Root(NodeType.AND, 0, 0), v, rankGenerator.getTree()));
//                    pw.println(newLine);
//                }
//            }
//            System.out.println("Преобразование завершено.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void parseFileUnRankOneThread(String inputFilePath, String outputFilePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
//             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFilePath)))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(" - ");
//                if (parts.length == 2) {
//                    String timestamp = parts[0];
//                    BigInteger value = new BigInteger(parts[1].trim());
//                    StringBuilder newLine = new StringBuilder();
//                    newLine.append(timestamp);
//                    newLine.append( " - ");
//                    newLine.append(RankingAlgorithm.unRank(value, rankGenerator.getTree()));
//                    pw.println(newLine);
//                }
//            }
//            System.out.println("Преобразование завершено.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

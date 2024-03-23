package ru.golovin.springalgrank.algorithm;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.spam.RandomEntityProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.golovin.springalgrank.domain.entity.EntityType.*;

@Component
@RequiredArgsConstructor
public class RankGenerator {

    private final RandomEntityProvider random;
    private final Random randomUtil = new Random();
    private final AndOrTree tree = new AndOrTree();
    private final Root root = new Root(NodeType.AND, 0, 0);

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

    public AndOrTree getTree() {
        return tree;
    }

    public BigInteger getRandomRank() {
        List<Integer> point = new ArrayList<>();
        point.add(randomUtil.nextInt(random.getCountEntities(USER)));
        point.add(randomUtil.nextInt(random.getCountEntities(EVENT)));
        point.add(randomUtil.nextInt(random.getCountEntities(IP)));
        point.add(randomUtil.nextInt(random.getCountEntities(STATUS)));
        point.add(randomUtil.nextInt(random.getCountEntities(PRIORITY)));
        return RankingAlgorithm.rank(root, point, tree);
    }

    public List<String> parse(BigInteger rank) {
        return RankingAlgorithm.unRank(rank, tree);
    }

}

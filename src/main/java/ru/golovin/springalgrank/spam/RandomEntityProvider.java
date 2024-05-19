package ru.golovin.springalgrank.spam;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrank.domain.entity.*;
import ru.golovin.springalgrank.domain.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static ru.golovin.springalgrank.domain.entity.EntityType.*;

@Component
@RequiredArgsConstructor
public class RandomEntityProvider {

    private long DESIRED_LENGTH = 0;

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final IpRepository ipRepository;

    private final Random random = new Random();
    private final Map<EntityType, List<EntityField>> map = new HashMap<>();
    private final Map<EntityType, Map<String, Integer>> mapByName = new HashMap<>();

    @PostConstruct
    private void init() {
        map.put(USER, userRepository.findAllObjects());
        map.put(EVENT, eventRepository.findAllObjects());
        map.put(IP, ipRepository.findAllObjects());
        map.put(STATUS, statusRepository.findAllObjects());
        map.put(PRIORITY, priorityRepository.findAllObjects());

        Map<String, Integer> userFields = new HashMap<>();
        for (EntityField field : map.get(USER)) {
            userFields.put(field.getField(), field.getId().intValue() - 1);
        }
        mapByName.put(USER, userFields);

        Map<String, Integer> eventFields = new HashMap<>();
        for (EntityField field : map.get(EVENT)) {
            eventFields.put(field.getField(), field.getId().intValue() - 1);
        }
        mapByName.put(EVENT, eventFields);

        Map<String, Integer> ipFields = new HashMap<>();
        for (EntityField field : map.get(IP)) {
            ipFields.put(field.getField(), field.getId().intValue() - 1);
        }
        mapByName.put(IP, ipFields);

        Map<String, Integer> statusFields = new HashMap<>();
        for (EntityField field : map.get(STATUS)) {
            statusFields.put(field.getField(), field.getId().intValue() - 1);
        }
        mapByName.put(STATUS, statusFields);

        Map<String, Integer> priorityFields = new HashMap<>();
        for (EntityField field : map.get(PRIORITY)) {
            priorityFields.put(field.getField(), field.getId().intValue() - 1);
        }
        mapByName.put(PRIORITY, priorityFields);

        DESIRED_LENGTH = Math.max(userRepository.count(),
                Math.max(eventRepository.count(),
                        Math.max(ipRepository.count(),
                                Math.max(statusRepository.count(), priorityRepository.count()))));
    }

    public User getRandomUser() {
        return (User) map.get(USER).get(random.nextInt(map.get(USER).size()));
    }

    public Event getRandomEvent() {
        return (Event) map.get(EVENT).get(random.nextInt(map.get(EVENT).size()));
    }

    public Priority getRandomPriority() {
        return (Priority) map.get(PRIORITY).get(random.nextInt(map.get(PRIORITY).size()));
    }

    public Status getRandomStatus() {
        return (Status) map.get(STATUS).get(random.nextInt(map.get(STATUS).size()));
    }

    public Integer getIp(String ip) {
        return mapByName.get(IP).get(ip);
    }

    public Integer getUser(String u) {
        return mapByName.get(USER).get(u);
    }

    public Integer getEvent(String e) {
        return mapByName.get(EVENT).get(e);
    }

    public Integer getPriority(String p) {
        return mapByName.get(PRIORITY).get(p);
    }

    public Integer getStatus(String s) {
        return mapByName.get(STATUS).get(s);
    }

    public Ip getRandomIp() {
        return (Ip) map.get(IP).get(random.nextInt(map.get(IP).size()));
    }

    public int getCountEntities(EntityType type) {
        return map.get(type).size();
    }

    public List<String> getEntities(EntityType type) {
        List<String> users = map.get(type).stream()
                .map(EntityField::getField)
                .collect(Collectors.toList());
        return padList(users);
    }

    private List<String> padList(List<String> list) {
        int currentSize = list.size();
        if (currentSize < DESIRED_LENGTH) {
            for (int i = currentSize; i < DESIRED_LENGTH; i++) {
                list.add("");
            }
        }
        return list;
    }
}

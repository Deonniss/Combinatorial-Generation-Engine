package ru.golovin.springalgrang.spam;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrang.domain.entity.*;
import ru.golovin.springalgrang.domain.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static ru.golovin.springalgrang.domain.entity.EntityType.*;

@Component
@RequiredArgsConstructor
public class RandomEntityProvider {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final IpRepository ipRepository;

    private final Random random = new Random();
    private final Map<EntityType, List<Object>> map = new HashMap<>();

    @PostConstruct
    private void init() {
        map.put(USER, userRepository.findAllObjects());
        map.put(EVENT, eventRepository.findAllObjects());
        map.put(IP, ipRepository.findAllObjects());
        map.put(STATUS, statusRepository.findAllObjects());
        map.put(PRIORITY, priorityRepository.findAllObjects());
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

    public Ip getRandomIp() {
        return (Ip) map.get(IP).get(random.nextInt(map.get(IP).size()));
    }
}

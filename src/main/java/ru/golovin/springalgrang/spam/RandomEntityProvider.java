package ru.golovin.springalgrang.spam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.golovin.springalgrang.domain.entity.*;
import ru.golovin.springalgrang.domain.repository.*;

@Component
@RequiredArgsConstructor
public class RandomEntityProvider {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final IpRepository ipRepository;

    public User getRandomUser() {
        return userRepository.findRandom();
    }

    public Event getRandomEvent() {
        return eventRepository.findRandom();
    }

    public Priority getRandomPriority() {
        return priorityRepository.findRandom();
    }

    public Status getRandomStatus() {
        return statusRepository.findRandom();
    }

    public Ip getRandomIp() {
        return ipRepository.findRandom();
    }
}

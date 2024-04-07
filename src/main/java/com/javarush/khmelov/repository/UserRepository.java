package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class UserRepository extends QuestObjectRepository<User> {
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());

    public UserRepository() {
        map.put(1L, new User(1L, "Alisa", "qwerty", Role.USER));
        map.put(2L, new User(2L, "Bob", "", Role.GUEST));
        map.put(3L, new User(3L, "Carl", "admin", Role.ADMIN));
        map.put(4L, new User(4L, "Khmelov", "admin", Role.ADMIN));
    }

    public Stream<User> find(User pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()));
    }

    ;
}

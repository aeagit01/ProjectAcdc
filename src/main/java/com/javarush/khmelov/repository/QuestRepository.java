package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Quest;
import java.util.stream.Stream;

public class QuestRepository extends QuestObjectRepository<Quest>{

    public QuestRepository() {
//        map.put(1L, Quest.builder().id(1L).name("Quest 1").description("Description quiest 1").build());
//        map.put(2L, Quest.builder().id(2L).name("Quest 2").description("Description quiest 2").build());
//        map.put(3L, Quest.builder().id(3L).name("Quest 3").description("Description quiest 3").build());
//        map.put(4L, Quest.builder().id(4L).name("Quest 4").description("Description quiest 4").build());
    }
    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getDescription(), u.getDescription()))
                .filter(u -> nullOrEquals(pattern.getName(), u.getName()));
    }

}




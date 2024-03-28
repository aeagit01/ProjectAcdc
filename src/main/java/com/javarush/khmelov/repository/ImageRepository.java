package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.tools.Keys;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ImageRepository implements Repository{
    private int imageID;
    private final Map<Integer,String> map = new HashMap<Integer, String>();

    public ImageRepository(){
        map.put(1, Keys.WEBINF + "/map/image" + 1);
        map.put(1, Keys.WEBINF + "/map/image" + 2);
        map.put(1, Keys.WEBINF + "/map/image" + 3);
        map.put(1, Keys.WEBINF + "/map/image" + 4);
        map.put(1, Keys.WEBINF + "/map/image" + 5);
    }
    @Override
    public Collection<String> getAll() {
        return map.values();
    }

    @Override
    public Optional get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}

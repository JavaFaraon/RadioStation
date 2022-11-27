package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Presenter implements PresentersInterface {
    protected String name;
    List <Broadcast> broadCastsList = new ArrayList<>();

    public Presenter(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Broadcast> getBroadCastsList() {
        return broadCastsList;
    }

    @Override
    public void addBrodCast(Broadcast brodCast) {
        broadCastsList.add(brodCast);
    }
}

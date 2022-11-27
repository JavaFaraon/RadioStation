package org.example;
import java.util.List;

public interface PresentersInterface {
    String getName();

    List<Broadcast> getBroadCastsList();

    void addBrodCast(Broadcast brodCast);
}

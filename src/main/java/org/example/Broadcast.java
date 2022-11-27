package org.example;

import java.time.LocalDate;
import java.util.LinkedList;

public class Broadcast {
     private final LocalDate airDate;
     private final double durationOfBroadcast;
     private final PresentersInterface presenter;
     LinkedList<TypeOfBroadcasts> typeOfBroadcastsList = new LinkedList<>();

     public Broadcast(LocalDate airDate, double durationOfBroadcast, PresentersInterface presenter, LinkedList<TypeOfBroadcasts> typeOfBroadcastsList) {
          this.airDate = airDate;
          this.durationOfBroadcast = durationOfBroadcast;
          this.presenter = presenter;
          this.typeOfBroadcastsList = typeOfBroadcastsList;
     }
     public LocalDate getAirDate() {
          return airDate;
     }
     public double getDurationOfBroadcast() {
          return durationOfBroadcast;
     }
     public PresentersInterface getPresenter() {
          return presenter;
     }

     @Override
     public String toString() {
          return "Трансляция на " + airDate +
                  ", длительность - " + durationOfBroadcast + " мин" +
                  ", ведущий - " + presenter.getName() +
                  ", содержание: " + typeOfBroadcastsList;
     }
}

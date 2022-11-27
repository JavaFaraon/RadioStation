package org.example;

public abstract class TypeOfBroadcasts {
    double duration;
    static final String CURRENCY = "евро";

    public TypeOfBroadcasts(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public static String getCURRENCY() {
        return CURRENCY;
    }

    public abstract int getPrice();
}

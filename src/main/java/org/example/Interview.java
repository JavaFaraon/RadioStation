package org.example;

public class Interview extends TypeOfBroadcasts {

    String nameOfInterviewee;
    final int price = 30;

    @Override
    public String toString() {
        return "Interview of " + nameOfInterviewee +
                ", цена - " + price + " " +
                CURRENCY + "/мин" + ", duration: " + duration + "мин";
    }

    public Interview(double duration, String nameOfInterviewee) {
        super(duration);
        this.nameOfInterviewee = nameOfInterviewee;
    }

    public int getPrice() {
        return price;
    }
}

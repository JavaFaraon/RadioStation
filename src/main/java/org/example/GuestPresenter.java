package org.example;

public class GuestPresenter extends Presenter {
    String resume;
    public GuestPresenter(String name, String resume) {
        super(name);
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "GuestPresenter " + name +
                "; Resume: " + resume +
                ", broadCastsList=" + broadCastsList;
    }
}

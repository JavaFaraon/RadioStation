package org.example;

public class StaffPresenter extends Presenter {
    int workExperience;

    public StaffPresenter(String name, int workExperience) {
        super(name);
        this.workExperience = workExperience;
    }

    @Override
    public String toString() {
        return "StaffPresenter " + name +
                "; work experience - " + workExperience +
                " years; " +
                "broadCastsList=" + broadCastsList;
    }
}

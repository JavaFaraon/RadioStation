package org.example;

public class Advertising extends TypeOfBroadcasts {

    String nameOfPromotionalProduct;
    final int price = 300;

    @Override
    public String toString() {
        return "Advertising of '" + nameOfPromotionalProduct + '\'' +
                ", цена - " + price + " " + CURRENCY + "/мин" +
                ", duration: " + duration + "мин";
    }

    public Advertising(double duration, String nameOfPromotionalProduct) {
        super(duration);
        this.nameOfPromotionalProduct = nameOfPromotionalProduct;
    }

    public int getPrice() {
        return price;
    }

}

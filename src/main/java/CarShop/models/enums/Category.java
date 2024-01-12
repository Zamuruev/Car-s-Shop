package CarShop.models.enums;

public enum Category {

    Car(0), Bus(1), Truck(2), Motorcycle(3);

    private final int index;
    Category(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

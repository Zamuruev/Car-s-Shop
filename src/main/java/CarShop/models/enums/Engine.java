package CarShop.models.enums;

public enum Engine {
    Gasoline(0), Diesel(1),
    Electric(2), Hybrid(3);

    private final int index;
    Engine(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
